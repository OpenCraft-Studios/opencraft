message(STATUS "Building for Linux")

# Define GLFW include directory
include_directories("${GLFW_DIR}/include")

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

# Include user libraries
link_directories(/usr/local/lib/)

# Build executable
add_executable(opencraft ${SRC_FILES})

# Link libraries
link_libraries(${GLFW_STATIC_LIB} glfw3)
target_link_libraries(opencraft PRIVATE ${GLFW_STATIC_LIB} GL GLU glfw3 X11 Xxf86vm Xrandr pthread Xi)
