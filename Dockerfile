# --- Builder Stage ---
FROM python:3.11-slim AS builder

# Set environment variables
ENV POETRY_NO_INTERACTION=1 \
    POETRY_VIRTUALENVS_CREATE=false

# Install Java (required for ANTLR) and build tools
RUN apt-get update && apt-get install -y --no-install-recommends \
    default-jre \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copy the project files needed for generation
COPY pyttern/ /app/pyttern/

# Run ANTLR generation
# We use the existing generate.sh script
RUN cd /app/pyttern/antlr && chmod +x generate.sh && ./generate.sh

# Cleanup: Remove non-python files from the antlr directory to save space
# This removes .g4, .jar, .sh, .tokens, and .interp files
RUN find /app/pyttern/antlr -type f ! -name "*.py" ! -name "__init__.py" -delete


# --- Final Stage ---
FROM python:3.11-slim

LABEL org.opencontainers.image.source=https://github.com/JulienLie/Pyttern

ENV PYTHONUNBUFFERED=1 \
    PYTHONDONTWRITEBYTECODE=1 \
    POETRY_VERSION=1.8.3 \
    POETRY_HOME="/opt/poetry" \
    POETRY_VIRTUALENVS_CREATE=false \
    POETRY_NO_INTERACTION=1

# Install system dependencies
RUN apt-get update && apt-get install -y --no-install-recommends \
    curl \
    && rm -rf /var/lib/apt/lists/*

# Install Poetry
RUN curl -sSL https://install.python-poetry.org | python3 -
ENV PATH="$POETRY_HOME/bin:$PATH"

WORKDIR /app

# Copy requirements
COPY pyproject.toml poetry.lock* README.md /app/

# Install dependencies
RUN poetry install --no-root --only main

# Copy the generated code and project from the builder
COPY --from=builder /app/pyttern/ /app/pyttern/

# Install the project
RUN poetry install --only main

EXPOSE 5000

ENTRYPOINT ["pyttern"]
