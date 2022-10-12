import java.util.Arrays;

public class HumanReadableTimeDuration{

    public static void main(String[] args) {
        //Here are some tests
        System.out.println(formatDuration(62));
        System.out.println(formatDuration(33333));
        System.out.println(formatDuration(241343153));
        System.out.println(formatDuration(12312));
        System.out.println(formatDuration(0));
        System.out.println(formatDuration(86400));
        System.out.println(formatDuration(12345));
        System.out.println(formatDuration(8000));
    }

    public static String formatDuration(int seconds) {
        if(seconds == 0){ return "now";}
        int[] TIME_PARAMETER = {31536000, 86400, 3600, 60, 1};
        return convertRecursive("", seconds, TIME_PARAMETER);     
    }

    private static String convertRecursive(String time, int seconds, int[] TIME_PARAMETER){

        if(TIME_PARAMETER.length == 0){  return time;  }

        int temp = 0;
        if(seconds >= TIME_PARAMETER[0]){

            temp = seconds / TIME_PARAMETER[0];
            seconds -=  TIME_PARAMETER[0] * temp;
            if(seconds == 0 && time != ""){
                time += " and ";
            }else if(seconds != 0 && time != ""){
                time += ", ";
            }
        
            time += temp + "";
            switch(TIME_PARAMETER[0]){
                case 31536000:
                    time += " year";
                    break;
                case 86400:
                    time += " day";
                    break;
                case 3600:
                    time += " hour";
                    break;
                case 60:
                    time += " minute";
                    break;
                default:
                    time += " second";
                    break;                    
            }
            if(temp != 1){ time += "s"; }
                
        }
        return convertRecursive(time, seconds, Arrays.copyOfRange(TIME_PARAMETER, 1, TIME_PARAMETER.length));
    }
}