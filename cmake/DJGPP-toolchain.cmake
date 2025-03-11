# Toolchain to compile using DJGPP (MS-DOS)
  set(CMAKE_SYSTEM_NAME Generic)
  set(CMAKE_SYSTEM_PROCESSOR i586)

# Define C compiler
  set(CMAKE_C_COMPILER gcc)
  set(CMAKE_C_COMPILER_WORKS 1)

# Define C++ compiler
  set(CMAKE_CXX_COMPILER g++)
  set(CMAKE_CXX_COMPILER_WORKS 1)

# Define linker
  set(CMAKE_AR i586-pc-msdosdjgpp-ar CACHE FILEPATH "Archiver")
  set(CMAKE_RANLIB i586-pc-msdosdjgpp-ranlib CACHE FILEPATH "Ranlib")

# Define flags
  set(CMAKE_C_FLAGS "-Os -march=i586" CACHE STRING "C compiler flags")
  set(CMAKE_EXE_LINKER_FLAGS "-s" CACHE STRING "Linker flags")

