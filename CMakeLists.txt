cmake_minimum_required(VERSION 3.10)
project(OpenCraft C)

## Debug helper
  set(CMAKE_VERBOSE_MAKEFILE ON)

## C/C++ standars
  set(CMAKE_C_STANDARD 99)
  set(CMAKE_C_STANDARD_REQUIRED True)

## Source files
  # (Recursively search for all .c files in src/ and subdirectories)
  file(GLOB_RECURSE SRC_FILES ${CMAKE_SOURCE_DIR}/src/*.c)

## Compilation target options
  option(TARGET_WINDOWS "Build for Windows (32/64-bit)" OFF)
  option(TARGET_LINUX "Build for Linux (32/64-bit)" OFF)
  option(TARGET_MSDOS "Build for MS-DOS with DJGPP" OFF)

## Executable settings
  # Default executable name
    set(EXECUTABLE_NAME "opencraft")

  # Set output directory and filename
    set(CMAKE_RUNTIME_OUTPUT_DIRECTORY ${CMAKE_SOURCE_DIR}/bin)
    set(CMAKE_RUNTIME_OUTPUT_NAME ${EXECUTABLE_NAME})
    set(CMAKE_EXECUTABLE_SUFFIX "")

# Load platform-specific configuration
if(TARGET_WINDOWS)
    include(cmake/Windows.cmake)
elseif(TARGET_LINUX)
    include(cmake/Linux.cmake)
elseif(TARGET_MSDOS)
    include(cmake/MSDOS.cmake)
else()
    message(STATUS "Building in standard mode (compatible with the current system)")
endif()
