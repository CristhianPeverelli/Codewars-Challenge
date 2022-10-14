public class TenPinBowlingScore {

    public static void main(String[] args) {
        System.out.println(bowling_score("X X X X X X X X X XXX"));
        System.out.println(bowling_score("11 11 11 11 11 11 11 11 11 11"));
        System.out.println(bowling_score("81 23 34 44 45 53 7/ 35 X X45"));
        System.out.println(bowling_score("X X X 8/ X 24 X 6/ 4/ XXX"));
        System.out.println(bowling_score("X 00 00 00 00 00 00 00 X 2/3"));
    }

    public static int bowling_score(String frames) {
        int totalScore = 0, i;
        String[] scores = frames.split(" ");

        //First 8 shots
        for(i = 0; i < scores.length - 2; i++){
            String shot = scores[i];
            if(shot.charAt(0) == 'X'){
                totalScore += getStrike(scores[i+1], scores[i+2]);
            }else if(shot.charAt(1) == '/'){
                totalScore += getSpare(scores[i+1]);
            }else{
                totalScore += getPoints(shot);
            }
        }
        //Shot before last
        if(scores[i].charAt(0) == 'X'){
            if(scores[i+1].charAt(1) == '/'){
                totalScore += 20;
            }else{
                totalScore += getStrike(scores[i+1].charAt(0)+"0", scores[i+1].charAt(1)+"0");
            }
        }else if(scores[i].charAt(1) == '/'){
            totalScore += 10 + getPoints(scores[i+1].charAt(0)+"0");
        }else{
            totalScore += getPoints(scores[i]);
        }
        //Last shot
        i++;
        if(scores[i].charAt(1) == '/'){
            totalScore += 10 + getPoints(scores[i].charAt(2)+"0");
        }else if(scores[i].length() == 3 && scores[i].charAt(2) == '/'){
            totalScore += 20;
        }else{
            totalScore += getPoints(scores[i].charAt(0)+"0");
            totalScore += getPoints(scores[i].charAt(1)+"0");
            if(scores[i].length() == 3){
                totalScore += getPoints(scores[i].charAt(2)+"0");
            }
        }

        return totalScore;
    }

    private static int getStrike(String nextShot, String secondNextShot){
        int points = 0;
        if(nextShot.charAt(0) == 'X'){
            if(secondNextShot.charAt(0) == 'X'){
                points = 30;
            }else{
                points += 20 + Integer.parseInt(""+secondNextShot.charAt(0));
            }
        }else if(nextShot.charAt(1) == '/'){
            points = 20;
        }else{
            points = 10 + Integer.parseInt(""+nextShot.charAt(0)) + Integer.parseInt(""+nextShot.charAt(1));
        }
        return points;
    }

    private static int getSpare(String nextShot){
        int points = 0;
        if(nextShot.charAt(0) == 'X'){
            points = 20;
        }else{
            points = 10 + Integer.parseInt(""+nextShot.charAt(0));
        }
        return points;
    }

    private static int getPoints(String shot){
        if(shot.charAt(0) == 'X' || shot.charAt(1) == '/'){
            return 10;
        }
        return Integer.parseInt(""+shot.charAt(0)) + Integer.parseInt(""+shot.charAt(1));
    }
}
