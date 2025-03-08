# Table of Contents

1. [Why is Python popular for data analysis?](#popular)
2. [Lists in Python](#lists)
3. [Tuples in Python](#tuples)
4. [Sets in Python](#sets)
5. [Dictionary ({key: value}) in Python](#maps)
6. [Deque in Python](#deque)

# 1. Why is Python popular for data analysis? <a name="popular"></a>

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

# 2. Lists in Python <a name="lists"></a>

### Definition

A list is a mutable, ordered collection of elements in Python.
It can store multiple items in a single variable and allows duplicates. 
Lists are one of the most commonly used data structures because of their flexibility and ease of use.

### Key properties
- **Ordered:** Elements maintain the order in which they were added.
- **Mutable:** You can change the content of a list (add, remove, or modify elements).
- **Allows Duplicates:** Lists can contain multiple occurrences of the same value.

### Syntax
```python
my_list = [1, 2, 3, 4, 5]
```
### Basic List Operations
```python
# Create a list
my_list = [10, 20, 30, 40, 50]

# Accessing elements by index
print(my_list[0])  # Output: 10
print(my_list[-1]) # Output: 50 (Last element)

# Slicing the list
print(my_list[1:4])  # Output: [20, 30, 40] (elements from index 1 to 3)
# Slicing from the beginning to index 2
print(my_list[:2])   # Output: [1, 2]

# Modifying an element
my_list[2] = 99  # [10, 20, 99, 40, 50]
print(my_list)

# Adding an element to the list
my_list.append(60)  # [10, 20, 99, 40, 50, 60]
print(my_list)

# Inserting an element at a specific position
my_list.insert(2, 25)  # [10, 20, 25, 99, 40, 50, 60]
print(my_list)

# Removing an element
my_list.remove(40)  # [10, 20, 25, 99, 50, 60]
print(my_list)

# Popping an element (removes and returns it)
popped_value = my_list.pop()  # Removes and returns last element
print(popped_value)  # Output: 60
print(my_list)  # [10, 20, 25, 99, 50]

# List with mixed data types
mixed_list = [1, "apple", 3.14, True]

# Printing the list
print(mixed_list)  # Output: [1, 'apple', 3.14, True]

```
### Use Cases for Lists
#### 1. Storing a Collection of Data
Lists are ideal when you need an **ordered collection** of items, such as names, numbers, or other types of data.
The order in which data is added to the list is preserved.
#### 2. Dynamic data
Lists allow you to add, remove, and change elements during the execution of your program.
This makes them suitable when the data changes over time, such as user inputs or fetched data.
#### 3. Manipulating Data
Lists support a wide range of operations like appending, sorting, and modifying items,
which makes them useful for applications that need to perform these operations frequently.
#### 4. Accessing Data by Index
Since lists are ordered, you can access any item using its index,
which is helpful when you need to work with specific positions in the data.

# 3. Tuple in Python <a name="tuples"></a>

### Definition
A tuple is an immutable, ordered collection of elements in Python.
Once a tuple is created, it cannot be modified (i.e., no adding, removing, or changing elements).
Tuples can store multiple items of different types and allow duplicates.

### Key properties
- **Ordered:** Tuples maintain the order of elements.
- **Immutable:** Once created, you cannot change the tuple’s content. This means you cannot add, remove, or modify elements.
- **Allows Duplicates:** Like lists, tuples can contain multiple occurrences of the same value.
- **Faster than Lists:** Due to their immutability, tuples are generally faster and use less memory than lists.
### Syntax
```python
my_tuple = (1, 2, 3, 4, 5)
```
### Basic Operations
```python
# Create a tuple
my_tuple = (10, 20, 30, 40, 50)

# Accessing elements by index
print(my_tuple[0])   # Output: 10 (first element)
print(my_tuple[-1])  # Output: 50 (last element)

# Slicing the tuple
print(my_tuple[1:4])  # Output: (20, 30, 40)

# Concatenation of tuples
new_tuple = my_tuple + (60, 70)
print(new_tuple)  # Output: (10, 20, 30, 40, 50, 60, 70)

# Repeating a tuple
repeated_tuple = my_tuple * 2
print(repeated_tuple)  # Output: (10, 20, 30, 40, 50, 10, 20, 30, 40, 50)

# Nested tuple
nested_tuple = (1, 2, (3, 4), 5)

# Accessing nested tuple
print(nested_tuple[2])   # Output: (3, 4)
print(nested_tuple[2][1]) # Output: 4 (second element of the nested tuple)

# Tuple with mixed data types
mixed_tuple = (1, "apple", 3.14, True)

print(mixed_tuple)  # Output: (1, 'apple', 3.14, True)

# Trying to modify an element of the tuple (this will raise an error)
my_tuple[1] = 100  # Invalid operation: 'tuple' object does not support item assignment

```
### Use Cases for Tuples
#### 1. Fixed Data
Since tuples are immutable, they are ideal for storing data that should not change.
For example, you can store configuration settings or constants as tuples.

**Example:** A coordinate (latitude, longitude) pair that doesn't change.
#### 2. Return Multiple Values
Tuples are often used to return multiple values from a function.

**Example:** Returning the quotient and remainder from a division.
#### 3. Memory Efficiency
Tuples are more memory-efficient than lists because they are immutable,
making them a good choice when you don't need to modify the data.
#### 4. Key in Dictionaries
Because tuples are immutable, they can be used as keys in dictionaries.
Lists cannot be used as dictionary keys, but tuples can.

**Example:** Using a tuple of coordinates as a dictionary key.

# 4. Sets in Python <a name="sets"></a>
A set is an unordered collection of unique elements. 
Unlike lists and tuples, sets do not maintain the order of the elements, and they automatically remove duplicates.

### Key properties
- **Unordered:** The elements of a set are not stored in any particular order, which means they do not support indexing, slicing, or other sequence-like behavior.
- **Unique Elements:** Sets automatically remove duplicate values, ensuring that each element appears only once.
- **Mutable:** Sets are mutable, meaning you can add or remove elements after the set is created.
- **No Indexing:** Since sets are unordered, you cannot access elements by index or position.
### Syntax
```python
my_set = {1, 2, 3, 4, 5}
# or 
my_set = set([1, 2, 3, 4, 5])
```
### Basic Operations
```python
# Creating a set
my_set = {10, 20, 30, 40, 50}
print(my_set)  # Output: {10, 20, 30, 40, 50} (The order is not guaranteed and can vary each time)

# Adding multiple elements
my_set.update([70, 80, 90])
print(my_set)  # Output: {10, 20, 30, 40, 50, 60, 70, 80, 90}

# Removing an element (raises error if element doesn't exist)
my_set.remove(20)
print(my_set)  # Output: {10, 30, 40, 50, 60, 70, 80, 90}

# Removing an element (does not raise error if element doesn't exist)
my_set.discard(100)
print(my_set)  # Output: {10, 30, 40, 50, 60, 70, 80, 90}

# Removing and returning an arbitrary element
removed_item = my_set.pop()

# Union (combining elements from two sets)
set_a = {1, 2, 3}
set_b = {3, 4, 5}
union_set = set_a | set_b  # Or use set_a.union(set_b)
print(union_set)  # Output: {1, 2, 3, 4, 5}

# Intersection (common elements between two sets)
intersection_set = set_a & set_b  # Or use set_a.intersection(set_b)
print(intersection_set)  # Output: {3}

# Difference (elements in set_a but not in set_b) 
difference_set = set_a - set_b  # Or use set_a.difference(set_b)
print(difference_set)  # Output: {1, 2}

# Symmetric Difference (elements in either set but not both)
symmetric_diff_set = set_a ^ set_b  # Or use set_a.symmetric_difference(set_b)
print(symmetric_diff_set)  # Output: {1, 2, 4, 5}
```
### Use Cases for Sets
#### 1. Storing Unique Data
Since sets automatically remove duplicates, they are great for storing unique data, such as unique IDs or names.
```python
emails = {"alice@example.com", "bob@example.com", "alice@example.com"}
print(emails)  # Output: {'alice@example.com', 'bob@example.com'}
```
#### 2. Mathematical Set Operations
Sets are perfect when you need to perform set operations like union, intersection, and difference (e.g., finding common elements between two sets).

```python
# Finding common tags between two users' posts.
user_1_tags = {"python", "data", "analysis"}
user_2_tags = {"data", "python", "AI"}
common_tags = user_1_tags & user_2_tags  # Intersection
print(common_tags)  # Output: {'python', 'data'}
```
#### 3. Efficient lookups
Checking if an element is contained in a set is fast (constant time) because sets use hash-based implementation.

```python
user_ids = {101, 102, 103, 104}
print(103 in user_ids)  # Output: True
print(105 in user_ids)  # Output: False
```
#### 4. Removing Duplicates from a List
You can use a set to automatically remove duplicate items from a list.

```python
numbers = [1, 2, 2, 3, 4, 4, 5]
unique_numbers = set(numbers)
print(unique_numbers)  # Output: {1, 2, 3, 4, 5}
```

**Example:** Using a tuple of coordinates as a dictionary key.

# 5. Dictionary ({key: value}) in Python <a name="maps"></a>
A dictionary in Python is an unordered collection of key-value pairs.
Each key in a dictionary is unique, and it is associated with a specific value.
Dictionaries are extremely useful for representing and working with data where each element is identified by a unique key.

### Key properties
- **Key-Value Pair:** A dictionary is composed of keys and values. Each key maps to a value, and the key must be unique.
- **Unordered:** Dictionaries are unordered collections This means that the order in which elements are inserted is not guaranteed to be preserved when accessing or iterating over them.
- **Mutable:** Dictionaries are mutable, meaning you can add, remove, or modify elements in them after they are created.
- **Keys must be immutable:** The keys in a dictionary must be immutable types such as strings, numbers, or tuples, while values can be of any type (even other dictionaries).
- **Efficient lookups:** Dictionaries provide fast access (constant time) to values using keys due to hashing. 

# 6. Deque in Python <a name="deque"></a>