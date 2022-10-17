import java.util.ArrayList;

public class Warrior {

    int level;
    int experience;
    String rank;
    ArrayList<String> achievements;

    Warrior(){
        level = 1;
        experience = 100;
        rank = "Pushover";
        achievements = new ArrayList<String>();
    }   

    public int level(){
        return level;
    }

    public int experience(){
        return experience;
    }

    public String rank(){
        return rank;
    }

    public ArrayList<String> achievements(){
        return achievements;
    }

    public String battle(int enemyLevel){
        if(enemyLevel < 1 || enemyLevel > 100){ return "Invalid level";}
        int difference = level - enemyLevel;
        if(difference >= 2){
            return "Easy fight";
        }else if(difference == 1 || difference == 0){
            giveExp(5);
            if (difference == 0) { giveExp(5); }
            return "A good fight";
        }else{
            if(difference <= -5 && (level/10 - (enemyLevel/10)) < 0){ return "You've been defeated"; }
            giveExp(difference * difference * 20);
            return "An intense fight";
        }   
    }

    private void giveExp(int exp){
        String[] ranks = {"Pushover","Novice","Fighter","Warrior","Veteran","Sage","Elite","Conqueror","Champion","Master","Greatest"};
        experience += exp;
        if(experience > 10000){ experience = 10000; }
        String givenRank = ranks[experience/1000];
        if(givenRank != rank){ rank = givenRank; }
        level = experience / 100;
    }

    public String training(String achievement, int expGained, int levelRequired){
        if(levelRequired > level ){ return "Not strong enough"; }
        achievements.add(achievement);
        giveExp(expGained);
        return achievement;
    }

}
