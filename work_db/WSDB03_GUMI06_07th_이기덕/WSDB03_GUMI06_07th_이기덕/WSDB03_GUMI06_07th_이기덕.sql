use world;

# 1.
select code, country.name
from country, city
where city.name = 'Kabul';

# 2.
select country.name , language, percentage
from country, countrylanguage
where countrylanguage.percentage = 100 and country.code = countrylanguage.countrycode
order by name asc;

# 3.
select city.name, language, country.name
from city, countrylanguage, country
where city.name = 'amsterdam' and city.countrycode = country.code
and countrylanguage.countrycode = city.countrycode
order by countrylanguage.percentage desc
limit 1;

# 4.
select co.name , co.capital, city.name, city.population 
from country co join city
on co.capital = city.id
where co.name like 'United%';

# 5.
select country.name, capital, if(capital is null, '수도 없음', city.name) as 수도 ,
if(city.population is null, '수도 없음', city.population) as 수도인구
from country left outer join city
on city.id = country.capital
where country.name like 'United%';

# 6.
select count(percentage)
from country co join countrylanguage lang
on co.code = lang.countrycode
where lang.isofficial = true and
percentage > (select max(percentage) from countrylanguage lang where lang.countrycode = 'che' and lang.isofficial = true);

# 7.
select co.name , language
from countrylanguage lang join country co
on lang.countrycode = co.code
where co.name = 'south korea' and lang.isofficial = true;

# 8.
select co.name, co.code ,count(city.countrycode) as 도시개수
from country co, city
where co.code = city.countrycode and co.name like 'bo%'
group by co.code;

# 9.
select co.name, co.code , if(city.countrycode is null , '단독',count(city.countrycode) )as 도시개수
from country co left outer join city
on co.code = city.countrycode 
where co.name like 'bo%'
group by co.code;

# 10.
select countrycode, name, population
from city
where population = (select max(population) from city);

# 11.
select country.name, countrycode, city.name, city.population
from city join country
on city.countrycode = country.code
where city.population = (select min(population) from city);

# 12.
select countrycode, name, population
from city
where population > (select city.population from city where countrycode = 'KOR' and name ='seoul');

# 13.
select city.countrycode, language
from city join countrylanguage lang
on city.countrycode = lang.countrycode
where city.name = 'san miguel' and lang.isofficial = true
order by city.countrycode;

# 14.
select countrycode, max(city.population) max_pop
from country co join city
on co.code = city.countrycode
group by countrycode
order by countrycode;

# 15.
select countrycode, city.name, max(city.population) population
from country co join city
on co.code = city.countrycode
group by countrycode
order by countrycode;

# 16.
select countrycode, co.name, city.name, max(city.population) population
from country co left outer join city
on co.code = city.countrycode
group by co.code
order by co.code;

# 17.
create view summary as
select countrycode, co.name as co_name, city.name as ci_name, max(city.population) population
from country co left outer join city
on co.code = city.countrycode
group by co.code
order by co.code;

# 18.
select *
from summary
where code = 'KOR';

