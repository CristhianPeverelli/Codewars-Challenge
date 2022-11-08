/* IMPORTANT: This exercise just requires to do a sum of 2 numbers in a string format.
 * In Java this could be easily done by using BigIntegers, but the istructions specifies to not 
 * use any kind of Java.Math methods. 
*/

import java.util.*;

public class SumBigInt {

    public static void main(String[] args) {
        System.out.println(add("239492949299592499592942432524424","85294925858868386834882383824858235"));
    }

    public static String add(String a, String b) {
        System.out.println("First string: "+a+"\nSecond string: "+b+"\n");
        String s = "";
        boolean carryOver = false;
        ArrayList<Integer> result = new ArrayList<Integer>();
        for(int i = 0; i < a.length() || i < b.length(); i++){
            int first = 0, second = 0;
            if(i < a.length()){ first = Integer.parseInt(a.charAt(a.length()-1-i)+""); }
            if(i < b.length()){ second = Integer.parseInt(b.charAt(b.length()-1-i)+""); }
            int sum = first + second;
            if(carryOver){ sum++; }
            if(sum >= 10){
                sum -= 10;
                carryOver = true;
            }else{
                carryOver = false;
            }
            result.add(sum);
        }
        if(carryOver){ result.add(1); } //this is for specific cases like 888+222
        for(int i = 0; i < result.size(); i++){
            s = result.get(i) + s;
        }
        while(s.charAt(0) == '0'){
            s = s.replaceFirst("0", "");
        }
        return s;
    }
}
