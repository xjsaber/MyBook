#include <stdio.h>

int main() {
    printf("Hello, World!\n");

    const char *s = "abcdefg";
    return 0;
}

struct linux_binfmt {
    struct list_head lh;
    strcut module *module;
    int (*load_binary)(struct linux_binprm *);
    int (*load_shlib)(struct file *);
    int (*core_dump)(struct coredump_params *cprm);
    unsigned long min_coredump;     /* minimal dump size */
}__randomize_layout;