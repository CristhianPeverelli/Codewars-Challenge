import java.math.BigInteger;

public class BinomialExpansion  {
    public static void main(String[] args) {
        expand("(5m+3)^4");
    }

    public static String expand(String expr) {
        String[] input = expr.split("[)]");
        int e = Integer.parseInt(input[1].substring(1));
        if(e == 0){ return "1"; }
        input[0] = input[0].substring(1);

        char variable = 'a';
        long firstCoeff = 0,secondCoeff = 0;
        for(int i = 0; i < input[0].length(); i++){
            if(isLiteral(input[0].charAt(i))){
                variable = input[0].charAt(i);
                if(i == 0){
                    firstCoeff = 1;
                }else if(input[0].charAt(0) == '-' && isLiteral(input[0].charAt(1)) ){
                    firstCoeff = -1;
                }else{
                    firstCoeff = Long.parseLong(input[0].substring(0,i));
                }
                secondCoeff = Long.parseLong(input[0].substring(i+1,input[0].length()));
                break;
            }
        }

        long intermediateCoeff = 0;
        String finalString = "";
        for(int i = 0; i <= e; i++){
            intermediateCoeff = 0;
            intermediateCoeff = intermediateCoeff + ((long)Math.pow((double)secondCoeff, (double)i) * getBinomialCoeff(e, i) * (long)Math.pow((double)firstCoeff, (double)(e-i)));
            if(intermediateCoeff != 0){
                if(intermediateCoeff > 0 && i != 0){ finalString += "+"; }
                if(!(intermediateCoeff == 1 && (e-i) > 0)){
                    if(intermediateCoeff == -1 && (e-i) >0){
                        finalString += "-";
                    }else{
                        finalString += intermediateCoeff;
                    }
                }
                if((e-i) >= 1){
                    finalString += ""+variable;
                    if((e-i) > 1){
                        finalString += "^"+(e-i);
                    }
                }
            }
        }
        System.out.println(finalString);
        return finalString;
    }   

    private static boolean isLiteral(char c){
        return ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'));
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
