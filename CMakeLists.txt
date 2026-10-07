cmake_minimum_required(VERSION 3.18)
project(_pyttern_cpp LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)
set(CMAKE_POSITION_INDEPENDENT_CODE ON)

# Find Python3
find_package(Python3 COMPONENTS Interpreter Development.Module REQUIRED)

# Find pybind11 (fallback to python module if not in CMAKE_PREFIX_PATH)
find_package(pybind11 CONFIG QUIET)
if(NOT pybind11_FOUND)
    execute_process(
        COMMAND "${Python3_EXECUTABLE}" -m pybind11 --cmakedir
        OUTPUT_STRIP_TRAILING_WHITESPACE
        OUTPUT_VARIABLE pybind11_DIR
    )
    find_package(pybind11 CONFIG REQUIRED)
endif()

# Find or fetch ANTLR4 C++ runtime 4.13.2
find_package(antlr4-runtime QUIET)
if(NOT antlr4-runtime_FOUND)
    include(FetchContent)
    set(ANTLR4_INSTALL OFF CACHE BOOL "" FORCE)
    set(WITH_LIBCXX OFF CACHE BOOL "" FORCE)
    set(WITH_DEMO OFF CACHE BOOL "" FORCE)
    FetchContent_Declare(
        antlr4_runtime
        URL https://www.antlr.org/download/antlr4-cpp-runtime-4.13.2-source.zip
    )
    FetchContent_MakeAvailable(antlr4_runtime)
endif()

# Include directories for ANTLR generated code and parsetree
set(PARSER_DIR "${CMAKE_CURRENT_SOURCE_DIR}/pyttern/antlr/python")
set(PARSETREE_DIR "${CMAKE_CURRENT_SOURCE_DIR}/pyttern/parsetree")

file(GLOB PARSER_SOURCES
    "${PARSER_DIR}/*.cpp"
)

file(GLOB PARSETREE_SOURCES
    "${PARSETREE_DIR}/*.cpp"
)

pybind11_add_module(_pyttern_cpp MODULE
    ${PARSETREE_SOURCES}
    ${PARSER_SOURCES}
)

target_include_directories(_pyttern_cpp PRIVATE
    ${PARSER_DIR}
    ${PARSETREE_DIR}
)

if(TARGET antlr4_static)
    target_link_libraries(_pyttern_cpp PRIVATE antlr4_static)
elseif(TARGET antlr4_shared)
    target_link_libraries(_pyttern_cpp PRIVATE antlr4_shared)
else()
    target_link_libraries(_pyttern_cpp PRIVATE antlr4_runtime)
endif()

set_target_properties(_pyttern_cpp PROPERTIES
    OUTPUT_NAME "_pyttern_cpp"
)
