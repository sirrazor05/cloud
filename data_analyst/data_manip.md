# Table of Contents

- [Handling Missing Data](#handlemissingdata)
- [Interpolation](#interpolation)
- [Scenario 1: Sales data](#scenario1)

# Handling Missing Data <a name="handlemissingdata"></a>

### Detecting Missing Data
Before handling missing data, it's important to identify missing values in the dataset.
```python
import pandas as pd
import numpy as np

# Creating a sample DataFrame with missing values
data = {
    "Product": ["A", "B", np.nan, "D"],
    "Price": [100, 200, 150, np.nan],
    "Quantity": [10, np.nan, 8, 5]
}
df = pd.DataFrame(data)
print(df)
```
Output:

| Product |Price| Quantity |
|---------|-----|----------|
| A       | 100 | 	10      |
| B       | 200 | 	NaN     |
| NaN     | 150 | 	8       |
| D       | NaN | 	5       |

```python
# Checking for Missing Values
print(df.isnull())
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| False   | False | 	False |
| False   | False | 	True  |
| True    | False | 	False |
| False   | True  | 	False |

```python
# Sum of Missing Values
print(df.isnull().sum())
```

Output:

| Column   | Missing Values |
|----------|----------------|
| Product  | 1              |
| Price    | 1              | 
| Quantity | 1              | 

### Replace with a Constant Value
```python
# Replace with a Constant Value
 df.fillna(0)
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100   | 10       |
| B       | 200   | 0        |
| 0       | 150   | 8        |
| D       | 0     | 5        |

### Replace with Column Mean
```python
df["Price"].fillna(df["Price"].mean(), inplace=True)
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100.0 | 10       |
| B       | 200.0 | NaN      |
| NaN     | 150.0 | 8        |
| D       | 150.0 | 5        |

### Replace with Column Median
The median is a statistical measure that represents the middle value in a sorted list of numbers.
If the list has an odd number of elements, the median is the middle element. 
If the list has an even number of elements, the median is the average of the two middle elements.
```python
df["Quantity"].fillna(df["Quantity"].median(), inplace=True)
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100.0 | 10       |
| B       | 200.0 | 8        |
| NaN     | 150.0 | 8        |
| D       | NaN   | 5        |

### Replace with Most Frequent Value (Mode)
The .mode() function in Pandas is used to return the mode of a dataset, which is the value that appears most frequently.
In simple terms, the mode is the most common value in a column.
When you use .mode(), it returns a Series of the most frequent values in the column. 
The reason .mode() returns a Series (even if there is only one mode) is because there can be multiple values that occur with the same highest frequency.
If there are multiple modes, all of them will be returned.

```python
# in our case all values appear 1 time so we take the first one A
df["Product"].fillna(df["Product"].mode()[0], inplace=True)
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100.0 | 10       |
| B       | 200.0 | NaN      |
| A       | 150.0 | 8        |
| D       | NaN   | 5        |

### Forward Fill (ffill)
In forward filling, the missing value is replaced by the most recent valid (non-null) value before it.
Essentially, you "carry forward" the last known value to fill the missing data.
```python
df.fillna(method="ffill", inplace=True)
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100.0 | 10       |
| B       | 200.0 | 10       |
| B       | 150.0 | 8        |
| D       | 150.0 | 5        |

### Backward Fill (bfill)
Unlike forward fill (ffill), in backward filling, the missing value is replaced by the next valid (non-null) value after it if exists.
Essentially, you "carry backward" the next known value to fill the missing data.

```python
df.fillna(method="bfill", inplace=True)
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100.0 | 10       |
| B       | 200.0 | 8        |
| D       | 150.0 | 8        |
| D       | NaN   | 5        |


### Interpolating Missing Values
The interpolate() method is a pandas function that is used to fill missing (NaN) values in a column by estimating them based on other available data points.
By default, interpolate() uses linear interpolation. 
This means that it will estimate the missing values by drawing a straight line between the known values before and after the missing value.
The missing value will be filled based on this straight line.

Input 

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100   | 10       |
| B       | 200   | NaN      |
| NaN     | 150   | 8        |
| D       | NaN   | 5        |
| E       | 75    | 3        |

The missing price value for D will be interpolated based on the surrounding valid values:

- Before row 4: Price = 150 (from row 3, NaN).
- After row 4: Price = 75 (from row 5, E).

```python
df["Price"] = df["Price"].interpolate()
```
Output:

| Product | Price | Quantity |
|---------|-------|----------|
| A       | 100   | 10       |
| B       | 200   | NaN      |
| NaN     | 150   | 8        |
| D       | 112.5 | 5        |
| E       | 75    | 3        |


# Types of interpolation <a name="interpolation"></a>

### Linear Interpolation
The missing values are estimated by drawing a straight line between the surrounding data points.
It's the most common type and works well for datasets where the values change steadily over time.
### Polynomial Interpolation
More complex than linear, this involves fitting a polynomial to the data. It’s used when the data follows a non-linear trend.
### Spline Interpolation
This uses a piecewise polynomial function (splines) to estimate the values. It's useful for smooth interpolation in datasets with curves.
### Time-based Interpolation
This is used specifically with time-series data, where interpolation occurs based on time intervals.

# Scenario 1: Sales data <a name="scenario1"></a>

### Definition

A company has sales data for various products across different regions. The data contains the following columns:

- **Product:** The name of the product sold.
- **Region:** The region where the product was sold.
- **Price:** The price of the product.
- **Quantity Sold:** The quantity of the product sold.
- **Date:** The date of the sale.

The company wants to:

- Read and clean the sales data.
- Calculate the total sales value (Price × Quantity Sold).
- Analyze the sales performance across different regions.
- Calculate the average and total sales per product.
- Identify the top-selling products based on total sales.

### Solution

#### Read and Load the Data

We’ll start by importing the necessary libraries, reading the data from a CSV file, and taking a quick look at the first few rows of the data.

```python
import pandas as pd

# Load the sales data into a DataFrame
df = pd.read_csv('sales_data.csv')

# Display the original DataFrame
print("Original DataFrame:")
print(df)
```
Example CSV Content (sales_data.csv):

| Product   | Region | Price | Quantity Sold | Date       |
|-----------|--------|-------|---------------|------------|
| Product A | East   | 100   | 20            | 2023-01-01 |
| Product B | West   | NaN   | 15            | NaN        |
| Product A | North  | 100   | 30            | 2023-01-03 |
| NaN       | East   | 200   | NaN           | 2023-01-04 |
| Product B | West   | 150   | 25            | 2023-01-05 |

#### Handle Missing Data (NaN Values)

Before we can proceed with the analysis, we need to handle missing data (NaN values).
We will use the fillna(0) method to replace NaN values with 0 for numerical columns, and for Product and Region, we will replace NaN with "Unknown".

```python
# Fill missing numerical values with 0 and categorical values with "Unknown"
df['Price'].fillna(0, inplace=True)
df['Quantity Sold'].fillna(0, inplace=True)
df['Product'].fillna('Unknown', inplace=True)
df['Region'].fillna('Unknown', inplace=True)
df['Date'].fillna('Unknown', inplace=True)

# Display the DataFrame after handling NaN values
print("\nDataFrame After Handling NaN Values:")
print(df)
```
Output:

| Product   | Region | Price | Quantity Sold | Date       |
|-----------|--------|-------|---------------|------------|
| Product A | East   | 100   | 20            | 2023-01-01 |
| Product B | West   | 0     | 15            | Unknown    |
| Product A | North  | 100   | 30            | 2023-01-03 |
| Unknown   | East   | 200   | 0             | 2023-01-04 |
| Product B | West   | 150   | 25            | 2023-01-05 |

#### Calculate Total Sales Value
Now, we calculate the Total Sales Value for each row, which is the product of the Price and Quantity Sold.
```python
# Add a new column for Total Sales (Price * Quantity Sold)
df['Total Sales'] = df['Price'] * df['Quantity Sold']

# Display the DataFrame with the Total Sales column
print("\nDataFrame After Calculating Total Sales:")
print(df)
```
Output:

| Product   | Region | Price | Quantity Sold | Date       | Total Sales |
|-----------|--------|-------|---------------|------------|-------------|
| Product A | East   | 100   | 20            | 2023-01-01 | 2000        |
| Product B | West   | 0     | 15            | Unknown    | 0           |    
| Product A | North  | 100   | 30            | 2023-01-03 | 3000        |   
| Unknown   | East   | 200   | 0             | 2023-01-04 | 0           |  
| Product B | West   | 150   | 25            | 2023-01-05 | 3750        | 

#### Analyze Sales Performance Across Regions

Next, we analyze the sales performance across different regions by calculating the total and average sales per region.

```python
# Group by 'Region' and calculate the total and average sales
region_sales = df.groupby('Region').agg(
    total_sales=('Total Sales', 'sum'),
    average_sales=('Average Sales', 'mean')
).reset_index()

# Display the region sales analysis
print("\nSales Performance Across Regions:")
print(region_sales)
```
Output:

| Region   | Total Sales | Average Sales|
|----------|-------------|--------------|
| East     | 5000        | 2500.0       |
| North    | 3000        | 3000.0       |
| Unknown  | 0           | 0.0          | 
| West     | 3750        | 1875.0       |

#### Calculate Average and Total Sales Per Product
Now, let's calculate the total and average sales per product to identify the top-selling products.
```python
# Group by 'Product' and calculate the total and average sales
product_sales = df.groupby('Product').agg(
    total_sales=('Total Sales', 'sum'),
    average_sales=('Average Sales', 'mean')
).reset_index()

# Display the product sales analysis
print("\nTotal and Average Sales Per Product:")
print(product_sales)
```
Output:

| Product   | Total Sales | Average Sales |
|-----------|-------------|---------------|
| Product A | 5000        | 2500.0        |
| Product B | 5500        | 2750.0        |
| Unknown   | 0           | 0.0           |

#### Identify Top-Selling Products Based on Total Sales

Finally, we identify the top-selling products based on the total sales value.
```python
# Sort products by total sales in descending order to identify top sellers
top_selling_products = product_sales.sort_values(by='total_sales', ascending=False)

# Display the top-selling products
print("\nTop-Selling Products Based on Total Sales:")
print(top_selling_products)
```
Output:

| Product   | Total Sales | Average Sales |
|-----------|-------------|---------------|
| Product B | 5000        | 2750.0        |
| Product A | 5500        | 2500.0        |
| Unknown   | 0           | 0.0           |