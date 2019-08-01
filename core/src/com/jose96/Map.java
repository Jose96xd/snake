package com.jose96;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    private static final int MAP_SIZE = 50;
    private static final float MAP_OFFSET = 15;
    private static final float WALL_SCALE = 0.2f;
    private static final float MAP_FIX_X = 130;
    private static final float MAP_FIX_Y = 14;


    int[][] map = new int[MAP_SIZE][MAP_SIZE];

    Texture wallTexture;
    Sprite wallSprite;



    public Map() {

        wallTexture = new Texture("grassBlock_hit.png");
        wallSprite = new Sprite(wallTexture);


        for (int i = 0; i < MAP_SIZE; i++) {
            map[i][0] = 1;
            map[i][MAP_SIZE - 1] = 1;
            map[0][i] = 1;
            map[MAP_SIZE - 1][i] = 1;
        }
    }

    public void draw(SpriteBatch spriteBatch){

        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                switch (map[i][j]){
                    case 0:
                        wallSprite.setColor(0,0.5f,0,1);
                        break;
                    case 1:
                        wallSprite.setColor(1,0,0,1);
                        break;
                }
                wallSprite.setPosition(i*MAP_OFFSET + MAP_FIX_X, j*MAP_OFFSET + MAP_FIX_Y);
                wallSprite.setScale(WALL_SCALE, WALL_SCALE);
                wallSprite.draw(spriteBatch);
            }
        }

    }


}
