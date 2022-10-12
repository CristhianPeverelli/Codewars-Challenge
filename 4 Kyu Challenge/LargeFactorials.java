public class LargeFactorials { 

    public static void main(String args[])  {  
        int num = 1000;    
        System.out.println(Factorial(num));
    } 

    public static String Factorial(int num) {  
        int numberDigits[] = new int[num * 600];  //Max digits of the calculated factorial
        int actualDigitsSize = 1;  
        numberDigits[0] = 1;  
        for (int digit = 2; digit <= num; digit++) {  
            actualDigitsSize = getNextMultiplication(digit, numberDigits, actualDigitsSize);  
        }  
        String numberString = "";
        for(int j = actualDigitsSize - 1; j >= 0; j--)  {  
            numberString += numberDigits[j];  
        } 
        return numberString;
    }  
    
    private static int getNextMultiplication(int digit, int numberDigits[], int actualDigitsSize)  {  
        int carryOn = 0, product;         
        for (int j = 0; j < actualDigitsSize; j++)  {  
            product = numberDigits[j] * digit + carryOn;  
            numberDigits[j] = product % 10;   
            carryOn = product / 10; 
        }   
        while (carryOn != 0)  {  
            numberDigits[actualDigitsSize] = carryOn % 10;  
            carryOn = carryOn / 10;  
            actualDigitsSize++;  
        }  
        return actualDigitsSize;  
    }  
}  

