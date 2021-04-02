import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;


class CandyRecommenderTest {
    Scanner in = new Scanner(System.in);
    String answer = in.nextLine();
    int pref = Integer.parseInt(answer);
    String line = in.nextLine();
    String[] parsedLine = line.split(":");
    String[] ingredients = parsedLine[1].split(",");
    private static List<String> likes = new ArrayList<>();
    private static List<String> dislikes = new ArrayList<>();
    Set<String> possible = new HashSet<>();

    
    @BeforeEach
    void setUp() throws Exception {
        for (String ingredient: ingredients) {
            switch (pref) {
            case 1:
                likes.add(ingredient);
                break;
            case -1:
                dislikes.add(ingredient);
                break;
            default:
            }
        }
        for (String ingredient : likes) {
            possible.add(ingredient);
        }
        for (String ingredient : dislikes) {
            possible.remove(ingredient);
        }
    }

    @Test
    void allIngredientsLiked() {
        int ingredientCount = 0;
        for (String ingredient: ingredients) {
            ingredientCount++;
        }
        if (ingredientCount == possible.size()){
            fail("All ingredients are liked");
    }
    }
    
    void invalidIngredientTest() {
        for (String ingredient: ingredients) {
            assertTrue(ingredient.equals("1234567890"));
        }      
    }
}

