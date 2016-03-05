#include <iostream>

using namespace std;

int main() {
    int c;
    while(c = getchar() != EOF){
        printf("%d\n", c);
    }
    printf("%d - at EOF\n", c);

    return 0;
}