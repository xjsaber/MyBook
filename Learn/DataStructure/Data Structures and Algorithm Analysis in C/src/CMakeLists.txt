#指定CMake编译最低要求版本
cmake_minimum_required(VERSION 3.15)
#给项目命名
project(src C)

set(CMAKE_C_STANDARD 99)

add_executable(src main.c)
add_library(list D_List_C.c)
add_library(stack D_Stack_C.c)
