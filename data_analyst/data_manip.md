# Table of Contents

1. [Scenario 1: Sales data](#scenario1)

# 1. Scenario 1: Sales data <a name="scenario1"></a>

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
    average_sales=('Total Sales', 'mean')
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
    average_sales=('Total Sales', 'mean')
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