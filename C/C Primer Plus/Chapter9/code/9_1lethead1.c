/* lethead1.c  */
#include <stdio.h>
#define NAME "GIGATHINK, INC."
#define ADDRESS "101 Megabuck Plzaz"
#define PLACE "Megapolis, CA 94904"
#define WIDTH 40

void starBar(void); /* 函数原型 */

int main(void)
{
    starBar();
    printf("%s\n", NAME);
    printf("%s\n", ADDRESS);
    printf("%s\n", PLACE);

    starBar(); /* 使用函数 */

    return 0;
}

void starBar(void) /* 定义函数*/
{
    int count;

    for (count = 1; count <= WIDTH; count++) {
        putchar('*');
    }
    putchar('\n');
}