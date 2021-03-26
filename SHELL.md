# **连续执行多个命令**  
a）`command1 ; command2`  
command1执行完之后，不管失败与否，都将开始执行command2。如果没有set -e这样的设置，command1 ; command2之后的shell语句会继续执行。  
b）`command1 && command2`  
当command1执行完毕之后，且回传码是0，才会执行command2。如果没有set -e这样的设置，command1 && command2之后的shell语句会继续执行。  
c）`command1 || command2`  
当command1执行完毕之后，且回传码是不等于0，才会执行command2。如果没有set -e这样的设置，command1 || command2之后的shell语句会继续执行。  
d）`command1 | command2`  
这个是管道命令啦，如果没有set -e这样的设置，command1 | command2之后的shell语句会继续执行。

# sudo vs su
[sudo_su](cmd/linux_unix/sudo_su.md)  

# 参考
[Linux下多个命令的组合方式小结](https://blog.csdn.net/wangjianno2/article/details/17200599)