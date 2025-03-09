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
import numpy as np

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
