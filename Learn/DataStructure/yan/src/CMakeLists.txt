cmake_minimum_required(VERSION 3.15)
project(src)

set(CMAKE_CXX_STANDARD 14)

add_executable(src ArrayList.h ArrayList.cpp main.cpp LinkedList.h LinkedList.cpp DLinkedList.cpp DLinkedList.h)