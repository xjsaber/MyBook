#include <iostream>
#define NONBLANK 'a'

using namespace std;

int main() {
    int c;
    int tab = 0, space = 0, nl = 0;
    while(c=getchar() != EOF){
        switch (c){
            case ' ':
                space++;
            case '\t':
                tab++;
            case '\n':
                nl++;
                break;
        }
    }
    printf("space: %d\n tab: %d\n nl: %d", space, tab nl);
    return 0;
}