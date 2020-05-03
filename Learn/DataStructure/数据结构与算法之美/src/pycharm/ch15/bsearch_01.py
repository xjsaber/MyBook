#!/usr/bin/python3

arr = [3, 5, 5, 6, 8]
n = 5
value = 5
low = 0
high = n - 1
result = -1
while low <= high:
    mid = low + ((high - low) >> 1)
    if arr[mid] > value:
        high = mid - 1
    elif arr[mid] < value:
        low = mid + 1
    else:
        if (mid == 0) or (arr[mid - 1] != value):
            result = mid
            break
        else:
            high = mid - 1

print(result)
