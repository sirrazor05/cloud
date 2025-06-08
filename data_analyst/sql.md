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

### Explain ACID Properties in SQL Databases <a name="acid-properties"></a>

ACID is a set of properties that guarantee reliable processing of database transactions:

- **Atomicity:**  
  A transaction is “all or nothing.” If any part fails, the entire transaction is rolled back.

- **Consistency:**  
  Transactions take the database from one valid state to another, maintaining all defined rules (constraints, triggers).

- **Isolation:**  
  Concurrent transactions don’t interfere. Intermediate states are hidden until a transaction completes.

- **Durability:**  
  Once a transaction commits, changes are permanent, even if the system crashes.

### How Does Indexing Work Under the Hood? <a name="indexing"></a>

- An **index** is a data structure (usually a B-tree or sometimes a hash) that the database uses to quickly locate rows without scanning the entire table.
- It stores keys (indexed column values) along with pointers to the corresponding data rows.
- When you run a query with a `WHERE` clause on an indexed column, the database uses the index to jump directly to matching rows, drastically reducing search time.
- Indexes speed up read operations but can slow down write operations (`INSERT`, `UPDATE`, `DELETE`) because the index also needs updating.

### How Do You Handle Performance Optimization in SQL?<a name="performance-optimization"></a>

- **Use Indexes Wisely**
    - Create indexes on columns used frequently in `WHERE`, `JOIN`, `ORDER BY`, and `GROUP BY` clauses.
    - Avoid over-indexing since indexes slow down writes.

- **Analyze Query Execution Plans**
    - Use tools like `EXPLAIN` or `EXPLAIN ANALYZE` to understand how the database executes queries.
    - Look for full table scans, missing indexes, or inefficient joins.

- **Optimize Queries**
    - Select only needed columns instead of `SELECT *`.
    - Avoid unnecessary subqueries or complex joins.
    - Use `JOIN` instead of subqueries when appropriate.
    - Filter data early using `WHERE`.

- **Use Appropriate Joins**
    - Use the correct join types (`INNER`, `LEFT`, etc.) and join on indexed columns.

- **Limit Dataset Size**
    - Use `LIMIT` or pagination to avoid fetching large result sets unnecessarily.

- **Consider Denormalization (When Needed)**
    - For read-heavy workloads, sometimes duplicating data (denormalization) can speed up queries.

- **Use Caching**
    - Cache frequently accessed data at the application or database level.

- **Partition Large Tables**
    - Partitioning splits large tables into smaller chunks, improving query speed on subsets.

- **Avoid Functions on Indexed Columns**
    - Avoid wrapping indexed columns in functions in `WHERE` clauses, which prevents index use.

- **Regular Maintenance**
    - Run database maintenance tasks like `ANALYZE`, `VACUUM`, or rebuilding indexes to keep performance optimal.

### How Do You Implement and Optimize Time-Series Data Queries?<a name="time-series-queries"></a>

- Partition tables by time (e.g., by day, month).
- Create indexes on timestamp columns.
- Use window functions for running totals, moving averages.
- Downsample or aggregate data to reduce volume.
- In cloud warehouses, leverage clustering or partition pruning.

### How Do You Implement Slowly Changing Dimensions (SCD) in SQL?

<a name="scd-implementation"></a>

- **Type 1:** Overwrite old data.
- **Type 2:** Add new rows with versioning or effective dates to keep history.
- **Type 3:** Add new columns to track previous values.

**Example for Type 2:**  
Add `start_date`, `end_date`, and `is_current` columns to track versions.

```sql
-- Sample structure for Type 2 SCD
CREATE TABLE customer_scd (
  customer_id INT,
  name VARCHAR(100),
  start_date DATE,
  end_date DATE,
  is_current BOOLEAN
);

-- Insert new version when data changes
INSERT INTO customer_scd (customer_id, name, start_date, end_date, is_current)
VALUES (1, 'Alice', CURRENT_DATE, NULL, TRUE);

-- Update previous version's end_date and is_current
UPDATE customer_scd
SET end_date = CURRENT_DATE, is_current = FALSE
WHERE customer_id = 1 AND is_current = TRUE;
```

### Explain Row-Level Security (RLS) in Modern SQL Databases<a name="row-level-security"></a>

Row-Level Security (RLS) restricts access to rows based on user context or role by applying security policies at the database level. For example, PostgreSQL allows defining RLS policies so users can only see rows they own.

###  How Do You Handle Data Versioning or Audit Trails in SQL? <a name="data-versioning"></a>

- Use temporal tables or history tables to track changes.
- Implement triggers to log changes to audit tables.
- Use database features like system-versioned tables (SQL Server, Oracle).

### What Is the Difference Between Horizontal and Vertical Partitioning? <a name="partitioning"></a>

- **Horizontal partitioning:** Split rows into different tables/partitions based on a key (e.g., date ranges).
- **Vertical partitioning:** Split columns into separate tables, useful when some columns are large or infrequently accessed.

### Explain the Difference Between DELETE, TRUNCATE, and DROP.<a name="delete-truncate-drop"></a>

- **DELETE:** Removes rows based on a condition; can be rolled back; slower due to logging.
- **TRUNCATE:** Removes all rows quickly by deallocating pages; cannot use WHERE; minimal logging.
- **DROP:** Deletes entire table schema and data.

### What Is a Foreign Key Constraint and How Does It Ensure Referential Integrity?<a name="foreign-key"></a>

A foreign key ensures that a value in one table matches a primary key in another, preventing invalid references and maintaining consistent relationships between tables.

###  What Is a Surrogate Key vs Natural Key? When Would You Use Each?<a name="surrogate-vs-natural-key"></a>

- **Natural key:** Uses real-world data (like email) as the primary key.
- **Surrogate key:** Artificial key (e.g., auto-increment ID) with no business meaning.

Surrogate keys are preferred for flexibility and stability.

### How Do You Optimize Queries Involving Large JOIN Operations?<a name="join-optimization"></a>

- Ensure join columns are indexed.
- Use appropriate join types (`INNER` vs `LEFT`).
- Filter rows before join when possible.
- Avoid joining large datasets unnecessarily.
- Analyze execution plans to identify bottlenecks.

