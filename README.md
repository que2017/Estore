# Estore
电子商城案例

#1 数据库创建
##1.1 用户表
id	主键（自增长）
username	用户名
password	密码
nickname	昵称
email		邮箱
role		角色
state		激活状态
activecode	激活码
updatetime	更新时间
数据表创建语句：
CREATE TABLE users(
	id int primary key auto_increment,
	username varchar(40),
	password varchar(100),
	nickname varchar(40),
	email varchar(100),
	role varchar(100),
	state int,
	activecode varchar(100),
	updatetime timestamp
)

##1.2 商品表
id		主键（自增长）
name		商品名称
price		商品单价
category	商品种类
pnum		商品库存数量
imgurl		商品图片链接
description	商品描述
数据表创建语句：
CREATE TABLE products(
	id varchar(100) primary key,
	name varchar(40),
	price double,
	category varchar(40),
	pnum int,
	imgurl varchar(100),
	description varchar(255)
);

##1.3 订单表
id		主键（自增长）
money		订单金额
receiverinfo	收货地址
paystate	支付状态
ordertime	下单时间
user_id		用户编号（外键）
数据表创建语句：
CREATE TABLE orders(
	id varchar(100) primary key,
	money double,
	receiverinfo varchar(255),
	paystate int,
	ordertime timestamp,
	user_id int,
	foreign key(user_id) references users(id)
);

##1.4 订单项表
order_id	订单编号（外键）
product_id	商品编号（外键）
buynum		购买数量
primary		联合主键，order_id和product_id两列的值加在一起作为这张表的主键使用
CREATE TABLE orderitem(
	order_id varchar(100),
	product_id varchar(100),
	buynum int,
	primary key(order_id,product_id), #联合主键,两列的值加在一起作为这张表的主键使用
	foreign key(order_id) references orders(id),
	foreign key(product_id) references products(id)
);
