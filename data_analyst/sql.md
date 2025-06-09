# Table of Contents

- [What is the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN?](#join)
- [What is a CROSS JOIN?](#cross-join)
- [What Is a Foreign Key Constraint and How Does It Ensure Referential Integrity?](#foreign-key)
- [What Is a Surrogate Key vs Natural Key? When Would You Use Each?](#surrogate-vs-natural-key)
- [What is the difference between a primary key and a unique key?](#primary-vs-unique-key)
- [Explain data type choices — when would you use INT vs BIGINT vs UUID as a primary key?](#primary-key-types)
- [When to use composite primary keys?](#composite-primary-keys)
- [What is Normalization?](#normalization)
- [What Are the Trade-offs Between Normalized and Denormalized Database Schemas?](#normalization-tradeoffs)
- [What are indexes and why are they used?](#index)
- [Explain the Difference Between Clustered and Non-Clustered Indexes. When Would You Use Each?](#clustered-vs-nonclustered)
- [How Does Indexing Work Under the Hood?](#indexing)
- [Explain the Concept of a Covering Index.](#covering-index)
- [What is the difference between WHERE and HAVING?](#where_having)
- [What is the difference between UNION and UNION ALL?](#union_vs_union_all)
- [Explain the Difference Between DELETE, TRUNCATE, and DROP.](#delete-truncate-drop)
- [Explain EXISTS vs IN — When Is Each Better?](#exists-vs-in)
- [What Is a Cumulative Sum in SQL?](#cumulativesum)
- [What Are Window Functions in SQL?](#windowfunction)
- [What is a Window Function? How Does It Differ from Aggregate Functions?](#window-function-summary)
- [What is a CTE (Common Table Expression)?](#cte)
- [How do you find duplicates in a table?](#duplicates)
- [What is a Materialized View and How Is It Different from a Regular View?](#materialized-view)
- [Explain ACID Properties in SQL Databases](#acid-properties)
- [How Do You Handle Performance Optimization in SQL?](#performance-optimization)
- [How Do You Implement and Optimize Time-Series Data Queries?](#time-series-queries)
- [How Do You Implement Slowly Changing Dimensions (SCD) in SQL?](#scd-implementation)
- [Explain Row-Level Security (RLS) in Modern SQL Databases](#row-level-security)
- [How Do You Handle Data Versioning or Audit Trails in SQL?](#data-versioning)
- [What Is the Difference Between Horizontal and Vertical Partitioning?](#partitioning)
- [How Do You Optimize Queries Involving Large JOIN Operations?](#join-optimization)
- [What Are Common Causes of Slow Queries and How Do You Troubleshoot Them?](#slow-query-causes)
- [What Is the Difference Between a Database View and a Materialized View?](#view-vs-materialized-view)
- [What Are Some Common Pitfalls with NULL Values in SQL?](#null-pitfalls)
- [How Would You Optimize a Query That Has Multiple OR Conditions?](#or-condition-optimization)
- [How Do You Implement Pagination in SQL Queries Efficiently?](#pagination)
- [Scalar Function vs Table-Valued Function](#scalar-vs-table-func)
- [Explain how transactions work in SQL. What commands are used?](#transactions)
- [SQL Triggers — Overview, Types, and Examples](#sql-triggers)


### What is the difference between INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN? <a name="join"></a>

- **INNER JOIN**: returns only matching rows in both tables
- **LEFT JOIN**: all rows from the left table + matched rows from the right
- **RIGHT JOIN**: all rows from the right + matched rows from the left
- **FULL JOIN**: all rows from both tables (matched and unmatched)

```sql
SELECT * FROM orders INNER JOIN customers ON orders.customer_id = customers.id;
```

### What is a CROSS JOIN? <a name="cross-join"></a>

A CROSS JOIN returns the Cartesian product of two tables — meaning every row from the first table is combined with every row from the second.

- If Table A has 3 rows and Table B has 4 rows, the result will have 3 × 4 = 12 rows.
- It does not require any ON condition.

### What Is a Foreign Key Constraint and How Does It Ensure Referential Integrity?<a name="foreign-key"></a>

A foreign key ensures that a value in one table matches a primary key in another, preventing invalid references and maintaining consistent relationships between tables.

###  What Is a Surrogate Key vs Natural Key? When Would You Use Each?<a name="surrogate-vs-natural-key"></a>

- **Natural key:** Uses real-world data (like email) as the primary key.
- **Surrogate key:** Artificial key (e.g., auto-increment ID) with no business meaning.

Surrogate keys are preferred for flexibility and stability.

### What is the difference between a primary key and a unique key? <a name="primary-vs-unique-key"></a>

- Primary key: Uniquely identifies each row; cannot be NULL; one per table.
- Unique key: Ensures uniqueness of values but can allow one NULL (depending on DB).

### Explain data type choices — when would you use INT vs BIGINT vs UUID as a primary key? <a name="primary-key-types"></a>

- INT: Small tables
- BIGINT: Large tables
- UUID: Decentralized inserts (e.g., distributed systems)

### When to use composite primary keys? <a name="composite-primary-keys"></a>

- When the combination of two or more columns uniquely identifies a row.
- Often in many-to-many or versioning tables.

### What is Normalization? <a name="normalization"></a>

Normalization is the process of organizing data in a database to:
- Eliminate data redundancy
- Ensure data integrity
- Make updates/inserts/deletes more efficient and consistent

It's done in stages called normal forms: 1NF → 2NF → 3NF (most common in practice)

###  What Are the Trade-offs Between Normalized and Denormalized Database Schemas? <a name="normalization-tradeoffs"></a>

- **Normalized:** Reduces redundancy, easier to maintain, but may require complex joins.
- **Denormalized:** Improves read performance and simplicity but increases redundancy and update anomalies.

### What are indexes and why are they used? <a name="index"></a>

Indexes speed up SELECT queries by allowing fast lookup of rows. But they slow down inserts/updates and use extra storage.

```sql
CREATE INDEX idx_user_email ON users(email);
```

### Explain the Difference Between Clustered and Non-Clustered Indexes. When Would You Use Each? <a name="clustered-vs-nonclustered"></a>

- **Clustered Index:** The table’s data is physically sorted according to this index (one per table). Efficient for range queries.
- **Non-clustered Index:** Separate structure pointing to data rows. Multiple non-clustered indexes can exist per table. Good for quick lookups on columns.

Use clustered for primary keys or frequently range-queried columns, non-clustered for others.

### How Does Indexing Work Under the Hood? <a name="indexing"></a>

- An **index** is a data structure (usually a B-tree or sometimes a hash) that the database uses to quickly locate rows without scanning the entire table.
- It stores keys (indexed column values) along with pointers to the corresponding data rows.
- When you run a query with a `WHERE` clause on an indexed column, the database uses the index to jump directly to matching rows, drastically reducing search time.
- Indexes speed up read operations but can slow down write operations (`INSERT`, `UPDATE`, `DELETE`) because the index also needs updating.

### Explain the Concept of a Covering Index. <a name="covering-index"></a>

An index that contains all the columns a query needs, so the database can fulfill the query using only the index without accessing the table data.

### What is the difference between WHERE and HAVING? <a name="where_having"></a>

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
### What is the difference between UNION and UNION ALL? <a name="union_vs_union_all"></a>

- **UNION**: removes duplicates
- **UNION ALL**: includes all rows, including duplicates

### Explain the Difference Between DELETE, TRUNCATE, and DROP.<a name="delete-truncate-drop"></a>

- **DELETE:** Removes rows based on a condition; can be rolled back; slower due to logging.
- **TRUNCATE:** Removes all rows quickly by deallocating pages; cannot use WHERE; minimal logging.
- **DROP:** Deletes entire table schema and data.

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

###  What is a Window Function? How Does It Differ from Aggregate Functions?<a name="window-function-summary"></a>

**Window functions** perform calculations across a set of rows related to the current row **without collapsing** the result set. They return a value **for each row**.

**Aggregate functions** (like `SUM()`, `AVG()`) summarize data by **grouping** rows and return a **single result per group**.

**Key Difference:**

- **Aggregate functions**: Reduce rows (grouping)
- **Window functions**: Keep rows intact, add calculated values (e.g., running totals, rankings)

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

### How do you find duplicates in a table? <a name="duplicates"></a>

```sql
SELECT name, COUNT(*) 
FROM users 
GROUP BY name 
HAVING COUNT(*) > 1;
```

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

### How Do You Implement Slowly Changing Dimensions (SCD) in SQL?<a name="scd-implementation"></a>

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

### How Do You Optimize Queries Involving Large JOIN Operations?<a name="join-optimization"></a>

- Ensure join columns are indexed.
- Use appropriate join types (`INNER` vs `LEFT`).
- Filter rows before join when possible.
- Avoid joining large datasets unnecessarily.
- Analyze execution plans to identify bottlenecks.

### What Are Common Causes of Slow Queries and How Do You Troubleshoot Them? <a name="slow-query-causes"></a>

- Missing indexes.
- Large data scans/full table scans.
- Poorly written queries (e.g., unnecessary joins, subqueries).
- Locking/blocking.
- Network latency.

Use profiling tools, logs, and execution plans to diagnose.

### What Is the Difference Between a Database View and a Materialized View?<a name="view-vs-materialized-view"></a>

- **View:** A virtual table based on a query. Data is not stored, calculated on access.
- **Materialized view:** Stores the result physically. Faster reads but needs refresh to stay current.

### What Are Some Common Pitfalls with NULL Values in SQL?<a name="null-pitfalls"></a>

- Comparisons with NULL always return UNKNOWN, so use `IS NULL` instead of `= NULL`.
- Aggregate functions ignore NULLs.
- NULLs can cause unexpected results in joins or conditions.

### How Would You Optimize a Query That Has Multiple OR Conditions? <a name="or-condition-optimization"></a>

- Rewrite using `UNION` or `UNION ALL` if appropriate.
- Use indexes on the involved columns.
- Sometimes split the query into separate queries and combine results.

### How Do You Implement Pagination in SQL Queries Efficiently? <a name="pagination"></a>

Use `LIMIT` and `OFFSET` in MySQL/PostgreSQL or `ROW_NUMBER()` in SQL Server with a filter on row number. For large offsets, keyset pagination (using a `WHERE` clause with last seen value) is more efficient.

**Example with ROW_NUMBER():**

```sql
WITH Ordered AS (
  SELECT *, ROW_NUMBER() OVER (ORDER BY id) AS rn FROM table
)
SELECT * FROM Ordered WHERE rn BETWEEN 11 AND 20;
```

### Scalar Function vs Table-Valued Function <a name="scalar-vs-table-func"></a>

- **Scalar function:** Returns a single value.
- **Table-valued function:** Returns a table (result set) that can be queried like a regular table.

### Explain how transactions work in SQL. What commands are used?<a name="transactions"></a>

Transactions group SQL statements into a single unit of work. Commands:

- BEGIN or START TRANSACTION to start.
- COMMIT to save changes.
- ROLLBACK to undo changes.

### SQL Triggers — Overview, Types, and Examples<a name="sql-triggers"></a>

🔹 What is a Trigger in SQL?

A trigger is a special stored procedure that automatically runs when certain events occur in a database, like:

- INSERT
- UPDATE
- DELETE

It’s attached to a table or view, and is typically used to enforce business rules, audit changes, or maintain integrity.

🔹 Types of Triggers

- BEFORE Trigger: Fires before the operation is executed. Often used to validate or modify input.
- AFTER Trigger: Fires after the operation is completed. Useful for auditing or logging.
- INSTEAD OF Trigger (mostly for views): Replaces the operation with custom logic.

🔹 Trigger Events

You can define triggers for:

- BEFORE INSERT
- AFTER INSERT
- BEFORE UPDATE
- AFTER UPDATE
- BEFORE DELETE
- AFTER DELETE

When to Use Triggers

✅ Use for:

- Auditing (e.g., logging changes)
- Enforcing complex validation rules
- Syncing derived tables

🚫 Avoid when:

- Logic gets too complex (can hurt readability and debugging)
- Performance matters (triggers add overhead)

###  What is a query execution plan? <a name="query-execution-plan"></a>

It shows how SQL engine executes a query—e.g., join types, scan types, index usage. Use it to identify performance bottlenecks.

###  PARTITION BY vs GROUP BY <a name="partition-vs-group"></a>

- GROUP BY reduces rows.
- PARTITION BY retains rows but groups them logically for window functions.

