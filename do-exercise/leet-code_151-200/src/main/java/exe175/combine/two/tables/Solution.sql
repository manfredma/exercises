# Write your MySQL query statement below
SELECT FirstName, LastName, City, State
FROM Person LEFT JOIN Address USING(PersonId);

select p.FirstName, p.LastName, a.City, a.State from Person p left join Address a on p.PersonId = a.PersonId;