cmake_minimum_required(VERSION 3.15)
project(Clion)

set(CMAKE_CXX_STANDARD 14)

# Add main.cpp file of project root directory as source file
set(SOURCE_FILES LinkedList.h LinkedList.cpp main.cpp)

# Add executable target with source files listed in SOURCE_FILES variable
add_executable(simple_example ${SOURCE_FILES})