package candy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Homework4(PodB6)
 * @Students: Callie He, Ilika Tripathi, Emily Nishikimoto, Sam Kadel-Garcia
 */
class CandyRecommenderTest {

    @BeforeEach
    void setUp() throws Exception {
        CandyRecommender.initialize();
    }
    
    @Test
    void testReadIllFormattedCandyFile() throws FileNotFoundException {
        /**
         * Purpose: test reading a file that is ill-formatted
         * Method: readCandyFile()
         * Initialization: The candy file that has name and ingredients
         * Parameters: candy file name
         * Correct result: replicated ingredients/wrong ingredients with candy name
         * will not be added to the map
         */
        
        CandyRecommender.readCandyFile("IllFormattedCandy.txt");
        
        List<Candy> candies = CandyRecommender.getCandies();
        IngredientMap ingredientMap = CandyRecommender.getIngredienMap();
        List<String> candyNames = new ArrayList<>();
        
        for (Candy c : candies) {
            candyNames.add(c.getName());
        }
        
        assertTrue(candyNames.contains("M&Ms"));
        assertTrue(candyNames.contains("Peanut M&Ms"));
        assertEquals(candies.size(), 3);
        
        Collection<Candy> chocCandies = ingredientMap.getCandyWith("CHOCOLATE");
        List<String> chocNames = new ArrayList<>();
        for (Candy c : chocCandies) {
            chocNames.add(c.getName());
        }
        
        //asserts below would fail in this project because we didn't modify the Candy.java
        //and ingredientMap.java. They should pass when all the bugs are fixed
        assertEquals(chocNames.size(), 2);
        assertTrue(chocNames.contains("M&Ms"));
        assertTrue(chocNames.contains("Peanut M&Ms"));
        
        Collection<Candy> MMsCandies = ingredientMap.getCandyWith("M&Ms");
        assertNull(MMsCandies);
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
     
     @Test
     void testUserLikesAllIngredients() {
         /**
          * Purpose: to test what happens if user likes all ingredients
          * Method: findLikedCandies
          * Initialization: IngredientMap
          * Parameters: None
          * Correct result: a full set of Candy.
          */
         IngredientMap ingredientMap = CandyRecommender.getIngredienMap();
         List<String> likes = CandyRecommender.getLikes();
         List<Candy> candies = CandyRecommender.getCandies();
         try {
             CandyRecommender.readCandyFile("Candy.txt");
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         for (String ingredient : ingredientMap.ingredients()) {
             likes.add(ingredient);
         }
         Set<Candy> possible = CandyRecommender.findLikedCandies();
         //fails because of bugs in the original code
         assertEquals(possible.size(), candies.size());
     }

     @Test
     void invalidInputTest() {
         /**
          * Purpose: to test what happens if input is invalid
          * Method: readCandyFile
          * Initialization: IngredientMap
          * Parameters: None
          * Correct result: an error is any input is invalid
          */
         List<Candy> candies = CandyRecommender.getCandies();
         try {
             CandyRecommender.readCandyFile("Candy.txt");
         } catch (FileNotFoundException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
         for (Candy candy : candies) {
             assertFalse(candy.equals("1234567890"));
         }      
     }
     
     @Test
     void testReadCandyFile() throws FileNotFoundException {
         List<Candy> candies = CandyRecommender.getCandies();
         //make sure that it can read in a file with just one candy and add that candy to the candy list
         CandyRecommender.readCandyFile("CandyTest1.txt");
         assertEquals(candies.size(), 1);
         //See if it can read in the whole file using the for loop
         CandyRecommender.readCandyFile("Candy.txt");
         assertEquals(candies.size(), 18);
     }
     
     @Test
     void getLikedCandiesTest() throws FileNotFoundException {
         IngredientMap ingredientMap = CandyRecommender.getIngredienMap();
         //hard code likes and dislikes 
         CandyRecommender.readCandyFile("Candy.txt");
         CandyRecommender.addDislikes("FRUIT");
         CandyRecommender.addDislikes("COCONUT");
         CandyRecommender.addLikes("CHOCOLATE");
         Set<Candy> possible = CandyRecommender.findLikedCandies();
         Collection<Candy> chocCandy = ingredientMap.getCandyWith("CHOCOLATE");
         Collection<Candy> fruitCandy = ingredientMap.getCandyWith("FRUIT");
         Collection<Candy> coconutCandy = ingredientMap.getCandyWith("COCONUT");
         //make sure that it contains the likes 
         for(Candy c: chocCandy) {
             assertTrue(possible.contains(c));
         }
         //make sure not making dislikes a possible choice
         for (Candy f: fruitCandy) {
             assertFalse(possible.contains(f));
         }
         for (Candy t :coconutCandy) {
             assertFalse(possible.contains(t));
         }
     }
   
}
