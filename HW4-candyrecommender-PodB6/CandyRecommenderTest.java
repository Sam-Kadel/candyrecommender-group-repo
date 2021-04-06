package candy;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Test;

class CandyRecommenderTest {

    
    static void setUp() throws Exception{
        CandyRecommender.initialize();
    }
    static void testReadCandyFile() throws FileNotFoundException {
        //make sure that it can read in a file with just one candy and add that candy to the candy list
        CandyRecommender.readCandyFile("CandyTest1.txt");
        assertEquals(CandyRecommender.count, 1);
        //See if it can read in the whole file using the for loop
        CandyRecommender.readCandyFile("Candy.txt");
        assertEquals(CandyRecommender.count, 18);
    }
    static void getLikedCandiesTest() {
        //hard code likes and dislikes 
        CandyRecommender.addDislikes("FRUIT");
        CandyRecommender.addDislikes("COCONUT");
        CandyRecommender.addLikes("CHOCOLATE");
        Set<Candy> possible = CandyRecommender.findLikedCandies();
        Collection<Candy> chocCandy = CandyRecommender.ingredientMap.getCandyWith("CHOCOLATE");
        Collection<Candy> fruitCandy = CandyRecommender.ingredientMap.getCandyWith("FRUIT");
        Collection<Candy> coconutCandy = CandyRecommender.ingredientMap.getCandyWith("COCONUT");
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