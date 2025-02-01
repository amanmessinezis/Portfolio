package uk.ac.city.adbt065;

import java.util.ArrayList;

/**
 * Creates a list of each game level
 */
public class listOfGameLevels {
    // Global variables
    private final ArrayList<gameLevel> list = new ArrayList<>();

    // Method that adds a game level to the list
    public void addLevel(gameLevel gl){
        list.add(gl);
    }

    // Getter that gets a game level from the list based on the integer passed
    public gameLevel getGameLevel(int i){
        return list.get(i);
    }

}
