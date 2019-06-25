package sample

import (
	"log"
	"os"
)

func init()  {
	log.SetOutput(os.Stdout)
}
