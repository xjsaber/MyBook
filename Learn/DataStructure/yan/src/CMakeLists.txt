cmake_minimum_required(VERSION 3.15)
project(src)

set(CMAKE_CXX_STANDARD 14)

add_executable(main main.cpp Base.h yan/LinkQueue.h yan/LinkQueue.cpp yan/SString.cpp yan/SString.h yan/SortUtils.cpp yan/SortUtils.h MyGraph.h MyGraph.cpp wang/WGraph.h wang/WGraph.cpp)