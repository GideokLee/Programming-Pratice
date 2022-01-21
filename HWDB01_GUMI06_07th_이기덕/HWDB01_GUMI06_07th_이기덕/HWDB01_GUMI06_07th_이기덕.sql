DROP DATABASE IF EXISTS products;

CREATE DATABASE products;

USE products;


DROP TABLE IF EXISTS `product`;

# 1.
CREATE TABLE `product` (
  `Code` varchar(20) CHARACTER SET utf8mb4 NOT NULL DEFAULT '',
  `Name` varchar(255) CHARACTER SET utf8mb4 NOT NULL,
  `price` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# 2.
insert into product 
values ('001' , '노트북', 10000),
('002' , '벽걸이형 TV', 20000),
('003' , '스탠드형 TV', 30000),
('004' , '냉장고', 40000),
('005' , '노트북2', 50000);

# 3.
select code, name, price * 0.85 as 'sale price' 
from product;

# 4.
update product
set price = (price * 0.8)
where name like '%TV%';

select *
from product;

# 5.
select sum(price) as 'totalPrice'
from product;

