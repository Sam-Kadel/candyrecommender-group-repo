import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import candy.Candy;
import candy.CandyRecommender;
import candy.IngredientMap;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class CandyRecommenderTest {
    private List<Candy> candies = new ArrayList<>();
    private List<String> likes = new ArrayList<>();
    private List<String> dislikes = new ArrayList<>();
    private IngredientMap ingredientMap = new IngredientMap();

    @BeforeEach
    void setUp() throws Exception {
    }
    
    @Test
    void testUserLikesAllIngredients() {
        /**
         * Purpose: to test what happens if user likes all ingredients
         * Method: findLikedCandies
         * Initialization: IngredientMap
         * Parameters: None
         * Correct result: a full set of Candy.
         */
        try {
            CandyRecommender.readCandyFile();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (String ingredient : ingredientMap.ingredients()) {
            likes.add(ingredient);
        }
        Set<Candy> possible = CandyRecommender.findLikedCandies();
        assertEquals(possible, candies.size());
    }

    @Test
    /**
     * Purpose: to test what happens if input is invalid
     * Method: readCandyFile
     * Initialization: IngredientMap
     * Parameters: None
     * Correct result: an error is any input is invalid
     */
    void invalidInputTest() {
        try {
            CandyRecommender.readCandyFile();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        for (Candy candy : candies) {
            assertTrue(candy.equals("1234567890"));
        }      
    }


}
