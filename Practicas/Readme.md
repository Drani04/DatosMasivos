Practice 1

// Assessment 1/

//1. Develop an algorithm in scala that calculates the radius of a circle

// We declare three variables
// In the first variable is where we assign the value that the circumference will take
// In another variable we assign the value of pi
// In the third variable we will perform the operation according to the formula to calculate the radius of a circle.
// And finally the command to print the value thrown in our third variable


val  cir = 10
val  pi = 3.1416
val  r = cir/pi*2
println(r)


//2. Develop an algorithm in scala that tells me if a number is a cousin

// We declare two variables
// In the first one where we enter the number we want to know if it is a cousin or not
// In the second one a counter starting at zero
// We created a FOR cycle to record the number of times the number was divisible
// We created an IF to verify that the condition of the number to be divided was met by checking that the residue was 0
// Creating the condition if the counter was divisible by the same number and by 1
// We create another IF where if the counter is different from two means that the number will not be a prime number
// Then with the function to print to send as text “Number not cousin” or “He is cousin”


val  numero = 5
var  cont=0
for ( i<-Rango (1, número )) 
{
 if (numero % i == 0) 
 {
   cont+=1
 }
}
if (cont!=2) {
println (" Numero no primo ")
} más {
println ( " El numero es primo " )
}


//3. Given the variable bird = "tweet", use string interpolation to print "I am writing a tweet"

// We create a variable called bird that hosts the word “tweet”
// Create a variable called interpolate with default text and interpolate the bird variable created earlier so the text is shown interpolating the text of the word within the variable


val  bird  =  " tweet "
val  interpolar  =  " Estoy escribiendo un " + bird

//4. Given the variable message = "Hi Luke, I'm your father!" use slice to extract the sequence "Luke"

// Create a variable to enter the text by default
// We link the variable to the slice function that will show us what is in the space from 5 to 10 within the default text

val  star  =  " Hola Luke Yo soy tu padre "
    star.slice ( 5 , 10 )

//5. What is the difference in value and a variable in scala?
// The variable can change in value and is called a mutable variable
// The value is a variable that cannot change its value and is called an immutable variable.


//6. Given the tuple ((2,4,5), (1,2,3), (3,116,23))) return the number 3.1416

// Create a variable with the assigned values
// print the variable with the property to show us the position we want according to the number
 
val  tupla  = (( 2 , 4 , 5 ), ( 1 , 2 , 3 ), ( 3.1416 , 23 ))
println (tupla._3)


Practice 2


// 1. Create a list called "list" with the elements "red", "white", "black"

// Create a list named with the word list and enter string values
and print the list to show that if the values ​​were saved

val  lista  =  List ( " rojo " , " blanco " , " negro " )
 println (lista)



// 2. Add 5 more items to "list" "green", "yellow", "blue", "orange", "pearl"

// We add 5 more variables to the list with the property to position each value where it corresponds as is the case

val  c1  =  " Verde "  :: lista
val  c2  =  " Amarillo "  :: c1
val  c3  =  " Azul "  :: c2
val  c4  =  " Naranja "  :: c3
val  c5  =  " Perla "  :: c4


// 3. Bring the "list" "green", "yellow", "blue" items

// Create the list variable in which we will call the list with the requested values
// display the list with the positions of a slice to bring the necessary colors from the list

var  lista  =  List ( " rojo " , " blanco " , " negro " , " verde " , " amarillo " , " azul " , " naranja " , " perla " )
lista.slice ( 3 , 6 )


// 4. Create a number array in the 1-1000 range in 5-in-5 steps

// create an arrangement that goes from 1 to 1000 by throwing the values ​​of 5 in 5
// We create a FOR cycle where the arrangement does not exceed 1000 continue to throw the numbers of 5 in 5
// We print the arrangement plus the value in the counter preventing the battery from overflowing


val array = (1 to 1000).by(5)
    for(i <- array){
        println(""+i)

    }



// 5. What are the unique elements of the List list (1,3,3,4,6,7,3,7) use conversion to sets

// Create a list with the name mylist with default values
// In the function we return the values ​​of the list that are not repeated

val mylist = List(1,3,3,4,5,7,3,7)
    mylist.toSet

// 6. Create a mutable map called names containing the following "José", 20, "Luis", 24, "Ana", 23, "Susana", "27"
//to . Print all map keys

// We create a mutable map called names with default values
// print the map with the KEYS property to show the default values

val nombres = collection.mutable.Map(("José",20),("Luis",24),("Ana",23),("Susana",27))
nombres.keys
// b. Add the following value to the map ("Miguel", 23)

// Under the property of the mutable map we add another value to the map

nombres += ("Miguel" -> 23)

Practice 3


// Fibonacci was performed recursively
// Fibonnaci series
// Recursive

def fib(n:Int) : Int = 
{
     if (n<2){n  }
     else { fib(n-1)+fib(n-2)}
 }
 for (i<-1 to 10)
 println(fib(i))

 //Fiboniacci // Recursive

val n = 10

def fibonacci1(n:Int) : Int ={
if (n<2){
return n
}
else{
    return fibonacci1(n-1) + fibonacci1(n-2)
}
}  

println(fibonacci1(n))



// Algorithm 2 Version with explicit formula (6) (Complexity O

val n = 10
var phi=((1+math.sqrt(5))/2)
var j=((math.pow(phi,n)-math.pow((1-phi),n))/(math.sqrt(5)))


def fibonacci2(n:Double) : Double ={
if (n<2){
return n
}
else {

    return j
}
}
println(fibonacci2(n))

// Algorithm 3 Iterative version
// (Complexity O (n)

def fibonacci3(n:Int):Int={
var n : Int = 6
var a = 0
var b = 1
var c = 0
var k = 0 


    for(k <- 1 to n) {
        
        c = b + a
        a = b
        b = c 
    }
     return a
}
println(fibonacci3(n))

// Algorithm 4 Iterative version 2 variables (Complexity (O (n))

def fibonacci4(n:Int):Int={
var n : Int = 10
var a = 0
var b = 1
var k = 0 


    for(k <- 1 to n) {
        b = b + a
        a = b - a        
    
        }
     return a
}
println(fibonacci4(a))


// Algorithm 5 Iterative vector version (Complexity O (n))


var n = 10
def fibonacci4(n:Int):Int={
    var arreglo = Array (n+2)
    var i : Int
    arreglo (0,0)
    arreglo (1,1)

    for (i <- 1 to 2 )



}
println(fibonacci4(a))



  def fib(n: Int): Int = {
    val n = 10
    val f: Array[Int] = Array.ofDim[Int](n + 2)
    

    f(0) = 0
    f(1) = 1

    for (i <- 2 to n) {
      
      f(i) = f(i - 1) + f(i - 2) //{ i += 1; i - 1 }
    }
    f(n)
  }
  println(fib(8))




// ALGORITHM 6

def fib6 (n : Int) : Double =
{
    if (n <= 0)
    {
        return 0
    }
    var i = n-1
    var auxOne = 0.0
    var auxTwo = 1.0
    var ab = Array(auxTwo,auxOne)
    var cd = Array(auxOne,auxTwo)
    while (i>0)
    {
        if (i % 2 != 0)
        {
            auxOne = cd(1) * ab(1) + cd(0) * ab(0)
            auxTwo = cd(1) * (ab(1)+ab(0)) + cd(0)*ab(1)
            ab(0) = auxOne
            ab(1) = auxTwo 
        } 
        auxOne = (math.pow(cd(0),2)) + (math.pow(cd(1),2))
        auxTwo = cd(1)* (2*cd(0) + cd(1))
        cd(0) = auxOne
        cd(1) = auxTwo
        i = i/2
    }
    return (ab(0) + ab(1))
}



 def fibi(n:Int, arr:Int) Int , int ={

    for(int i = 0; i < n; i++){
            if(i < 2)
               arr[i] = 1;}
            else
               arr[i] = arr[i-1] + arr[i-2];
         return arr[n-1];
 }
 
println(fibi(10))

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


Homework 1:


The correlation indicates the strength and direction of a linear relationship and proportionality between two statistical variables. Consider that two quantitative variables are correlated when the values ​​of one of them systematically control with respect to the homonymous values ​​of the other: if we have two variables (A and B) there is a correlation between them if the values ​​of A also decrease. of B and vice versa. The correlation between two variables does not imply, by itself, any causal relationship.


The term correlation is often used to indicate the correspondence or reciprocal relationship between two or more things, ideas, people, among others.
Meanwhile, in probability and statistics, the correlation is what indicates the force and the linear direction that it establishes between two random variables.

Homework 2:

Variance is a measure of dispersion that represents the variability of a series of data with respect to its mean. Formally it is calculated as the sum of the square residuals divided by the total observations. It can also be calculated as the standard deviation squared. By the way, we understand as residual the difference between the value of one variable at a time and the average value of the entire variable. 

The unit of measure of the variance will always be the unit of measure corresponding to the data but squared. The variance is always greater than or equal to zero. When the residuals are square, it is mathematically impossible for the variance to be negative. And that way it can't be less than zero.


