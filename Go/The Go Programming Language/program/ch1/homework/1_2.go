package main

import (
	"fmt"
	"os"
)

func main() {
	s, sep := "", ""
	for i, arg := range os.Args[1:] {
		s += sep + arg
		sep = " "
		fmt.Printf("%d: %s \n", i, arg)
	}
	fmt.Println(s)
}
