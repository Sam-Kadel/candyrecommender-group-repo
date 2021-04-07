package candy;
import java.util.ArrayList;
import java.util.List;

/**
 * Candy consists of a name and a list of ingredients.  A candy
 * can be scored based upon how closely its list of ingredients
 * aligns with a list of desirable ingredients.
 */
public class Candy {
    // The name of the candy
    private String name;
    
    // The list of ingredients in the candy
    private List<String> ingredients = new ArrayList<>();

    /**
     * Construct a new Candy object
     * @param name the candy's name
     * @param ingredients the ingredients in the candy.  There should be no
     * duplicates in the ingredient list.
     */
    public Candy(String name, String[] ingredients) {
        this.name = name;
        for (String ingredient : ingredients) {
            this.ingredients.add(ingredient);
        }
    }
    
    /**
     * @return the candy's name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Calculates a score for the candy based upon how many of the ingredients 
     * passed in are in the candy.
     * @param likes the ingredients to compare to
     * @return a number between 0 and 10.  0 means none are in the candy.  10
     * means all the ingredients in the candy are also in the likes list.
     */
    public int score (List<String> likes) {
        int match = 0;
        for (String ingredient : likes) {
            if (ingredients.contains(ingredient)) {
                match++;
            }
        }
        
        // Multiply by 10 so we get an int result between 0 and 10.
        return 10 * match / ingredients.size();
    }
}
