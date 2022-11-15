import java.util.ArrayList;

public class MorseCodeAdvanced {

    public static void main(String[] args) {
        System.out.println(decodeBits("1111110011001111110011000000110011000000110011111100"));
        System.out.println(decodeMorse("-.- --. .-.   --. -.-"));
    }

    public static String decodeBits(String bits) {
        String morseCode = "";
        bits = validate(bits);
        System.out.println("Starting bits: "+bits);

        int timeUnit = getTimeUnit(bits);
        for(int i = 0; i < timeUnit; i++){
            bits += "0";
        }
        int counterOfOne = 0;
        int counterOfZero = 0;
        for(int i = 0; i < bits.length()+1-timeUnit; i+=timeUnit){
            String first = ""+bits.charAt(i);
            if(first.equals("1")){
                if(counterOfZero == 3*timeUnit){
                    morseCode += " ";
                }else if(counterOfZero == 7*timeUnit){
                    morseCode += "   ";
                }
                counterOfOne+=timeUnit;
                counterOfZero = 0;
            }else{
                if(counterOfOne == 1*timeUnit){
                    morseCode += ".";
                }else if(counterOfOne == 3*timeUnit){
                    morseCode += "-";
                }
                counterOfOne = 0;
                counterOfZero+=timeUnit;
            }
           
        }
        return morseCode;
    }

    private static int getTimeUnit(String bits){
        boolean foundUnit = false;
        String[] subZero = bits.split("0");
        String[] subOne = bits.split("1");
        int timeUnit = subZero[0].length() * 3;;
        while(!foundUnit){
            foundUnit = true;
            timeUnit--;
            for(int i = 0; i < subZero.length; i++){
                if(subZero[i].length() > 0)
                    if(!(subZero[i].length() == 1*timeUnit || subZero[i].length() == 3*timeUnit)){
                        foundUnit = false;
                        break;
                    }
            }
            for(int i = 0; i < subOne.length; i++){
                if(subOne[i].length() > 0)
                    if(!(subOne[i].length() == 1*timeUnit || subOne[i].length() == 3*timeUnit || subOne[i].length() == 7*timeUnit)){
                        foundUnit = false;
                        break;
                    }
            }
        }
        System.out.println("Time unit found: "+timeUnit);
        return timeUnit;
    }

    private static String validate(String bits){
        if(bits.charAt(0) == '0')
            for(int i = 0; i < bits.length(); i++)
                if(bits.charAt(i) == '1'){
                    bits = bits.substring(i, bits.length());
                    break;
                }  
        if(bits.charAt(bits.length()-1) == '0')
            for(int i = bits.length()-1; i > 0; i--)
                if(bits.charAt(i) == '1'){
                    bits = bits.substring(0, i+1);
                    break;
                }  
        return bits;
    }

    public static String decodeMorse(String morseCode) {
        String word = "";
        String[] dati = morseCode.split(" ");
        for(int i = 0; i < dati.length; i++){
            if(dati[i].length() == 0){
                word += " ";
                i++;
            }else{
                //MorseCode is a preallocated Class, not specified here
                word += MorseCodeAdvanced.get(dati[i]);
            }
        }
        return word;
    }
      
}
