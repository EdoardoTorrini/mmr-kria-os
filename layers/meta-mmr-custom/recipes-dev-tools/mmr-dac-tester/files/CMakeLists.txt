cmake_minimum_required(VERSION 3.8)
project(mmr_dac_test)

add_executable(${PROJECT_NAME} src/main.cpp src/mcp4921.cpp)
target_include_directories(${PROJECT_NAME} PUBLIC include)
set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 20)

install(
  TARGETS ${PROJECT_NAME}
  DESTINATION lib/${PROJECT_NAME}
)