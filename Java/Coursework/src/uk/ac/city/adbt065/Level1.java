package uk.ac.city.adbt065;

import org.jbox2d.common.Vec2;

/**
 * The initial world with bodies and a background
 */

public class Level1 extends gameLevel { // Inherits methods and data from the gameLevel class

    // Instantiate my class using a constructor

    public Level1(Game game) {
        // The game argument is passed to the parent class of Level1 - gameLevel in this case
        super(game);
        super.defaultGround(); // Creates the default ground from the gameLevel class

        // Platform
        platform(2,-8,-5f);
        platform(2,2.5f,-3f);
        blockedPlatform(5,0,2);

        // Positions the friend, which was already created from the parent class
        Vec2 friendPosition = new Vec2(9.367753f,-10.265001f);
        getFriend().setPosition(friendPosition);
        // getFriend().setAlwaysOutline(true);

        // Instantiates the bounceMonster to add to the world and also positions it
        bounceMonster bounceMonster = new bounceMonster(this);
        Vec2 bounceMonsterPosition = new Vec2(-2.5f, -10.25f);
        bounceMonster.setPosition(bounceMonsterPosition);

        bounceMonster bounceMonster2 = new bounceMonster(this);
        Vec2 bounceMonster2Position = new Vec2(-8, -3.25f);
        bounceMonster2.setPosition(bounceMonster2Position);
    }

    @Override
    public void populate() {
        super.populate();
        // Positions the knight and by default, moves left. The collision listener for the knight is also added
        Knight knight = new Knight(this);
        knight.setPosition(new Vec2(7.6046224f,-10.265001f));
        knight.addCollisionListener(new knightCollision(knight));
        knight.moveLeft();

        // Instantiates the health pack class and positions it
         new healthPack(this,-8, -9.5f);

        // Adds a coin to the coin array as well as positions it
        addCoin(2.5f,-0.5f);
        addCoin(0,4);

        // Creates sprites and positions both of them
        setInitialPosition(new Vec2(-11.0f,-10.265156f));
        getScott().setPosition(getInitialPosition());
    }

    @Override
    public String getLevelName() {
        return "Level1";
    }

    @Override
    public boolean isComplete() {
        return getScott().getNumberOfCoins() == 2;
    }

    // Get access to Scott's members and methods from the World object
}
