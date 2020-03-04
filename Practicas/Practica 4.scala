Practice 4

import org.apache.spark.sql.SparkSession

val spark = SparkSession.builder().getOrCreate()

val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")

df.printSchema()

df.show()

//1
df.describe ("High").show 
// Describe the statistical values ​​of the selected column

//2 
df.select ("High","Close").show 
// Displays the related values ​​of the consulted columns


//3 
df.select ("Open","Low").filter("Close < 480").show 
// Display the related and selected columns and put a filter to only display those that are less than 480

//4 
df.groupBy ("Open").show

//5
df.first 
// return the first column of the dataframe

//6 
df.columns 
// Return the dataframe columns


//7 
val df2 = df.withColumn("HV Ratio", df("High")+df("Volume")) 
// Add a column that derives from the high and Volume column



//8 
df.select(min("Volume")).show() 
// Obtiene el min de la columna volume
 
//9 
df.select(max("Volume")).show() 
// Get the max of the volume column

//10
val df2 = df.withColumn("Year", year(df("Date"))) 
// Create the year column from the date column

// 11 
val df3 = df.withColumn("Month", month(df("Date"))) 
// Create the month column from the date column

// 12 
val df3 = df.withColumn("Day", dayofmonth(df("Date"))) 
// create the day column from the month and date column

// 13
al df3 = df.withColumn("Day", dayofyear(df("Date"))) 
// Create the day column from the year column

// 14 
df.select(corr($"High", $"Volume")).show() 
// return the correlation between the High and Volume column

// 15 
df.select($"High").take(1) 
// Take 1 column from the column


// 16 
df.select("High").repartition().show() 
// Partition the selected column

// 17 
df.sort($"High".asc).show() 
// Draw the High column


// 18 
df.select(avg("High")).show() 
// Show the high column average

 
// 19 
df.filter($"Close" < 480 && $"High" < 480).collectAsList() 
// create a list from a collection

//20 
df.select(last_day(df("Date"))).show() 
// return the last day of the date column
