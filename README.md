# SimpleLex
java实现的简易lex工具v1.0

此处不涉及语法分析，只是简单的词法分析。

## 输入输出

1. 自行定义.l文件作为Lex源程序，采用lex工具生成的程序可执行文件后（此版本生成可执行java文件）。
2. 输入任意长度字符串，即可得到输出（此版本只支持词法单元序列）。

## Lex语言定义

这里Lex程序的格式与一般的Lex程序格式一样：  

声明部分  
% %  
转换规则  
% %  
辅助函数  

附：Lex的正则表达式

|表达式|匹配|例子|
|:---|:----|:----|
|c|单个非运算符字符c|a|
|\c|字符c的字面值|\\*|
|"s"|串s的字面值|"**"|
|\.|除换行符以外的任何字符|a\.\*b|
|^|一行的开始|\^abc|
|$|行的结尾|abc$|
|\[s\]|字符串s中的任何一个字符|\[abc\]|
|\[^s\]|不在串s中的任何一个字符|\[^abc\]|
|r\*|和r匹配的零个或多个串连接成的串|a\*|
|r+|和r匹配的一个或多个串连接成的串|a+|
|r?|零个或一个r|a?|
|r{m,n}|最少m个，最多n个r的重复出现|a{1,5}|
|r<sub>1</sub>r<sub>r2</sub>|r<sub>1</sub>后加上r<sub>2</sub>|ab|
|r<sub>1</sub>\|r<sub>r2</sub>|r<sub>1</sub>或r<sub>r2</sub>|a\|b|
|(r)|与r相同|(a\|b)|
|r<sub>1</sub>/r<sub>r2</sub>|后面跟有r<sub>2</sub>时的r<sub>1</sub>|abc/123|