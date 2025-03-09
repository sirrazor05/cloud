# Table of Contents

1. [Lists](#scenario1)

# 1. Scenario: Sales data <a name="scenario1"></a>

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

# Display the first few rows
print(df.head())

```
