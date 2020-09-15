# Write your MySQL query statement below
select  max(e.Salary) as SecondHighestSalary  from Employee e where e.Salary < (select max(e2.Salary) from Employee e2);