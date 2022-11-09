import java.math.BigInteger;

public class BinomialExpansion {

    public static void main(String[] args) {
        expand("(5m+3)^4");
    }

    public static String expand(String expr) {
        String[] input = expr.split("[)]");
        int e = Integer.parseInt(input[1].substring(1));
        if(e == 0){ return "1"; }
        input[0] = input[0].substring(1);
        System.out.println("Contenuto parentesi: "+input[0]);
        System.out.println("Esponente: "+e);

        char lettera = 'a';
        long first = 0,second = 0;
        for(int i = 0; i < input[0].length(); i++){
            if(isLiteral(input[0].charAt(i))){
                lettera = input[0].charAt(i);
                first = Long.parseLong(input[0].substring(0,i));
                second = Long.parseLong(input[0].substring(i+1,input[0].length()));
                break;
            }
        }

        long temp = 1;
        long temp2 = 1;
        long temp3 = 1;
        for(int i = 0; i < e; i++){
            temp3 *= second;
            temp *= getBinomialCoeff(e, i);
        }
        temp = temp * temp2 * temp3;
        System.out.println(first+" "+second);
        System.out.println(temp);
        

        return "";
    }   

    private static boolean isLiteral(char c){
        if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')){
            return true;
        }
        return false;
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
