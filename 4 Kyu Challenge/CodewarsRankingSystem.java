class User {

    public int rank;
    public int progress;

    User(){
        rank = -8;
    }

    public static void main(String[] args) {
        //Test here!
        User user1 = new User();
        user1.incProgress(-7);
        System.out.println(user1.rank);
        System.out.println(user1.progress);
    }

    public void incProgress(int upComingRank){
        if(upComingRank >= -8 && upComingRank <= 8 && upComingRank != 0){
            int points = getPoints(upComingRank);
            if(rank != 8){ progress += points; }
            if( progress >= 100 && rank != 8){
                rank += progress / 100;
                if(rank >= 8){ rank = 8; progress = 0;}
                progress = progress % 100;
                if(rank == 0){ rank++; }
            }
        }else{
            throw new IllegalArgumentException("Inserted rank in incProgress is invalid");
        }
    }

    private int getPoints(int exerciseRank){
        int points = 3;
        if( rank != exerciseRank ){ 
            if(rank < exerciseRank){
                int difference = getDifference(rank, exerciseRank);
                points = 10 * difference * difference;
            }else{
                points = (getDifference(exerciseRank, rank) == 1) ? 1 : 0;
            }
        }
        return points;
    }

    private int getDifference(int minValue, int maxValue){
        int difference = 0;
        for(int count = minValue; count < maxValue; count++){
            if(count == -1){ count++;}
            difference++;
        }
        return difference;
    }

}