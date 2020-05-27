cmake_minimum_required(VERSION 3.15)
project(src)

set(CMAKE_CXX_STANDARD 14)

add_executable(main main.cpp Base.h yan/SqList.h yan/SqList.cpp yan/LinkList.h yan/LinkList.cpp yan/DuLinkList.h yan/DuLinkList.cpp)