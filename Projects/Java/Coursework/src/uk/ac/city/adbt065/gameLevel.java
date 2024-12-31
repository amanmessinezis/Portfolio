package uk.ac.city.adbt065;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import java.util.ArrayList;

/**
 * The superclass that defines what bodies are within the different game levels
 * <br>
 * All game levels that inherit from this class will display the lives left
 * <br>
 * as well as how many coins have not been collected yet in that game level
 */

public abstract class gameLevel extends World{ // Inherits data and methods from the World class

    // Global variables

    private final Game game;
    private Scott scott;
    private final Friend friend;
    private Vec2 groundPosition, initialPosition;
    private final ArrayList<Coin> coinList = new ArrayList<>();
    private StaticBody groundBody;
    private Shape groundShape;
    private boolean pause = false;
    // CONSTANTS
    private static final float G_HEIGHT = 0.5f;
    private static final float Y_GROUND = -12;

    /**
     * Game level constructor
     * @param game Game
     */
    public gameLevel(Game game) {
        this.game = game;
        friend = new Friend(this);
        borders();
        baseGround();
    }

    /**
     * Creates changeable bodies
     */
    public void populate(){
        scott = new Scott(this);
        friendEncounter fe = new friendEncounter(this,game);
        scott.addCollisionListener(fe);
    }

    /**
     * Sets scott
     * @param scott Scott
     */

    public void setScott(Scott scott) {
        this.scott = scott;
    }

    /**
     * Checks if the game is paused
     * @return Boolean value
     */

    public boolean isPause() {
        return pause;
    }

    /**
     * Resumes or pauses the game
     * @param pause Boolean value where true pauses the game and false resumes it
     */

    public void setPause(boolean pause) {
        this.pause = pause;
        if(pause){
            stop();
            getGame().getView().getGameMusic().stop();
        } else{
            start();
            getGame().getView().getGameMusic().resume();
        }
    }

    /**
     *
     * @return Scott
     */

    public Scott getScott() {
        return scott;
    }

    /**
     * Returns the friend Scott goes to after all coins are collected
     * @return The friend Scott goes to after all coins are collected
     */

    public Friend getFriend() {
        return friend;
    }

    /**
     * Returns the game
     * @return The game
     */

    public Game getGame() {
        return game;
    }

    /**
     * Sets the initial position of Scott
     * @param initialPosition Initial position given as a Vec2 object
     */

    public void setInitialPosition(Vec2 initialPosition) {
        this.initialPosition = initialPosition;
    }

    /**
     * Return the array list that contains all the coins in the level as well as their position
     * @return The array list that contains all the coins in the level as well as their position
     */


    public ArrayList<Coin> getCoinList() {
        return coinList;
    }

    /**
     * Return initial position of Scott when the game loads for the first time
     * @return Initial position of Scott when the game loads for the first time
     */

    public Vec2 getInitialPosition() {
        return initialPosition;
    }

    /**
     * Abstract class that requires all predecessors of the gameLevel class to implement this method
     * @return The name of the level
     */

    public abstract String getLevelName();


    /**
     * Creates a default floor using the ground method
     */

    public void defaultGround(){
        ground(15);
    }

    /**
     * Creates a ground with a custom x position
     * @param width The width of the ground
     * @param x Horizontal position of the ground
     */

    public void ground(float width, float x) {
        groundShape = new BoxShape(width, G_HEIGHT);
        groundBody = new StaticBody(this,groundShape);
        groundPosition = new Vec2(x, Y_GROUND);
        groundBody.setPosition(groundPosition);
    }

    /**
     * Creates a ground
     * @param width The width of the ground
     */

    public void ground(float width) {
        groundShape = new BoxShape(width, G_HEIGHT);
        groundBody = new StaticBody(this,groundShape);
        groundPosition = new Vec2(0, Y_GROUND);
        groundBody.setPosition(groundPosition);
    }

    /**
     * Creates a platform
     * @param width The width of the platform
     * @param x Horizontal position of the platform
     * @param y Vertical position of the platform
     */

    public void platform(float width, float x, float y){
        groundShape = new BoxShape(width, G_HEIGHT);
        groundBody = new StaticBody(this,groundShape);
        groundPosition = new Vec2(x,y);
        groundBody.setPosition(groundPosition);
    }

    /**
     * Creates an angled platform
     * @param width The width of the platform
     * @param x Horizontal position of the platform
     * @param y Vertical position of the platform
     * @param angle Angle of the platform in degrees
     */

    public void angledPlatform(float width, float x, float y, float angle){
        groundShape = new BoxShape(width, G_HEIGHT);
        groundBody = new StaticBody(this,groundShape);
        groundPosition = new Vec2(x,y);
        groundBody.setPosition(groundPosition);
        groundBody.setAngle(angle);
    }

    /**
     * Platform with long ends from the sides
     * @param width Width of the platform
     * @param x Horizontal position of the platform
     * @param y Vertical position of the platform
     */

    public void blockedPlatform(float width, float x, float y){
        Shape platformShape = new BoxShape(width+0.5f, G_HEIGHT);
        StaticBody platformBody = new StaticBody(this,platformShape);
        Vec2 platformPos = new Vec2(x,y);
        platformBody.setPosition(platformPos);
        platformBody.setAlwaysOutline(false);
        Shape border = new BoxShape(0.5f,1);
        StaticBody leftBorder = new StaticBody(this,border);
        StaticBody rightBorder = new StaticBody(this,border);
        Vec2 lbPosition = new Vec2(x-width,y+1.5f);
        Vec2 rbPosition = new Vec2(x+width,y+1.5f);
        leftBorder.setPosition(lbPosition);
        rightBorder.setPosition(rbPosition);
    }

    /**
     * Ground not visible to the user but used to detect whether Scott has fallen off the edge
     */

    public void baseGround(){
        groundShape = new BoxShape(25, G_HEIGHT);
        groundBody = new StaticBody(this,groundShape);
        groundPosition = new Vec2(0, Y_GROUND-3);
        groundBody.setPosition(groundPosition);
    }

    /**
     * Add coin to the list coinList
     * @param x Horizontal position of the coin
     * @param y Vertical position of the coin
     */

    public void addCoin(float x, float y){
        Coin coin = new Coin(this);
        coin.setPosition(new Vec2(x,y));
        coinList.add(coin);
    }

    /**
     * Creates borders on the sides of the world, not visible to the player
     */

    public void borders(){
        Vec2 leftBorderPosition = new Vec2(-12.75f, 0);
        Vec2 rightBorderPosition = new Vec2(12.75f, 0);

        Shape borderShape = new BoxShape(0.5f, 12.5f);

        StaticBody leftBorderBody = new StaticBody(this, borderShape);
        StaticBody rightBorderBody = new StaticBody(this, borderShape);

        leftBorderBody.setPosition(leftBorderPosition);
        rightBorderBody.setPosition(rightBorderPosition);
    }


    /**
     * Checks if Scott has collected all the coins on the map
     * @return Boolean value
     */

    public abstract boolean isComplete();
}
