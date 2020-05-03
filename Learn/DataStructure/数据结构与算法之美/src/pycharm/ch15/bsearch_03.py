#!/usr/bin/python3

arr = [3, 5, 5, 5, 8]
n = 5
value = 5
low = 0
high = n - 1
result = -1
while low <= high:
    mid = low + ((high - low) >> 1)
    if arr[mid] <= value:
        low = mid + 1
    else:
        if mid == 0:
            result = mid
            break
        elif arr[mid - 1] > value:
            high = mid - 1
        else:
            result = mid
            break

print(result)
