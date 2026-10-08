from loguru import logger

from .main import PytternMatcher, match_files

# Disable library logging by default to avoid stderr spam and formatting overhead
logger.disable("pyttern")

__all__ = [
    "PytternMatcher",
    "match_files",
]