package base

import (
	"fmt"
)

func swap(x, y string) (string, string) {
	return y, x
}

func main()  {
	a, b := swap("hello", "world")
	fmt.Print(a, b)
}
