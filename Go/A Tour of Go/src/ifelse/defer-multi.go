package ifelse

import "fmt"

func main()  {
	fmt.Println("counting")

	defer fmt.Println("-1")
	for i := 0; i < 10; i++ {
		defer fmt.Println(i)
	}

	fmt.Println("done")
}
