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
    Fish fish;


    ArrayList<Part> body = new ArrayList<>(); //Declarar e instanciar pero sin rellenar
    //Esto es una lista en la que guardo y voy añadiendo las partes de la serpiente

    private static final float MAX_TICK = 0.05f;
    private float tick;
    private Direction direction = Direction.UP;


    public Snake(Map map, Fish fish) { //Constructor que recibe parametro map
        this.map = map; //Copio la variable map de la clase igualandolo al recibido
        this.fish = fish;

        headTexture = new Texture("slimeBlock.png");  //Asigno a las texturas la imágen
        bodyTexture = new Texture("slimeBlock_hit.png");

        headSprite = new Sprite(headTexture);  //Asigno a los sprits las textoras  (instanciar?)
        bodySprite = new Sprite(bodyTexture);

        //Aquí añadimos 4 partes
        //A lista body, añado (add) nueva parte (llamada a constructor Part) y le asigno valores a x e y
        body.add(new Part(10, 10));
        body.add(new Part(11, 10));  // todo lo de abajo
        body.add(new Part(12, 10)); //Cómo llegan estas x e ys al array para saber dónde esan???
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
        body.add(new Part(23, 10));
        body.add(new Part(24, 10));
        body.add(new Part(25, 10));
    }

    public void draw(SpriteBatch spriteBatch){
        for (int i = 0; i < body.size(); i++) {
            Part part = body.get(i);
            Sprite sprite;

            if (i == body.size() - 1) {
                sprite = headSprite;
            } else {
                sprite = bodySprite;

                //Colores alternantes entre las piezas
                if (i % 2 == 0) {
                    sprite.setColor(0.8f, 1, 0.5f, 1);
                } else {
                    sprite.setColor(0.2f, 0.8f, 0.2f, 1);
                }
                //Colores alternantes entre las piezas
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
        Part bodyPart = body.get(0);  //Para crear más culos

        boolean collision = false;


        switch (direction){
            case UP:
                for (int i = 0; i < (body.size() -1); i++) {
                    if ( headPart.x == body.get(i).x && (headPart.y + 1) == body.get(i).y) {
                        collision = true;
                    }
                }
                if (map.map[headPart.x][headPart.y + 1] == 1) {
                    collision = true;
                }
                if (!collision) {
                    body.add(new Part(headPart.x, headPart.y + 1));
                }
                break;

            case DOWN:
                for (int i = 0; i < (body.size() -1); i++) {
                    if ( headPart.x == body.get(i).x && (headPart.y - 1) == body.get(i).y) {
                        collision = true;
                    }
                }
                if (map.map[headPart.x][headPart.y - 1] == 1) {
                    collision = true;
                }
                if (!collision) {
                    body.add(new Part(headPart.x, headPart.y - 1));
                }
                break;

            case RIGHT:
                for (int i = 0; i < (body.size() -1); i++) {
                    if ( (headPart.x + 1) == body.get(i).x && headPart.y == body.get(i).y) {
                        collision = true;
                    }
                }
                if (map.map[headPart.x + 1][headPart.y] == 1) {
                    collision = true;
                }
                if (!collision) {
                    body.add(new Part(headPart.x + 1, headPart.y));
                }
                break;

            case LEFT:
                for (int i = 0; i < (body.size() -1); i++) {
                    if ( (headPart.x - 1) == body.get(i).x && headPart.y == body.get(i).y) {
                        collision = true;
                    }
                }
                if (map.map[headPart.x - 1][headPart.y] == 1) {
                    collision = true;
                }
                if (!collision) {
                    body.add(new Part(headPart.x - 1, headPart.y));
                }
                break;

        }
        boolean touchFish = headPart.x == fish.fishPart.x && headPart.y == fish.fishPart.y;
        if (!collision && !touchFish){
            body.remove(0);

        }
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
//    Zona de prueba;



}
