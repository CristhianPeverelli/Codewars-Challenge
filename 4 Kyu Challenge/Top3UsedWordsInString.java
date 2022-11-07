/* IMPORTANT SPECIFICS: 
 * A word is a string of letters (A to Z) optionally containing one or more apostrophes (') in ASCII.
 * Apostrophes can appear at the start, middle or end of a word ('abc, abc', 'abc', ab'c are all valid)
 * Any other characters (e.g. #, \, / , . ...) are not part of a word and should be treated as whitespace.
 * Matches should be case-insensitive, and the words in the result should be lowercased.
*/

import java.util.*;

public class Top3UsedWordsInString {
    
    public static List<String> top3(String s) {
        System.out.println("Starting string: \n"+s);
        s = s.replaceAll("[^a-zA-Z0-9']+"," ");
        System.out.println("\nModified string: \n"+s);
        String[] input = s.split(" ");
        HashMap<String,Integer> map = new HashMap<String,Integer>();
        for(int i = 0; i < input.length; i++){
            if(input[i].length() != 0 && isValid(input[i])){
                map.merge(input[i].toLowerCase(),1,Integer::sum); 
            }          
        }
        Map.Entry<String, Integer> topEntry = null;
        Map.Entry<String, Integer> secondEntry = null;
        Map.Entry<String, Integer> thirdEntry = null;
        for (Map.Entry<String,Integer> entry : map.entrySet()){
            if (topEntry == null || entry.getValue().compareTo(topEntry.getValue()) > 0){
                thirdEntry = secondEntry;
                secondEntry = topEntry;
                topEntry = entry;
            }else if (secondEntry == null || entry.getValue().compareTo(secondEntry.getValue()) > 0){
                thirdEntry = secondEntry;
                secondEntry = entry;
            }else if (thirdEntry == null || entry.getValue().compareTo(thirdEntry.getValue()) > 0){
                thirdEntry = entry;
            }
        }
        System.out.println(map);
        ArrayList<String> results = new ArrayList<String>();
        if(topEntry != null){ results.add(topEntry.getKey()); } 
        if(secondEntry != null){ results.add(secondEntry.getKey()); } 
        if(thirdEntry != null){ results.add(thirdEntry.getKey()); } 
        
        return results;
    }

    private static boolean isValid(String s){
        if(s.replaceAll("'","").length() == 0){
          return false;
        }
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c != 39 && ((c < 97 && c > 90) || (c > 122 || c < 65))){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
    }

}

