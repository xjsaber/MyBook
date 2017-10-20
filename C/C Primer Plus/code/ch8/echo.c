#include <stdio.h>

int main() {
    char ch;

    while ((ch = (char) getchar()) != '#')
        putchar(ch);

    return 0;
}