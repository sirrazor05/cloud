# Table of Contents

### What is the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN? <a name="join"></a>

- **INNER JOIN**: returns only matching rows in both tables
- **LEFT JOIN**: all rows from the left table + matched rows from the right
- **RIGHT JOIN**: all rows from the right + matched rows from the left
- **FULL JOIN**: all rows from both tables (matched and unmatched)

#### Syntax

```sql
SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.id;
```

### What is the difference between WHERE and HAVING?? <a name="where_having"></a>

- **WHERE**: filters rows before aggregation
- **HAVING**: filters after aggregation (used with GROUP BY)

#### Syntax

```sql
-- WHERE
SELECT * FROM users WHERE age > 18;

-- HAVING
SELECT department, COUNT(*) 
FROM employees 
GROUP BY department 
HAVING COUNT(*) > 10;
```
### How do you find duplicates in a table? <a name="duplicates"></a>

```sql
SELECT name, COUNT(*) 
FROM users 
GROUP BY name 
HAVING COUNT(*) > 1;
```