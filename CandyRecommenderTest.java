package candy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CandyRecommenderTest {

    private List<Candy> candies = new ArrayList<>();
    private List<String> likes = new ArrayList<>();
    private List<String> dislikes = new ArrayList<>();
    private IngredientMap ingredientMap = new IngredientMap();

    @BeforeEach
    void setUp() throws Exception {
    }

    @Test
    void testNoRecommendation() {
        /**
         * Purpose: test user is neutral about all ingredients
         * Method: findLikedCandies
         * Initialization: IngredientMap
         * Parameters: None
         * Correct result: an empty set of Candy.
         */
        try {
            CandyRecommender.readCandyFile();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String ingredient : ingredientMap.ingredients()) {
            //add nothing to the map
        }
        Set<Candy> possible = CandyRecommender.findLikedCandies();
        assertTrue(possible.size() == 0);
    }

     @Test
    void testUserDislikesAllIngredients() {
        /**
         * Purpose: to test what happens if user dislikes all ingredients
         * Method: findLikedCandies
         * Initialization: IngredientMap
         * Parameters: None
         * Correct result: an empty set of Candy.
         */
        try {
            CandyRecommender.readCandyFile();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String ingredient : ingredientMap.ingredients()) {
            dislikes.add(ingredient);
        }
        Set<Candy> possible = CandyRecommender.findLikedCandies();
        assertEquals(possible, null);
    }

}
