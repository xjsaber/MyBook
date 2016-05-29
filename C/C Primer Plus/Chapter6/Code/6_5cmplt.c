/* t_and_f.c -- 浮点数比较 */
#include <math.h>
#include <stdio.h>
int main(void)
{
    const double ANSWER = 3.14159;
    double response;

    printf("What is the value of pi?\n ");
    while (fabs(response - ANSWER) > 0.0001)
    {
        scanf("%lf", &response);
        if (fabs(response - ANSWER) > 0.0001) {
            printf("Try again!\n");
        }
    }
    printf("Close enough!\n");

    return 0;
}