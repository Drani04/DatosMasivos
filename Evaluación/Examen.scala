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
