package main

import "os"

func main() {
	counts := make(map[string]int)
	files := os.Args[1:]
	if len(files) == 0 {
		count
	}
}
