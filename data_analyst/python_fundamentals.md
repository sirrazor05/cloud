# 1. Why is Python popular for data analysis?

### Rich Data Processing
Libraries like Pandas, NumPy, and SciPy provide powerful tools for manipulating datasets.
### Easy Data Visualization
Libraries like Matplotlib and Seaborn help create insightful charts and graphs.
### Machine Learning & AI Integration
Python supports ML frameworks like Scikit-learn, TensorFlow, and PyTorch.
### Automation & Scripting
Python helps automate data cleaning, processing, and reporting tasks.
### Big Data Compatibility
Works well with Hadoop, Spark (PySpark), and cloud-based data storage.
### SQL & API Support
Python can connect to databases and extract data using SQL queries or APIs.

# 2. Explain the difference between lists, tuples, and sets.

Python provides different data structures to store collections of data:
- **Lists** are mutable and ordered.
- **Tuples** are immutable and ordered.
- **Sets** are unordered and contain unique elements.

## Comparison Table

| Feature      | **List** 📝 | **Tuple** 🔗 | **Set** 🔀 |
|-------------|------------|-------------|------------|
| **Definition** | Ordered, mutable collection | Ordered, immutable collection | Unordered, mutable collection with unique elements |
| **Syntax** | `my_list = [1, 2, 3]` | `my_tuple = (1, 2, 3)` | `my_set = {1, 2, 3}` |
| **Mutability** | ✅ Mutable (Can change) | ❌ Immutable (Cannot change) | ✅ Mutable (Can add/remove, but elements must be unique) |
| **Duplicates Allowed?** | ✅ Yes | ✅ Yes | ❌ No (Duplicates removed automatically) |
| **Indexing & Slicing** | ✅ Yes (`list[0]`) | ✅ Yes (`tuple[0]`) | ❌ No (Unordered, no indexing) |
| **Performance** | 🔴 Slower (More memory, flexible) | 🟢 Faster (Less memory, fixed) | 🟢 Fast lookups, but unordered |
| **Use Case** | When you need a **modifiable** sequence | When you need a **constant** sequence | When you need **unique** values & fast lookups |

## Examples

```python
# List Example
my_list = [1, 2, 3, 3]
my_list.append(4)  # ✅ Allowed
my_list[0] = 99    # ✅ Allowed
print(my_list)  # Output: [99, 2, 3, 3, 4]

# Tuple Example
my_tuple = (1, 2, 3, 3)
# my_tuple[0] = 99  ❌ TypeError: 'tuple' object does not support item assignment
print(my_tuple)  # Output: (1, 2, 3, 3)

# Set Example
my_set = {1, 2, 3, 3}
my_set.add(4)  # ✅ Allowed
print(my_set)  # Output: {1, 2, 3, 4} (No duplicates!)
```

# 3. How does Python handle memory management and garbage collection?
# 4. What is the difference between deep copy and shallow copy?
# 5. How do you handle exceptions in Python?
# 6. What are Python decorators, and how do they work?
