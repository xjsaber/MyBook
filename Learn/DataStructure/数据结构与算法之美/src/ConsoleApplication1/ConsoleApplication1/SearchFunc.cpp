#include "SearchFunc.h"
#include <iostream>

using namespace std;

int bSearch(int a[], int n, int value) {
	int low = 0;
	int high = n - 1;

	while (low <= high) {
		int mid = (low + high) / 2;
		cout << "low: " << low << endl;
		cout << "mid: " << mid << endl;
		cout << "high: " << high << endl;
		if (a[mid] == value) {
			return mid;
		} else if (a[mid] < value){
			low = mid + 1;
		}
		else {
			high = mid - 1;
		}
	}
	return -1;
}