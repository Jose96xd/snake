package com.jose96;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Fish {

    Texture fishTexture;
    Sprite fishSprite;

    Map map;
    Part fishPart;

    public Fish(Map map) {
        this.map = map;

        fishTexture = new Texture("fishPink.png");
        fishSprite = new Sprite(fishTexture);
//        map.map[randomNumber()][randomNumber()] = 2;  // todo revisar
        fishPart = new Part(randomNumber(), randomNumber());
    }

    public int randomNumber() {
//        int i = (int) Math.round(Math.random());
//
//        while(i < 1 || i > 49) {
//            i = (int) Math.round(Math.random());
//        }
//        return i;
        return (int)( (Math.random()*48) + 1);

    }



    public void draw(SpriteBatch spriteBatch) {
        fishSprite.setBounds(fishPart.x * Map.MAP_OFFSET + Map.MAP_FIX_X, fishPart.y * Map.MAP_OFFSET + Map.MAP_FIX_Y, Map.TILE_SIZE, Map.TILE_SIZE);
        fishSprite.draw(spriteBatch);
    }


}
