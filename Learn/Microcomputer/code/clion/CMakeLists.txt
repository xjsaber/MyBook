cmake_minimum_required(VERSION 3.16)
project(src C)

set(CMAKE_C_STANDARD 11)

add_executable(src
        add_lib.c
        for.c
        function_example.c
        ifelse.c
        link_example.c)
