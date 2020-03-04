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


