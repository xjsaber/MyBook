package advance

import "fmt"

func main()  {
	i, j := 42, 2010

	p := &i
	fmt.Println(*p)
	*p = 21

}
