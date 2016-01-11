##练习1-3
请修改温度转换程序，使之能在转换表的顶部打印一个标题

##练习1-4
编写一个程序打印摄氏问题转换相应华式问题的转换表

##练习1-6
验证布尔表达式getchar() != EOF的取值是0还是1
>当有字符可读时，getchar()不会返回文件结束符(即EOF)，所以<code>getchar() != EOF</code>的取值为真,变量c将被赋值为1.当程序遇到文件结束符时，表达式取值为假，此时，变量c将被赋值为0，程序将会结束。

##练习1-9
编写一个将输入复制到输出的程序，并将其中连续的多个空格用一个空格代替

##练习1-12
编写一个程序，打印输入中单词长度的直方图。

##备注
<table>
<tr><td>%d</td><td>print as decimal integer</td>  
<tr><td>%6d</td><td>print as decimal integer, at least 6 characters wide </td>
<tr><td>%f</td><td>   print as floating point  </td>
<tr><td>%6f</td> <td> print as floating point, at least 6 characters wide </td> 
<tr><td>%.2f</td><td>  print as floating point, 2 characters after decimal point </td> 
<tr><td>%6.2f</td><td>    print as floating point, at least 6 wide and 2 after decimal point</td>
</table> 