package main

import (
	"fmt"
	"log"
	"net/http"
)

func main() {
	http.HandleFunc("/", handle) // each request calls handler
	log.Fatal(http.ListenAndServe("localhost:8000", nil))
}

// // handler echoes the Path component of the request URL r.
func handle(w http.ResponseWriter, r *http.Request) {
	fmt.Fprintf(w, "URL.Path = %q\n", r.URL.Path)
}
