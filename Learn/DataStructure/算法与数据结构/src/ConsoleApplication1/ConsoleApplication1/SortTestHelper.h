#pragma once

#define SELECTIONSORT_SORTTESTHELPER_H

#include <iostream>
#include <time.h>
#include <cassert>

using namespace std;

namespace SortTestHelper {

	// 生成有n个元素的随机数组，每个元素的随机范围为[rangeL, rangeR]
	int* generateRandomArray(int n, int rangeL, int rangeR) {
		int* arr = new int[n];

		assert(rangeL <= rangeR);
		srand(time(nullptr));
        for (int i = 0; i < n; i++){
            arr[i] = rand() % (rangeR - rangeL + 1) + rangeL;
        }
		return arr;
	}
}
