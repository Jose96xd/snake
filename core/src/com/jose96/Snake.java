package com.jose96;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Snake {

    public enum Direction {  //Enumerado
        UP, DOWN, LEFT, RIGHT
    }

    Texture headTexture;  //Declaro texturas y prits
    Texture bodyTexture;

    Sprite headSprite;
    Sprite bodySprite;

    Map map;  //Declarar

    ArrayList<Part> body = new ArrayList<>(); //Declarar e instanciar pero sin rellenar
    //Esto es una lista en la que guardo y voy añadiendo las partes de la serpiente

    private static final float MAX_TICK = 0.05f;
    private float tick;
    private Direction direction = Direction.UP;


    public Snake(Map map) { //Constructor que recibe parametro map
        this.map = map; //Instancio el map de la clase igualandolo al recibido

        headTexture = new Texture("slimeBlock.png");  //Asigno a las texturas la imágen
        bodyTexture = new Texture("slimeBlock_hit.png");

        headSprite = new Sprite(headTexture);  //Asigno a los sprits las textoras  (instanciar?)
        bodySprite = new Sprite(bodyTexture);

        //Aquí añadimos 4 partes
        //A lista body, añado (add) nueva parte (llamada a constructor Part) y le asigno valores a x e y
        body.add(new Part(10, 10));
        body.add(new Part(11, 10));
        body.add(new Part(12, 10));
        body.add(new Part(13, 10));
        body.add(new Part(14, 10));
        body.add(new Part(15, 10));
        body.add(new Part(16, 10));
        body.add(new Part(17, 10));
        body.add(new Part(18, 10));
        body.add(new Part(19, 10));
        body.add(new Part(20, 10));
        body.add(new Part(21, 10));
        body.add(new Part(22, 10));

    }

    public void draw(SpriteBatch spriteBatch){
        for (int i = 0; i < body.size(); i++) {
            Part part = body.get(i);
            Sprite sprite;

            if (i == body.size() - 1) {
                sprite = headSprite;
            } else {
                sprite = bodySprite;
                sprite.setColor(0.2f, 0.8f, 0.2f, 1);

            }
            sprite.setBounds(part.x * Map.MAP_OFFSET + Map.MAP_FIX_X, part.y * Map.MAP_OFFSET + Map.MAP_FIX_Y, Map.TILE_SIZE, Map.TILE_SIZE);
            sprite.draw(spriteBatch);

        }
    }

    public void update(float delta) {
        if (tick > MAX_TICK) {
            tick = 0;
            move();
        } else {
            tick += delta;
        }

    }

    private void move() {
        Part headPart = body.get(body.size() - 1);
        switch (direction){
            case UP:
                body.add(new Part(headPart.x, headPart.y + 1));
                break;
            case DOWN:
                body.add(new Part(headPart.x, headPart.y - 1));
                break;
            case RIGHT:
                body.add(new Part(headPart.x + 1, headPart.y));
                break;
            case LEFT:
                body.add(new Part(headPart.x - 1, headPart.y));
                break;
        }
        body.remove(0);
    }

    public void input() {
        if (Gdx.input.isKeyPressed(Input.Keys.UP) && direction != Direction.DOWN) {
            direction = Direction.UP;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction != Direction.UP) {
            direction = Direction.DOWN;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction != Direction.RIGHT) {
            direction = Direction.LEFT;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction != Direction.LEFT) {
            direction = Direction.RIGHT;
        }
    }


}
