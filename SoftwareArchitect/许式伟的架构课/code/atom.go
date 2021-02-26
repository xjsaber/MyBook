package main

import (
	"fmt"
	"sync"
)

func judgeCircle(moves string) bool {
	x, y := 0, 0
	length := len(moves)
	for i:=0; i<length; i++ {
		switch moves[i] {
		case 'U':
			y--
		case 'D':
			y++
		case 'L':
			x--
		case 'R':
			x++
		}
	}
	return x == y && x == 0;
}

func main() {
	//var val int32 = 1
	//var delta int32 = 1
	//var newval = atomic.AddInt32(&val, delta)
	//fmt.Println(newval)

	var val int32 = 1
	var delta int32 = 1
	var mutex sync.Mutex
	mutex.Lock()
	val += delta
	var newval = val
	fmt.Println(newval)
	mutex.Unlock()
}
