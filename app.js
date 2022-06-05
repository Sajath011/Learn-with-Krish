// This program is specially design to identify a missing number among the given array list.

var a,b,c;
var firstNum = 21;
var LengthOfArray = 10;
var array = [ 21,25,29,28,22,24,27,26,23 ];
array.sort()


if(LengthOfArray != array.length)
{
   for (let a = 0; a < array.length; a++) 
    {

        b = array[a];
        c = array[a+1];

        if(array[0]!=firstNum)
        {
            console.log(firstNum);
            break;
        }

        else if(b+1 != c)
        {
            console.log(b+1);
            break;
        }

        else if((b+1 == c) && (a==array.length-1))
        {
            console.log(c+1);
            break;
        } 

    }

}

else{
        console.log("you have No Missing number in between the given array list");
    }