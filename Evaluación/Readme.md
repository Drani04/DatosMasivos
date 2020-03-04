TEST 1

// A list is created with integer values ​​and a variable for each of the 2 diagonals with which you work

val arr=List(11,2,4,4,5,6,10,8,-12)
var d1=(arr._1)+(arr._5)+(arr._9)
var d2=(arr._3)+(arr._5)+(arr._7)

// A function is created which will receive from parameter a list with which the function will work, in addition an if was used so that the result is always positive assuming that the value was less than 0 the result is multiplied by -1 to remove the negative value

def DiagonalDiference1(arr:List[Int] ): Int = 
{    
    if((x-y) <  0)
    {
        return ((x-y)*(-1))
    }
        else
        {
            return x-y
        }
}


// The list is sent for the function to work with it and return the result

DiagonalDiference1(List(11,2,4,4,5,6,10,8,-12))


Test 2:
import org.apache.spark.sql.SparkSession

//1 Starting spark session
val spar = SparkSession.builder().getOrCreate()

//2 Importing Csv File
val df = spark.read.option("header", "true").option("inferSchema","true")csv("Netflix_2011_2016.csv")
df.printSchema()

//3 Showing name of columns
df.columns

//4 Showing the scheme of the bd
df.show()

//5 printing the first 5 columns
df.select($"Date", $"Open", $"High", $"Low", $"Close").show()

//6 shows a general description of the bd
df.describe().show()

//7 Creating a new dataframe with a new column that is the ratio of high and volume
val df2 = df.withColumn("HV Ratio", df("High")+df("Volume"))
df2.show()

//8 Showing the highest peak of close
 val dia = df2.groupBy("Date")
 df2.select(max("Close")).show();

//9 ¿Cuál es el significado de la columna Cerrar "Close"?
//El dinero del dia a la hora de cerrar

//10  Showing minimum and maximum volume
df.select(min("Volume"),max("Volume")).show(); 

// 11(a) Showing the times that the close column was less than 600
df.filter($"Close"<600).count()

// 11(b) Showing the percentage at which High was greater than 500
df.filter($"High" > 500).count()*100/ df.count()
 
//11(c) Showing the correlation of the High and Volume columns
df.select(corr($"High", $"Volume")).show()

// 11(d) showing the maximum of the High column per year
val df2 = df.withColumn("Year", year(df("Date")))
val Maximo = df2.groupBy("Year").max()
Maximo.select($"Year", $"max(High)").show()

//11(e) Showing the average of the close column for each month of the year
val df2 = df.withColumn("Month", month(df("Date")))
val promedio = df2.groupBy("Month").avg()
promedio.select($"Month", $"avg(Close)").show()
