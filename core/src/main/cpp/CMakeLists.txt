cmake_minimum_required(VERSION 3.4.1)

add_library(native-lib SHARED libnative-mojaku.cpp)

find_library(native-lib log)

target_link_libraries(native-lib ${log-lib})