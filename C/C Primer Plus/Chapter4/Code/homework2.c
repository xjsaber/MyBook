//
// Created by xjsaber on 2017/6/11.
//
int main(void)
{
    printf("He sold the painting for $%2.2f.\n", 2.345e2);
    // 2.34 (234.50)
    printf("%c%c%c\n", 'H', 105, '\41');
    // i 105 Hi!
#define Q "His Hamlet was funny without being vulgar."
    printf("%s\nhas %d characters.\n", Q, (int) strlen(Q));
    // His Hamlet was funny without being vulgar. has 43(42) characters.
    printf("Is %2.2e the same as %2.2f?\n", 1201.0, 1201.0);
    // ?不知道(1.20e+003) 1201.00
}