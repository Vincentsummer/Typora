# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.7

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/vincent/clion-2017.1.1/bin/cmake/bin/cmake

# The command to remove a file.
RM = /home/vincent/clion-2017.1.1/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/vincent/Typora/C++

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/vincent/Typora/C++/cmake-build-debug

# Include any dependencies generated for this target.
include vPackage/Test/CMakeFiles/semaphoreTest.dir/depend.make

# Include the progress variables for this target.
include vPackage/Test/CMakeFiles/semaphoreTest.dir/progress.make

# Include the compile flags for this target's objects.
include vPackage/Test/CMakeFiles/semaphoreTest.dir/flags.make

vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o: vPackage/Test/CMakeFiles/semaphoreTest.dir/flags.make
vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o: ../vPackage/Test/semaphoreTest.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/vincent/Typora/C++/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o"
	cd /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test && /usr/bin/c++   $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o -c /home/vincent/Typora/C++/vPackage/Test/semaphoreTest.cpp

vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.i"
	cd /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/vincent/Typora/C++/vPackage/Test/semaphoreTest.cpp > CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.i

vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.s"
	cd /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test && /usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/vincent/Typora/C++/vPackage/Test/semaphoreTest.cpp -o CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.s

vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.requires:

.PHONY : vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.requires

vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.provides: vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.requires
	$(MAKE) -f vPackage/Test/CMakeFiles/semaphoreTest.dir/build.make vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.provides.build
.PHONY : vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.provides

vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.provides.build: vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o


# Object files for target semaphoreTest
semaphoreTest_OBJECTS = \
"CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o"

# External object files for target semaphoreTest
semaphoreTest_EXTERNAL_OBJECTS =

bin/semaphoreTest: vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o
bin/semaphoreTest: vPackage/Test/CMakeFiles/semaphoreTest.dir/build.make
bin/semaphoreTest: lib/libvincent.a
bin/semaphoreTest: vPackage/Test/CMakeFiles/semaphoreTest.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/vincent/Typora/C++/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable ../../bin/semaphoreTest"
	cd /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test && $(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/semaphoreTest.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
vPackage/Test/CMakeFiles/semaphoreTest.dir/build: bin/semaphoreTest

.PHONY : vPackage/Test/CMakeFiles/semaphoreTest.dir/build

vPackage/Test/CMakeFiles/semaphoreTest.dir/requires: vPackage/Test/CMakeFiles/semaphoreTest.dir/semaphoreTest.cpp.o.requires

.PHONY : vPackage/Test/CMakeFiles/semaphoreTest.dir/requires

vPackage/Test/CMakeFiles/semaphoreTest.dir/clean:
	cd /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test && $(CMAKE_COMMAND) -P CMakeFiles/semaphoreTest.dir/cmake_clean.cmake
.PHONY : vPackage/Test/CMakeFiles/semaphoreTest.dir/clean

vPackage/Test/CMakeFiles/semaphoreTest.dir/depend:
	cd /home/vincent/Typora/C++/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/vincent/Typora/C++ /home/vincent/Typora/C++/vPackage/Test /home/vincent/Typora/C++/cmake-build-debug /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test /home/vincent/Typora/C++/cmake-build-debug/vPackage/Test/CMakeFiles/semaphoreTest.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : vPackage/Test/CMakeFiles/semaphoreTest.dir/depend

