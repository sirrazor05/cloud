# Table of Contents

### What is the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN? <a name="join"></a>

- **INNER JOIN**: returns only matching rows in both tables
- **LEFT JOIN**: all rows from the left table + matched rows from the right
- **RIGHT JOIN**: all rows from the right + matched rows from the left
- **FULL JOIN**: all rows from both tables (matched and unmatched)

```sql
SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.id;
```

### What is the difference between WHERE and HAVING?? <a name="where_having"></a>

- **WHERE**: filters rows before aggregation
- **HAVING**: filters after aggregation (used with GROUP BY)

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

### What is the difference between UNION and UNION ALL? <a name="union_vs_union_all"></a>

- **UNION**: removes duplicates
- **UNION ALL**: includes all rows, including duplicates

### What are indexes and why are they used? <a name="index"></a>

Indexes speed up SELECT queries by allowing fast lookup of rows. But they slow down inserts/updates and use extra storage.

```sql
CREATE INDEX idx_user_email ON users(email);
```

### What is Normalization? <a name="normalization"></a>

Normalization is the process of organizing data in a database to:
- Eliminate data redundancy
- Ensure data integrity
- Make updates/inserts/deletes more efficient and consistent

It's done in stages called normal forms: 1NF → 2NF → 3NF (most common in practice)

#### First Normal Form (1NF)
**Rule**: No repeating groups or arrays in a row — each column must hold atomic values (indivisible).
#### Second Normal Form (2NF)
**Rule**: Must be in 1NF and have no partial dependencies.

What is a Partial Dependency?
- A partial dependency means a column depends on part of a composite primary key, not the whole key.
#### Third Normal Form (3NF) <a name="2NF"></a>
**Rule**: Must be in 2NF and have no transitive dependencies.

What is a Transitive Dependency?
- A transitive dependency means non-key columns depend on other non-key columns, not directly on the primary key.

### What Is a Cumulative Sum in SQL? <a name="cumulativesum"></a>

A cumulative sum (also called a running total) is a sum that grows row by row. For each row, it adds the current value to the sum of all previous values in a defined order.

This is done using the SUM() window function with OVER(ORDER BY ...).

Example Table: sales

| id| customer | amount | sale_date  |
|---|----------|--------|------------|
|1	 | Alice    | 100    | 2 023-01-01|
|2	 | Alice    | 200    | 2023-01-02 |
|3	 | Alice    | 150    | 2023-01-03 |

SQL Query for Cumulative Sum:

```sql
SELECT
  customer,
  sale_date,
  amount,
  SUM(amount) OVER (PARTITION BY customer ORDER BY sale_date) AS running_total
FROM sales;
```
Result:

| customer | sale_date  | amount | running_total  |
|----------|------------|--------|----------------|
| Alice    | 2023-01-01 | 100    | 100            |
| Alice    | 2023-01-02 | 200    | 300            |
| Alice    | 2023-01-03 | 150    | 450            |

Explanation:

- SUM(amount) is the aggregate function.
- OVER (...) turns it into a window function.
- PARTITION BY customer resets the sum for each customer. 
- ORDER BY sale_date ensures the sum adds up row-by-row in order.

### What Are Window Functions in SQL? <a name="windowfunction"></a>

Window functions perform calculations across a set of rows (the "window") that are related to the current row, without collapsing the result set like a GROUP BY does.

They are essential for analytics, ranking, running totals, and comparisons within partitions of data.

Syntax Overview
```sql
function_name(...) OVER (
    [PARTITION BY column]
    [ORDER BY column]
    [ROWS or RANGE clause]
)
```

You can use just OVER(), or add PARTITION BY and ORDER BY for more control.

Common Window Functions:
- `ROW_NUMBER()` – Assigns a unique row number per partition
- `RANK()` – Ranks rows with gaps for ties
- `DENSE_RANK()` – Like RANK() but no gaps
- `LAG()` / `LEAD()` – Access previous/next row values
- `SUM()`, `AVG()` – Running totals or averages
- `FIRST_VALUE()` – First value in the window
- `LAST_VALUE()` – Last value in the window
- `NTILE(n)` – Splits data into n equal groups (buckets)

###  What is a CTE (Common Table Expression)? <a name="cte"></a>

A **CTE** is a temporary named result set (similar to a temporary table) defined within a SQL query using the `WITH` clause. It improves query readability and helps organize complex queries by breaking them into simpler parts.

**Use cases:**

- Simplifying complex joins or subqueries
- Writing recursive queries (e.g., hierarchical data)
- Reusing the same result set multiple times within a query

**Example:**

```sql
WITH Sales_CTE AS (
  SELECT customer_id, SUM(amount) AS total_sales
  FROM sales
  GROUP BY customer_id
)
SELECT * FROM Sales_CTE
WHERE total_sales > 1000;
```

###  What is a Window Function? How Does It Differ from Aggregate Functions?<a name="window-function-summary"></a>

**Window functions** perform calculations across a set of rows related to the current row **without collapsing** the result set. They return a value **for each row**.

**Aggregate functions** (like `SUM()`, `AVG()`) summarize data by **grouping** rows and return a **single result per group**.

**Key Difference:**

- **Aggregate functions**: Reduce rows (grouping)
- **Window functions**: Keep rows intact, add calculated values (e.g., running totals, rankings)

###  Explain EXISTS vs IN — When Is Each Better?<a name="exists-vs-in"></a>

- **IN** checks if a value matches any value in a list or subquery results.
    - Good for small lists or subqueries returning few values.
    - Example:
      ```sql
      SELECT * FROM users WHERE id IN (1, 2, 3);
      ```

- **EXISTS** checks if a subquery returns any row (true/false). It stops processing once it finds the first match, making it efficient for correlated subqueries.
    - Better when the subquery is correlated or large because it can short-circuit.
    - Example:
      ```sql
      SELECT * FROM users u WHERE EXISTS (
        SELECT 1 FROM orders o WHERE o.user_id = u.id
      );
      ```

**Summary:**
- Use **IN** when you have a fixed or small list of values.
- Use **EXISTS** for subqueries that check for presence or in correlated queries for better performance.

###  What is a Materialized View and How Is It Different from a Regular View? <a name="materialized-view"></a>

- A **regular view** is a virtual table defined by a query; it doesn’t store data itself. When you query a view, the underlying SQL runs each time.
- A **materialized view** stores the result set physically (like a cached table). It improves performance for complex or expensive queries by storing the computed data.
- Materialized views need to be refreshed to update their data, either manually or on a schedule.

**Summary:**

| Aspect        | Regular View            | Materialized View          |
|---------------|------------------------|---------------------------|
| Data Storage  | No (virtual)           | Yes (stored)              |
| Query Speed   | Slower (runs each time) | Faster (precomputed data) |
| Maintenance   | Always up-to-date       | Needs refresh             |
