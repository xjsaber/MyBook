cmake_minimum_required(VERSION 3.15)
project(src)

set(CMAKE_CXX_STANDARD 14)

add_executable(main main.cpp Base.h yan/LinkStack.cpp yan/LinkStack.h yan/LinkQueue.cpp yan/LinkQueue.h yan/SqStack.cpp yan/SqStack.h)