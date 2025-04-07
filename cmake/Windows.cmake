message(STATUS "Building for Windows")

## Define GLFW root directory first
  set(GLFW_DIR "${CMAKE_SOURCE_DIR}/glfw-unbounded")

## Define GLFW include directory
  include_directories("${GLFW_DIR}/include")

## Target settings
  option(TARGET_WINDOWS_64 "Build for Windows 64-bit with MinGW" OFF)
  option(TARGET_WINDOWS_32 "Build for Windows 32-bit with MinGW" OFF)

set(CMAKE_SYSTEM_NAME Windows)
set(CMAKE_EXECUTABLE_SUFFIX ".exe")

if(TARGET_WINDOWS_64)
    message(STATUS "Building for Windows 64-bit")

    set(CMAKE_C_COMPILER x86_64-w64-mingw32-gcc)
    set(CMAKE_CXX_COMPILER x86_64-w64-mingw32-g++)
    set(EXECUTABLE_NAME "oc_win64")

    # GLFW
    set(GLFW_ARCH_DIR "${GLFW_DIR}/win64")

elseif(TARGET_WINDOWS_32)
    message(STATUS "Building for Windows 32-bit")

    set(CMAKE_C_COMPILER i686-w64-mingw32-gcc)
    set(CMAKE_CXX_COMPILER i686-w64-mingw32-g++)
    set(EXECUTABLE_NAME "oc_win32")

    # GLFW
    set(GLFW_ARCH_DIR "${GLFW_DIR}/win32")

else()
    message(FATAL_ERROR "No target architecture specified. Use -DTARGET_WINDOWS_64=ON or -DTARGET_WINDOWS_32=ON")

endif()

set(GLFW_STATIC_LIB "${GLFW_ARCH_DIR}/libglfw3.a")

# region GLFW Required
if(NOT EXISTS ${GLFW_STATIC_LIB})
    message(FATAL_ERROR "GLFW static library not found: ${GLFW_STATIC_LIB}")
endif()
# endregion GLFW Required

## Ensure to locate the directory where GLFW is
  link_directories(${GLFW_ARCH_DIR})

## Build executable
  add_executable(${EXECUTABLE_NAME} ${SRC_FILES})

## Static linking
  target_compile_options(${EXECUTABLE_NAME} PRIVATE -static -mwindows)

## No console please
  target_link_options(${EXECUTABLE_NAME} PRIVATE -mwindows)

## Link libraries
  link_libraries(${GLFW_STATIC_LIB} glfw3 opengl32 gdi32 winmm)
  target_link_libraries(${EXECUTABLE_NAME} PRIVATE ${GLFW_STATIC_LIB} glfw3 opengl32 gdi32 winmm)
