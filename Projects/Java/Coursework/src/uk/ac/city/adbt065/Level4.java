package uk.ac.city.adbt065;

import org.jbox2d.common.Vec2;

/**
 * The initial world
 * All bodies are placed here
 */

public class Level4 extends gameLevel { // Has access to World functions from the City Engine


    public Level4(Game game) {
        super(game); // From tutorial sessions

        ground(3,-10);
        platform(1.5f,0,-12);
        ground(3,10);

        Vec2 friendPosition = new Vec2(11.419316f,-10.265001f);
        getFriend().setPosition(friendPosition);
        // getFriend().setAlwaysOutline(true);

        uk.ac.city.adbt065.bounceMonster bounceMonster = new bounceMonster(this);
        Vec2 bounceMonsterPosition = new Vec2(0, -10.5f);
        bounceMonster.setPosition(bounceMonsterPosition);
        
    }

    @Override
    public void populate() {
        super.populate();
        // Creates sprites and positions both of them
        setInitialPosition(new Vec2(-11.624919f,-10.265156f));
        getScott().setPosition(getInitialPosition());
        addCoin(5.3794727f,-1.4986571f);

        Knight knight = new Knight(this);
        Vec2 knightPos = new Vec2(8.481424f,-10.265549f);
        knight.setPosition(knightPos);
        knight.addCollisionListener(new knightCollision(knight));

    }

    @Override
    public String getLevelName() {
        return "Level2";
    }

    @Override
    public boolean isComplete() {
        return getScott().getNumberOfCoins() == 1;
    }
}
