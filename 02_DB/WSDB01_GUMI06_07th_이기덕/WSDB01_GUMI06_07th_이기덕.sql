# 1.
USE world;

# 2.
desc city;
desc country;
desc countrylanguage;

# 3.
select * from country where code = 'KOR';

# 4.
select code, name , gnp, gnpold , GNP-GNPOld gnp변동량 
from country 
where GNP - GNPOld  >= 0 
order by GNP - GNPOld  asc;

# 5.
select distinct Continent 
from country 
order by length(Continent);

# 6.
select concat(name, '은 ', region, '에 속하며 인구는 ', population, '이다.') 정보 
from country
where Continent = 'asia' 
order by name;

# 7.
select name, Continent, gnp, Population 
from country 
where IndepYear is null and population >= 10000 order by population asc;

# 8.
select code , name , population from country 
where Population <= 200000000 and Population >= 100000000 
order by Population desc limit 3;

# 9.
select code, name, indepyear
from country
where IndepYear in ('800', '1810', '1811', '1901') order by IndepYear, Code desc;

# 10.
select code, name, region
from country
where region like '%asia%' and Name like '_o%';

# 11.
select char_length('홍길동') 한글 , char_length('hong') 영문;

# 12.
select code , name, governmentform
from country
where GovernmentForm like '%republic%' and char_length(name) >= 10 order by char_length(name) desc limit 10;

# 13.
select code, name
from country
where left(name, 1) in ('a', 'e', 'u', 'i', 'o')
or left(name, 1) in ('A', 'E', 'I', 'O', 'U')
order by name asc limit 3,3;

# 14.
select name, concat(left(name,2),lpad('', char_length(name) - 4,'*') ,right(name,2)) guess
from country;

# 15.
select distinct replace(region, ' ', '_') 지역들 
from country
order by char_length(region) desc;

# 16.
select name, surfacearea, population, round(surfacearea / population, 3) as 1인당점유면적
from country
where Population >= 100000000
order by 1인당점유면적 asc;

# 17.
select date_format(now(), '%Y-%m-%d') as 오늘, 
dayofyear(now()) as '올해 지난 날' ,
date_format(date_add(now(), interval 100 day), '%Y-%m-%d') as '100일 후';

# 18.
select name, Continent, LifeExpectancy
, if(LifeExpectancy > 80, '장수국가' , if(LifeExpectancy > 60 , '일반국가' ,'단명국가') )
from country
where LifeExpectancy is not null and Continent = 'asia'
order by LifeExpectancy;

# 19.
select name, gnp, GNPOld , if(GNPOld is null , '신규', (gnp - gnpold)) as 'gnp 향상' 
from country
order by name;

# 20.
select weekday('2020-05-05'), if(weekday('2020-05-05'), '행복', '불행') as '행복 여부';

