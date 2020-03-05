cmake_minimum_required(VERSION 3.7)
project(CPP)

enable_testing()

if(NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE "Release")
endif()

# only build examples if this is the main project
if(CMAKE_PROJECT_NAME STREQUAL "CPP")
    option(MUDUO_BUILD_EXAMPLES "Build C_net examples" ON)
endif()

set(CMAKE_CXX_STANDARD 11)

set(CXX_FLAGS
        -g
        # -DVALGRIND
        -DCHECK_PTHREAD_RETURN_VALUE
        -D_FILE_OFFSET_BITS=64
        -Wall
        -Wextra
        #        -Werror
        #        -Wconversion
        #        -Wno-unused-parameter
        -Wold-style-cast
        -Woverloaded-virtual
        -Wpointer-arith
        -Wshadow
        #        -Wwrite-strings
        -march=native
        # -MMD
        -std=c++11
        -rdynamic
        )

string(REPLACE ";" " " CMAKE_CXX_FLAGS "${CXX_FLAGS}")

set(CMAKE_CXX_FLAGS_DEBUG "-O0")
set(CMAKE_CXX_FLAGS_RELEASE "-O2 -DNDEBUG")
set(EXECUTABLE_OUTPUT_PATH ${PROJECT_BINARY_DIR}/bin)
set(LIBRARY_OUTPUT_PATH ${PROJECT_BINARY_DIR}/lib)

file(GLOB HEADERS "*.h")
install(FILES ${HEADERS} DESTINATION include/C++)

add_subdirectory(Leetcode)
add_subdirectory(vPackage)
add_executable(CPP main.cpp)

