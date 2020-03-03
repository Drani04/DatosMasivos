import org.apache.spark.sql.SparkSession

//1 Iniciando sesion de spark
val spar = SparkSession.builder().getOrCreate()

//2 Importando archivo csv
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")
df.printSchema()

//3 Mostrando nombre de las columnas
df.columns

//4 Mostrando el esquema de la bd
df.show()

//5 imprimiendo las primeras 5 columnas
df.select($"Date", $"Open", $"High", $"Low", $"Close").show()

//6 muestra una descripcion general de la bd 
df.describe().show()

//7 Crea un nuevo dataframe con una nueva columna que es la relacion de high y volumen
val df2 = df.withColumn("HV Ratio", df("High")+df("Volume"))
df2.show()

//8 Mostrando el pico mas alto de close
 val dia = df2.groupBy("Date")
 df2.select(max("Close")).show();

//9 ¿Cuál es el significado de la columna Cerrar "Close"?
//El dinero del dia a la hora de cerrar

// 10 Mostrando minimo y maximo de volumen
df.select(min("Volume"),max("Volume")).show(); 

// 11(a) Mostrando las veces que la columna close fue menor a 600
df.filter($"Close"<600).count()

// 11(b) mostrando el porcentaje en que High fue mayor a 500
df.filter($"High" > 500).count()*100/ df.count()
 
//11(c) Mostrando la correlación de las columnas High y Volumen
df.select(corr($"High", $"Volume")).show()

// 11(d) Mostrando el maximo de la columna High por año
val df2 = df.withColumn("Year", year(df("Date")))
val Maximo = df2.groupBy("Year").max()
Maximo.select($"Year", $"max(High)").show()

//11(e) Mostrando el promedio de la columna close por cada mes del año
val df2 = df.withColumn("Month", month(df("Date")))
val promedio = df2.groupBy("Month").avg()
promedio.select($"Month", $"avg(Close)").show()


