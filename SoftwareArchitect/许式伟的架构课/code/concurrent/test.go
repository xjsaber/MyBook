package main

import (
	"fmt"
	"time"
)

func hello() {
	fmt.Println("hello world goroundine")
}

func main() {
	go hello()
	time.Sleep(1 * time.Second)
	fmt.Println("hello world")
}
