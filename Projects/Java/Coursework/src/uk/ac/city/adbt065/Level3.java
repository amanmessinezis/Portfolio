package uk.ac.city.adbt065;

import org.jbox2d.common.Vec2;

/**
 * The initial world
 * All bodies are placed here
 */

public class Level3 extends gameLevel { // Has access to World functions from the City Engine

    public Level3(Game game) {
        super(game); // From tutorial sessions
        super.defaultGround();

        // Platform
        platform(2.5f,-10,8);
        platform(2.5f,-10,-1.5f);
        platform(2.5f,-10,-8.5f);

        platform(2,-1.5f,1.75f);
        angledPlatform(2,-1.5f,-5f,35);

        platform(2,3.5f,5.25f);
        platform(2,3.5f,-1.5f);
        platform(2f,3.5f,-8.5f);

        platform(2,10.5f,3.25f);
        platform(2,10.5f,-1.5f);
        platform(2,10.5f,-5);

        Vec2 friendPosition = new Vec2(4.586043f,6.984999f);
        getFriend().setPosition(friendPosition);
        // getFriend().setAlwaysOutline(true);
    }

    @Override
    public void populate() {
        super.populate();
        Knight knight = new Knight(this);
        Vec2 knightPos = new Vec2(-1.0498636f,3.4849997f);
        knight.setPosition(knightPos);
        knight.addCollisionListener(new knightCollision(knight));

        Knight knight2 = new Knight(this);
        Vec2 knight2Pos = new Vec2(3.9251008f,-6.765001f);
        knight2.setPosition(knight2Pos);
        knight2.addCollisionListener(new knightCollision(knight2));

        // Positions and creates the sunflower collectible and moves it across the surface
        Sunflower fc = new Sunflower(this);
        fc.setPosition(new Vec2(-10.279003f,-0.23445167f));
        fc.addCollisionListener(new sunflowerCollision(fc));

        addCoin(-10.5f, -6.5f);
        addCoin(-10.5f,-10f);
        addCoin(10.5f,5f);
        addCoin(10.5f,-10f);

        Mushroom mushroom = new Mushroom(this);
        mushroom.setPosition(new Vec2(-5.8712473f,-10.265001f));
        mushroom.addCollisionListener(new mushroomCollision(mushroom));
        mushroom.moveLeft();

        // Creates sprites and positions both of them
        setInitialPosition(new Vec2(-11.624919f,9.734844f));
        getScott().setPosition(getInitialPosition());
        // getScott().setAlwaysOutline(true);
    }

    @Override
    public String getLevelName() {
        return "Level3";
    }

    @Override
    public boolean isComplete() {
        return getScott().getNumberOfCoins() == 4;
    }
}
