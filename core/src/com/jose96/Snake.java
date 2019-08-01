package com.jose96;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Snake {

    Texture headTexture;
    Texture bodyTexture;

    Sprite headSprite;
    Sprite bodySprite;

    Map map;

    ArrayList<Part> body = new ArrayList<>();


    public Snake(Map map) {
        this.map = map;

        headTexture = new Texture("slimeBlock.png");
        bodyTexture = new Texture("slimeBlock_hit.png");

        headSprite = new Sprite(headTexture);
        bodySprite = new Sprite(bodyTexture);

        body.add(new Part(10, 10));
        body.add(new Part(11, 10));
        body.add(new Part(12, 10));
        body.add(new Part(13, 10));

    }
    public void draw(SpriteBatch spriteBatch){
        // todo implement

    }


}
