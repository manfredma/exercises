# mysql

## 连接

`mysql -h主机地址 -u用户名 -p用户密码 -P端口 `  
*注意用户名前可以有空格也可以没有空格，但是密码前必须没有空格，否则让你重新输入密码*

`mysql -h127.0.0.1 -ucoupon -pcoupon -P3306`

## 用户管理

### 1. 用户

**创建用户**  
`CREATE USER 'dog'@'localhost' IDENTIFIED BY '123456';`  
`CREATE USER 'pig'@'192.168.1.101_' IDENDIFIED BY '123456';`  
`CREATE USER 'pig'@'%' IDENTIFIED BY '123456';`  
`CREATE USER 'pig'@'%' IDENTIFIED BY '';`  
`CREATE USER 'pig'@'%';`  
`create user 'coupon'@'%' identified by 'coupon';`

**删除用户**  
`DROP USER 'username'@'host';`

### 2. 权限

**授权**  
`GRANT privileges ON databasename.tablename TO 'username'@'host' WITH GRANT OPTION;`  
`GRANT SELECT, INSERT ON test.user TO 'pig'@'%';`  
`GRANT ALL ON *.* TO 'pig'@'%';`  
`grant all on *.* to 'coupon'@'%';`

**取消授权**  
` REVOKE privilege ON databasename.tablename FROM 'username'@'host';`  
` REVOKE SELECT ON *.* FROM 'pig'@'%';`

**刷新授权**  
` FLUSH PRIVILEGES;`

### 3. 修改密码
`SET PASSWORD FOR 'username'@'host' = PASSWORD('newpassword');`

## 常用小操作
1. 查看版本  
` select version();`
2. 切换数据库  
`use manfred;`
3. 查看表和建表语句   
`show tables;`  
`show create table mybooks;`

### 创建数据库
`CREATE DATABASE IF NOT EXISTS magento2 DEFAULT CHARSET utf8 COLLATE utf8_general_ci;//创建数据库`

# 参考

[MySQL创建用户与授权](https://www.jianshu.com/p/d7b9c468f20d)  
