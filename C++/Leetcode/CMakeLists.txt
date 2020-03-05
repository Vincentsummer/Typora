set(leetcode_SRC
        24_swapPairs.cpp
        26_removeDuplicates.cpp
        83_deleteDuplicates.cpp
        94_inorderTraversal.cpp
        108_sortedArrayToBST.cpp
        112_hasPathSum.cpp
        127_ladderLength.cpp
        142_detectCycle.cpp
        144_preorderTraversal.cpp
        147_insertionSortList.cpp
        150_evalRPN.cpp
        279_numSquares.cpp
        306_isAdditiveNumber.cpp
        342_isPowerOfFour.cpp
        368_largestDivisibleSubset.cpp
        437_pathSum.cpp
        459_repeatedSubstringPattern.cpp
        462_minMoves2.cpp
        476_findComplement.cpp
        479_largestPalindrome.cpp
        529_updateBoard.cpp
        559_maxDepth.cpp
        621_leastInterval.cpp
        622_myCircularQueue.cpp
        649_predictPartyVictory.cpp
        650_minSteps.cpp
        678_checkValidString.cpp
        731_myCalendarTwo.cpp
        796_rotateString.cpp
        899_orderlyQueue.cpp
        951_flipEquiv.cpp
        1116_zeroEvenOdd.cpp
        1154_dayOfYear.cpp
        1122_relativeSortArray.cpp
        1209_removeDuplicates.cpp
        1219_getMaximumGold.cpp
        1235_jobScheduling.cpp
        1252_oddCells.cpp
        1337_kWeakestRows.cpp

        m38_permutation.cpp
        m67_strToInt.cpp
        m0401_findWhetherExistsPath.cpp
        m1010_StreamRank.cpp
        m1701_add.cpp
        )

add_library(leetcode ${leetcode_SRC})
target_link_libraries(leetcode vincent pthread)

install(TARGETS leetcode DESTINATION lib)

file(GLOB HEADERS "*.h")
install(FILES ${HEADERS} DESTINATION include/C++/Leetcode)


if(MUDUO_BUILD_EXAMPLES)
    add_subdirectory(Test)
endif()