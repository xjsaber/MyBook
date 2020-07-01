package main

import (
	"fmt"
	"sync"
)

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
