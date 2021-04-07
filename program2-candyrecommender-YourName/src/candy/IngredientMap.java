package candy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Maps from ingredients to candies that contain the ingredient.
 */
public class IngredientMap {
    // The map being managed.
    private Map<String, List<Candy>> ingredientMap = new HashMap<>();

    /**
     * Adds the candy to the list for the given ingredient
     * @param ingredient the ingredient whose list is changed
     * @param candy the candy to add to the list
     */
    public void add(String ingredient, Candy candy) {
        List<Candy> newList = new ArrayList<>();
        newList.add(candy);
        ingredientMap.put(ingredient, newList);
    }

    /**
     * @return all the ingredients that appear in any candy.
     */
    public Set<String> ingredients() {
        return ingredientMap.keySet();
    }

    /**
     * 
     * @param ingredient an ingredient to look up
     * @return all candies that contain the ingredient.  Returns null if there 
     * are no candies with this ingredient.
     */
    public Collection<Candy> getCandyWith(String ingredient) {
        return ingredientMap.get(ingredient);
    }
}
