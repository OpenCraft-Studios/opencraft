message(STATUS "Building for Linux")

# Architecture options
option(TARGET_LINUX_64 "Build for Linux 64-bit with GCC" OFF)
option(TARGET_LINUX_32 "Build for Linux 32-bit with GCC" OFF)

if(TARGET_LINUX_64)
    message(STATUS "Building for Linux 64-bit")
    set(CMAKE_SYSTEM_NAME Linux)
    set(EXECUTABLE_NAME "oc_linux64")
elseif(TARGET_LINUX_32)
    message(STATUS "Building for Linux 32-bit")
    set(CMAKE_SYSTEM_NAME Linux)
    set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -m32")  # Enable 32-bit mode
    set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -m32")  # Enable 32-bit mode in C++
    set(EXECUTABLE_NAME "oc_linux32")
endif()

# Find GLFW & OpenGL
find_package(OpenGL REQUIRED)
find_package(glfw3 REQUIRED)

# Include directories
include_directories(${OPENGL_INCLUDE_DIR})
link_directories(${GLFW_LIBRARY_DIRS})

# Create the executable
add_executable(opencraft ${SRC_FILES})

# Apply compilation flags
target_link_libraries(opencraft PRIVATE ${OPENGL_gl_LIBRARY} glfw)
