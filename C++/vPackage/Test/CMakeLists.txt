add_executable(semaphoreTest semaphoreTest.cpp)
target_link_libraries(semaphoreTest vincent)

add_executable(atomic_flagTest atomic_flagTest.cpp)
target_link_libraries(atomic_flagTest vincent)

add_executable(syncTest syncTest.cpp)
target_link_libraries(syncTest vincent)

add_executable(DingingPhilosophersTest DingingPhilosophersTest.cpp)
target_link_libraries(DingingPhilosophersTest vincent)

add_executable(sortTest sortTest.cpp)
target_link_libraries(sortTest vincent)

add_executable(virtualDestructorTest virtualDestructorTest.cpp)
target_link_libraries(virtualDestructorTest vincent)

add_executable(bankerTest bankerTest.cpp)
target_link_libraries(bankerTest vincent)

add_executable(mytest mytest.cpp)
target_link_libraries(mytest vincent)