package com.jose96;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Snake {

    public enum Direction {  //Enumerado que contiene las distintas direcciones
        UP, DOWN, LEFT, RIGHT
    }

    Texture headTexture;  //Declaro texturas y prits
    Texture bodyTexture;

    Sprite headSprite;
    Sprite bodySprite;

    Map map;  //Declaraciones
    Fish fish;



    ArrayList<Part> body = new ArrayList<>(); //Declarar e instanciar pero sin rellenar
    //Esto es una lista en la que guardo y voy añadiendo las partes de la serpiente

    private float maxTick = 0.2f;
    private float tick;  //Tick y MAX_TICK son para hacer el renderizado y los pasos de tiempo
    private Direction direction = Direction.UP;
    private Direction newDirection = Direction.UP;
    public int score;  //Declaración de la variable puntuación

    //todo revisar
    //Boolean para verificar muerte
    public boolean dead;
    //Boolean para verificar muerte
    //todo revisar


    public Snake(Map map, Fish fish) { //Constructor que recibe parametro map y fish
        this.map = map; //Copio la variable map de la clase igualandolo al recibido
        this.fish = fish;

        headTexture = new Texture("slimeBlock.png");  //Asigno a las texturas la imágen
        bodyTexture = new Texture("slimeBlock_hit.png");

        headSprite = new Sprite(headTexture);  //Asigno a los sprits las textoras  (instanciar?)
        bodySprite = new Sprite(bodyTexture);

        //A lista body, añado (add) nueva parte (llamada a constructor Part) y le asigno valores a x e y
        body.add(new Part(10, 10));
        body.add(new Part(11, 10));
        body.add(new Part(12, 10));
        body.add(new Part(13, 10));
        body.add(new Part(14, 10));
        body.add(new Part(15, 10));
        body.add(new Part(16, 10));
        body.add(new Part(17, 10));
//        body.add(new Part(18, 10));
//        body.add(new Part(19, 10));
//        body.add(new Part(20, 10));
    }


    public void draw(SpriteBatch spriteBatch){  //Método de dibujo para la serpiente
        for (int i = 0; i < body.size(); i++) {  //Con el for recorremos la serpiente
            Part part = body.get(i);  //Declaramos e instanciamos un part que corresponde a cada uno de los parts de la lista
            Sprite sprite;  //Declaramos un sprite

            if (i == body.size() - 1) {  //Si la parte es la cabeza dibujamos la cabeza
                sprite = headSprite;
            } else {  //si no, pues un cuerpo
                sprite = bodySprite;

                //Colores alternantes entre las piezas
                if (i % 2 == 0) {
                    sprite.setColor(0.8f, 1, 0.5f, 1);
                } else {
                    sprite.setColor(0.2f, 0.8f, 0.2f, 1);
                }
                //Colores alternantes entre las piezas
            }
            //Aquí se ajusta la serpiente al mapa y al fondo para que se mueva correctamente
            sprite.setBounds(part.x * Map.MAP_OFFSET + Map.MAP_FIX_X, part.y * Map.MAP_OFFSET + Map.MAP_FIX_Y, Map.TILE_SIZE, Map.TILE_SIZE);
            sprite.draw(spriteBatch);

        }
    }


    public void update(float delta) {  //Este método es un ciclo que controla cada cuanto se mueve la snake
        if (tick > maxTick) {  //Aquí con una variable (tick) vamos moviendo la snake cada vez que supera el valor que quramos
            tick = 0;          //A mayor maxTick más lenta irá la snake
            move();
        } else if (!dead) {
            tick += delta;  //Cada vez que no ha pasado suficiente tiempo para que la serpiente se mueva le sumamos delta(algo de fps fijo)
        }

    }

    private void move() {  //Método de movimiento serpentil
        Part headPart = body.get(body.size() - 1);  //Declaramos e iniciamos una parte llamada "headPart" que corresponde a la cabeza

        boolean collision = false;  //Declaramos e instanciamos un boolean que corresponde a si hay o no colisión
        direction = newDirection;

        switch (direction){  //Usamos un switch que hace referencia aun enumerado del principio
            case UP:
                for (int i = 0; i < (body.size() -1); i++) {
                    //Comprueba toda la serpiente para que no se vaya a chocar contra si misma
                    if ( headPart.x == body.get(i).x && (headPart.y + 1) == body.get(i).y) {
                        collision = true;
                    }
                }
                //Comprueba que dentro de map (del array map) no haya un 1 que correspondería a un muro
                //En caso de haberlo collision se vuelve true
                if (map.map[headPart.x][headPart.y + 1] == 1) {
                    collision = true;
                }
                if (!collision) {//Si no hay colisión se crea una nueva cabeza y por ende se avanza
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
        //todo revisar
        //Prueba uno del boolean muerte como método de matar la partida
        if (collision) {
            dead = true;
        }
        //Prueba uno del boolean muerte como método de matar la partida
        //todo revisar

        //Instanciamos un boolean que corresponde a comerse el pez
        boolean touchFish = headPart.x == fish.fishPart.x && headPart.y == fish.fishPart.y;
        if (touchFish) {  //con el boolean hacemos que el pez reaparezca
            fish.respawnFish(body);

            //Subida de la puntuación al coger el pescadito
            score++;
            //Subida de la puntuación al coger el pescadito
            //Incremento de un 10% de la velocidad cada vez que se atrapa el pez
            maxTick -= maxTick * 0.1f;
        }
        if (!collision && !touchFish){  //Si no choca y no se come el pez pierde el culo
            body.remove(0);

        }
    }

    public void input() {  //Este método comprueba el imput de las flechas y les asigna una dirección
        //Para poder cambiar la dirreción (girar) debe ser introducida la dirección deseada Y NO IR ACTUALMENTE EN LA DIRECCIÓN CONTRARIA
        Part headPart = body.get(body.size() - 1);
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && direction != Direction.DOWN && map.map[headPart.x][headPart.y + 1] != 1) {
            newDirection = Direction.UP;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN) && direction != Direction.UP && map.map[headPart.x][headPart.y - 1] != 1) {
            newDirection = Direction.DOWN;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && direction != Direction.RIGHT && map.map[headPart.x - 1][headPart.y] != 1) {
            newDirection = Direction.LEFT;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && direction != Direction.LEFT && map.map[headPart.x + 1][headPart.y] != 1) {
            newDirection = Direction.RIGHT;
        }
    }


}
