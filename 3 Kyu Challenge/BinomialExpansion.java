import java.math.BigInteger;
import java.util.ArrayList;

public class BinomialExpansion {

    public static void main(String[] args) {
        System.out.println(expand("(1x-1)^2"));
    }

    public static String expand(String expr) {
        String[] input = expr.split("[)]");
        int e = Integer.parseInt(input[1].substring(1));
        if(e == 0){ return "1"; }
        input[0] = input[0].substring(1);

        //ax + b

        String[] x = input[0].split("[a-zA-Z]");
        int[] coeff = new int[x.length];
        for(int i = 0; i < x.length; i++){
            coeff[i] = Integer.parseInt(x[i]);
        }

        String result = "";
        for(int i = 0; i < e; i++){
            if(i != 0) {result += "+";}
            long temp = getBinomialCoeff(e, i) * coeff[i];
            if(temp != 1){ result += temp; }
            if(e-i > 0){
                result += "x";
                if(e-i > 1){
                    result += "^"+(e-i);
                }
            }
            if(i>0){
                result += "y";
                if(i > 1){
                    result += "^"+i;
                }
            }
        }   

		return result;
	}

    private static long getBinomialCoeff(int n, int k){
        if(k > n){ return 0; }
        if(k == n){ return 1; }
        BigInteger nBig = BigInteger.valueOf(n);
        BigInteger kBig = BigInteger.valueOf(k);
        return factorial(nBig).divide(factorial(kBig).multiply(factorial(nBig.subtract(kBig)))).longValue();
    }

    private static BigInteger factorial(BigInteger a){
        if(a.intValue() == 0){ return new BigInteger("1"); }
        return a.multiply(factorial(a.subtract(new BigInteger("1"))));
    }
}



/* 
        ArrayList<String> expressions = new ArrayList<String>();
        int startIndex = 0;
        for(int i = 0; i < input[0].length(); i++){
            if(input[0].charAt(i) == '+' || input[0].charAt(i) == '-'){
                if(startIndex == 0){ 
                    expressions.add("+"+input[0].substring(startIndex,i));
                }else{
                    expressions.add(input[0].substring(startIndex,i));
                }
                startIndex = i;
            }
        }
        expressions.add(input[0].substring(startIndex,input[0].length()));  */