package uk.ac.city.adbt065;

import org.jbox2d.common.Vec2;

/**
 * Second level
 */

public class Level2 extends gameLevel {

    // Allows instantiation of the the class

    public Level2(Game game){
        super(game);
        ground(6.5f,-12);

        platform(2,-1,-5);
        platform(2,3,1.5f);
        platform(3.5f,9,8);


        Vec2 friendPosition = new Vec2(11.241685f,9.734999f);
        this.getFriend().setPosition(friendPosition);
        // getFriend().setAlwaysOutline(true);


        uk.ac.city.adbt065.bounceMonster bounceMonster = new bounceMonster(this);
        Vec2 bounceMonsterPosition = new Vec2(-7, -10.25f);
        bounceMonster.setPosition(bounceMonsterPosition);
        uk.ac.city.adbt065.bounceMonster bounceMonster2 = new bounceMonster(this);
        Vec2 bounceMonster2Position = new Vec2(-1, -3.25f);
        bounceMonster2.setPosition(bounceMonster2Position);
        uk.ac.city.adbt065.bounceMonster bounceMonster3 = new bounceMonster(this);
        Vec2 bounceMonster3Position = new Vec2(3, 3.25f);
        bounceMonster3.setPosition(bounceMonster3Position);

        Vec2 waspPosition = new Vec2(-3,5.5f);
        Wasp wasp = new Wasp(this);
        wasp.setPosition(waspPosition);

    }

    @Override
    public void populate() {
        super.populate();
        setInitialPosition(new Vec2(-11.0f,-10.265666f));
        getScott().setPosition(getInitialPosition());

        addCoin(7.1020007f,9.734844f);
    }

    @Override
    public String getLevelName() {
        return "Level2";
    }

    @Override
    public boolean isComplete() {
        return getScott().getNumberOfCoins() == 1;
    }


    // Get access to Scott's members and methods from the World object
}
