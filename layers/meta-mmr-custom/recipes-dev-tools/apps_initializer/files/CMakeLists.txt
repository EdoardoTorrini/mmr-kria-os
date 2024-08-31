cmake_minimum_required(VERSION 3.8)
project(apps_initializer)

add_executable(${PROJECT_NAME} src/apps_initializer.cpp)
target_include_directories(${PROJECT_NAME} PUBLIC include)
set_property(TARGET ${PROJECT_NAME} PROPERTY CXX_STANDARD 20)

install(
  TARGETS ${PROJECT_NAME}
  DESTINATION lib/${PROJECT_NAME}
)