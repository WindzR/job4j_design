/*  В запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека. */
SELECT p.id, p.name, c.name FROM person AS p
INNER JOIN company AS c
ON p.company_id = c.id
WHERE p.company_id != 5;

/*  Необходимо выбрать название компании с максимальным количеством человек + количество человек в этой компании */
SELECT c.name, COUNT(p.company_id) AS persons FROM company AS c
INNER JOIN person AS p
ON c.id = p.company_id
GROUP BY c.name
ORDER BY COUNT(p.company_id) DESC
LIMIT 1;