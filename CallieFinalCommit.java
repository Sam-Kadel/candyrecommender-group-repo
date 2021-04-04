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

    @BeforeEach
    void setUp() throws Exception {
        CandyRecommender.initialize();
    }

    @Test
    void testReadIllFormattedCandyFile() throws FileNotFoundException {

        CandyRecommender.readCandyFile("IllFormattedCandy.txt");

        List<Candy> candies = CandyRecommender.getCandies();
        IngredientMap ingredientMap = CandyRecommender.getIngredienMap();
        List<String> candyNames = new ArrayList<>();

        for (Candy c : candies) {
            candyNames.add(c.getName());
        }

        assertTrue(candyNames.contains("M&Ms"));
        assertTrue(candyNames.contains("Peanut M&Ms"));
        assertEquals(candies.size(), 2);

        assertEquals(ingredientMap.getCandyWith("CHOCOLATE").size(), 2);
        assertTrue(ingredientMap.getCandyWith("CHOCOLATE").contains("M&Ms"));
        assertTrue(ingredientMap.getCandyWith("CHOCOLATE").contains("Peanut M&Ms"));

        assertEquals(ingredientMap.getCandyWith("M&Ms").size(), 1);
        assertTrue(ingredientMap.getCandyWith("M&Ms").contains("M&Ms"));

    }

    @Test
    void testFindCandiesRepeated() {
        /**
         * Purpose: test adding or removing repeated candies
         * Method: findLikedCandies
         * Initialization: The ingredientMap
         * Parameters: None
         * Correct result: no candy will be added or removed repetitively
         */
        Candy candy = CandyRecommender.modifyIngredientMap();
        CandyRecommender.modifyPreference();
        Set<Candy> possible = CandyRecommender.findLikedCandies();

        //check if the only possible candy is MMs
        assertEquals(possible.size(), 1);
        assertTrue(possible.contains(candy));
    }

    @Test
    void testNeutralAllIngredients() throws FileNotFoundException {
        /**
         * Purpose: test user is neutral about all ingredients
         * Method: findLikedCandies
         * Initialization: IngredientMap
         * Parameters: None
         * Correct result: an empty set of Candy.
         */
        IngredientMap ingredientMap = CandyRecommender.getIngredienMap();
        CandyRecommender.readCandyFile("Candy.txt");
        for (String ingredient : ingredientMap.ingredients()) {
            //add nothing to the list
        }
        Set<Candy> possible = CandyRecommender.findLikedCandies();
        assertEquals(possible.size(), 0);
    }

     @Test
    void testUserDislikesAllIngredients() throws FileNotFoundException {
        /**
         * Purpose: to test what happens if user dislikes all ingredients
         * Method: findLikedCandies
         * Initialization: IngredientMap
         * Parameters: None
         * Correct result: an empty set of Candy.
         */
        IngredientMap ingredientMap = CandyRecommender.getIngredienMap();
        List<String> dislikes = CandyRecommender.getDislikes();
        CandyRecommender.readCandyFile("Candy.txt");
        for (String ingredient : ingredientMap.ingredients()) {
            dislikes.add(ingredient);
        }
        Set<Candy> possible = CandyRecommender.findLikedCandies();
        assertEquals(possible.size(), 0);
    }

}


//--------------------------------add helper functions to CandyRecommender.java------------------------------

/**
 * initialization to empty up all the instance variables
 */
static void initialize() {
    candies = new ArrayList<>();
    loves = new ArrayList<>();
    likes = new ArrayList<>();
    dislikes = new ArrayList<>();
    ingredientMap = new IngredientMap();
}

static List<Candy> getCandies() {
    return candies;
}

static IngredientMap getIngredienMap() {
    return ingredientMap;
}

static List<String> getDislikes() {
    return dislikes;
}

/**
 * edit the loves and dislikes lists manually
 */
static void modifyPreference() {
    loves.clear();
    dislikes.clear();
    loves.add("chocolate");
    loves.add("peanuts");
    dislikes.add("caramel");
    dislikes.add("mint");
}

/**
 * edit the ingredientMap manually
 */
static Candy modifyIngredientMap() {
    String[] ingredients1 = {"chocolate", "peanuts", "caramel", "mint"};
    Candy Snickers = new Candy("Snickers", ingredients1);
    String[] ingredients2 = {"chocolate"};
    Candy MMs = new Candy("MMs", ingredients2);
    ingredientMap.add("chocolate", Snickers);
    ingredientMap.add("peanuts", Snickers);
    ingredientMap.add("caramel", Snickers);
    ingredientMap.add("mint", Snickers);
    ingredientMap.add("chocolate", MMs);

    return MMs;
}
