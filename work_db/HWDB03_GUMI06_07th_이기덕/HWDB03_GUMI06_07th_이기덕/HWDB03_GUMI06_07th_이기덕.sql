use empdept;

# 1.
select ename,job,sal
from emp, dept
where emp.deptno = dept.deptno and dept.loc = 'chicago';

# 2.
select empno, ename, job, deptno
from emp
where empno not in ( select empno from emp where empno in (select mgr from emp) );

# 3.
select ename, job, mgr
from emp
where mgr = (select mgr from emp where ename = 'blake') and ename != 'blake';

# 4.
select *
from emp
order by hiredate
limit 5;

# 5.
select ename, job, dname
from emp join dept
where mgr = (select empno from emp where ename = 'jones')
group by empno;

