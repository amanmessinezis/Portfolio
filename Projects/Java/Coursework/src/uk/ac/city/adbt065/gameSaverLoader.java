package uk.ac.city.adbt065;

import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import org.jbox2d.common.Vec2;

import java.io.*;

/**
 * Saves and loads the game
 */

public class gameSaverLoader {
    private static FileReader fr;
    private static BufferedReader reader;
    private static gameLevel gl;
    public static void save(gameLevel gl) throws IOException {
        String fileName = null;
        if(gl instanceof Level1){
            fileName = "data/level1.txt";
        } else if(gl instanceof Level2){
            fileName = "data/level2.txt";
        } else if(gl instanceof Level3){
            fileName = "data/level3.txt";
        } else if(gl instanceof Level4){
            fileName = "data/level4.txt";
        }
        assert fileName != null;
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(gl.getLevelName() + "\n");
            for(StaticBody body : gl.getStaticBodies()){
                if(body instanceof healthPack){
                    writer.write("Health pack," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if(body instanceof Coin){
                    writer.write("Coin," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            }
            for(DynamicBody body : gl.getDynamicBodies()){
                if(body instanceof Scott){
                    writer.write("Scott," + body.getPosition().x + "," + body.getPosition().y + "," + ((Scott) body).getNumberOfCoins() + "," + ((Scott) body).getHealth() + "\n");
                } else if(body instanceof Knight){
                    writer.write("Knight," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if(body instanceof Mushroom){
                    writer.write("Mushroom," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if(body instanceof Sunflower){
                    writer.write("Sunflower collectible," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            }
        }
    }
    public static gameLevel load(String fileName, Game game) throws IOException {
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            Vec2 initialPosition = game.getGl().getInitialPosition();
            String line = reader.readLine();
            switch (line) {
                case "Level1" -> gl = new Level1(game);
                case "Level2" -> gl = new Level2(game);
                case "Level3" -> gl = new Level3(game);
                case "Level4" -> gl = new Level4(game);
                default -> throw new IllegalStateException("Unexpected value: " + line);
            }
            line = reader.readLine();
            while (line != null){
                String[] tokens = line.split(",");
                switch (tokens[0]) {
                    case "Coin" -> {
                        float coinXPos = Float.parseFloat(tokens[1]);
                        float coinYPos = Float.parseFloat(tokens[2]);
                        gl.addCoin(coinXPos, coinYPos);
                    }
                    case "Health pack" -> {
                        float hpXPos = Float.parseFloat(tokens[1]);
                        float hpYPos = Float.parseFloat(tokens[2]);
                        new healthPack(gl, hpXPos, hpYPos);
                    }
                    case "Scott" -> {
                        Scott scott = new Scott(gameSaverLoader.getGl());
                        float scottXPos = Float.parseFloat(tokens[1]);
                        float scottYPos = Float.parseFloat(tokens[2]);
                        scott.setPosition(new Vec2(scottXPos, scottYPos));
                        int coins = Integer.parseInt(tokens[3]);
                        int health = Integer.parseInt(tokens[4]);
                        scott.setNumberOfCoins(coins);
                        scott.setHealth(health);
                        scott.addCollisionListener(new scottCollision(scott));
                        friendEncounter fe = new friendEncounter(gl,game);
                        scott.addCollisionListener(fe);
                        gl.setInitialPosition(initialPosition);
                        gl.setScott(scott);
                    }
                    case "Knight" -> {
                        Knight knight = new Knight(gl);
                        float knightXPos = Float.parseFloat(tokens[1]);
                        float knightYPos = Float.parseFloat(tokens[2]);
                        knight.setPosition(new Vec2(knightXPos, knightYPos));
                        if(gl instanceof Level1){
                            knight.moveLeft();
                        }
                        knight.addCollisionListener(new knightCollision(knight));
                    }
                    case "Mushroom" -> {
                        Mushroom mushroom = new Mushroom(gl);
                        float mushroomXPos = Float.parseFloat(tokens[1]);
                        float mushroomYPos = Float.parseFloat(tokens[2]);
                        mushroom.setPosition(new Vec2(mushroomXPos, mushroomYPos));
                        mushroom.addCollisionListener(new mushroomCollision(mushroom));
                    }
                    case "Sunflower collectible" -> {
                        Sunflower fc = new Sunflower(gl);
                        float fcXPos = Float.parseFloat(tokens[1]);
                        float fcYPos = Float.parseFloat(tokens[2]);
                        fc.setPosition(new Vec2(fcXPos, fcYPos));
                        fc.addCollisionListener(new sunflowerCollision(fc));
                    }
                }
                line = reader.readLine();
            }
            return gl;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

    public static gameLevel getGl() {
        return gl;
    }

    public static void setGl(gameLevel gl) {
        gameSaverLoader.gl = gl;
    }

}
