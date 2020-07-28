cmake_minimum_required(VERSION 3.16)
project(wang)

set(CMAKE_CXX_STANDARD 14)

add_executable(wang main.cpp MyArrayQueue.h MyArrayQueue.cpp)