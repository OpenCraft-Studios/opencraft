message(STATUS "Building for Windows")

# Define GLFW root directory first
set(GLFW_DIR "${CMAKE_SOURCE_DIR}/glfw-unbounded")

# Define GLFW include directory
include_directories("${GLFW_DIR}/include")

# Architecture options
option(TARGET_WINDOWS_64 "Build for Windows 64-bit with MinGW" OFF)
option(TARGET_WINDOWS_32 "Build for Windows 32-bit with MinGW" OFF)

if(TARGET_WINDOWS_64)
    message(STATUS "Building for Windows 64-bit")
    set(CMAKE_SYSTEM_NAME Windows)
    set(CMAKE_C_COMPILER x86_64-w64-mingw32-gcc)
    set(CMAKE_CXX_COMPILER x86_64-w64-mingw32-g++)
    set(EXECUTABLE_NAME "oc_win64")

    # GLFW
    set(GLFW_ARCH_DIR "${GLFW_DIR}/win64")
elseif(TARGET_WINDOWS_32)
    message(STATUS "Building for Windows 32-bit")
    set(CMAKE_SYSTEM_NAME Windows)
    set(CMAKE_C_COMPILER i686-w64-mingw32-gcc)
    set(CMAKE_CXX_COMPILER i686-w64-mingw32-g++)
    set(EXECUTABLE_NAME "oc_win32")

    # GLFW
    set(GLFW_ARCH_DIR "${GLFW_DIR}/win32")
endif()

set(CMAKE_EXECUTABLE_SUFFIX ".exe")

# Build executable
add_executable(opencraft ${SRC_FILES})

# Link GLFW statically
target_link_libraries(opencraft PRIVATE
        ${GLFW_ARCH_DIR}/libglfw3.a
        -static
        -static-libgcc
        -static-libstdc++
        -mwindows
        -lopengl32
        -lgdi32
        -luser32
        -lshell32
)
