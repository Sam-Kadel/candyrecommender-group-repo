package candy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IngredientMapTest {
    private Candy snickers;
    private Candy reeses;
    private IngredientMap iMap = new IngredientMap();


    @BeforeEach
    void setUp() throws Exception {
        String[] ingredients = {"chocolate", "peanuts", "caramel"};
        snickers = new Candy("Snickers", ingredients);

        String[] ingredients2 = {"chocolate", "peanut butter"};
        reeses = new Candy("Reese's peanut butter cup", ingredients2);
    }

    @Test
    void testFirstCandy() {
        /*
         * Purpose:  Ingredient map keeps track of candies that are added, first candy for an ingredient
         * Method: add / getCandyWith
         * Initialization: Construct an IngredientMap, construct Snickers as in Test 1
         * Parameters: add ("chocolate", Snickers)
         * Correct result: getCandyWith ("chocolate") should return a collection containing just Snickers
         */
        iMap.add("chocolate", snickers);
        Collection<Candy> chocCandies = iMap.getCandyWith("chocolate");
        assertEquals(1, chocCandies.size());
        assertTrue(chocCandies.contains(snickers));
    }
    
    @Test
    void testMultipleCandiesForIngredient() {
        /*
         * Purpose:  Ingredient map keeps track of candies that are added, second candy for an ingredient
         * Method: add / getCandyWith
         * Initialization: Construct an IngredientMap, construct candy Reese’s with ingredients chocolate & peanut butter
         * Parameters: add ("chocolate", Snickers); add ("chocolate", Reese’s)
         * Correct result: getCandyWith ("chocolate") should return a collection containing both Snickers and Reese’s
         */
        iMap.add("chocolate", snickers);
        iMap.add("chocolate", reeses);
        Collection<Candy> chocCandies = iMap.getCandyWith("chocolate");
        
        assertTrue(chocCandies.contains(reeses));
        assertTrue(chocCandies.contains(snickers));
        
        assertEquals(2, chocCandies.size());
        assertTrue(chocCandies.contains(snickers));
        assertTrue(chocCandies.contains(reeses));
    }
    
    @Test
    void testIngredients() {
        /*
         * Purpose:  ingredients method returns all known ingredients
         * Method: ingredients
         * Initialization: Construct an ingredient map, construct Snickers and Reese’s as before, 
         * add them both to the ingredient map using all of their ingredients
         * Parameters: None
         * Correct result: Set containing "chocolate", "peanuts", "caramel", "peanut butter" in any order
         */
        iMap.add("chocolate", snickers);
        iMap.add("peanuts", snickers);
        iMap.add("caramel", snickers);
        iMap.add("chocolate", reeses);
        iMap.add("peanut butter", reeses);
        Set<String> ingred = iMap.ingredients();
        assertEquals(4, ingred.size());
        assertTrue(ingred.contains("chocolate"));
        assertTrue(ingred.contains("peanuts"));
        assertTrue(ingred.contains("caramel"));
        assertTrue(ingred.contains("peanut butter"));
    }

}
