#指定CMake编译最低要求版本
cmake_minimum_required(VERSION 3.15)
#给项目命名
project(src C)

set(CMAKE_C_STANDARD 99)

#收集c/c++文件并赋值给变量SRC_LIST_CPP  ${PROJECT_SOURCE_DIR}代表区当前项目录
FILE(GLOB SRC_LIST_CPP ${PROJECT_SOURCE_DIR}/src/*.cpp)
FILE(GLOB SRC_LIST_C ${PROJECT_SOURCE_DIR}/src/*.c)
#指定头文件目录
INCLUDE_DIRECTORIES(${PROJECT_SOURCE_DIR}/include)
#指定生成库文件的目录
SET(LIBRARY_OUTPUT_PATH ${PROJECT_SOURCE_DIR}/lib)
#去变量SRC_LIST_CPP 与SRC_LIST_C 指定生成libmyprint 动态库   默认生成静态库  SHARED指定生成库类型为动态库
ADD_LIBRARY(myprint SHARED ${SRC_LIST_CPP} ${SRC_LIST_C})
