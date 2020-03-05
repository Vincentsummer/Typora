set(vincent_SRC
        vSortAndSearch/sort.cpp
        vTree/vTree.cpp
        vThread/vSycnTest.cpp
        vThread/vDiningPhilosophers.cpp
        vThread/vBanker.cpp
        vUtils.cpp
        )

add_library(vincent ${vincent_SRC})
target_link_libraries(vincent pthread)

install(TARGETS vincent DESTINATION lib)

file(GLOB HEADERS "*.h")
install(FILES ${HEADERS} DESTINATION include/C++/vPackage)


if(MUDUO_BUILD_EXAMPLES)
    add_subdirectory(Test)
endif()