package com.jose96;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Fish {

    Texture fishTexture;
    Sprite fishSprite;

    Map map;
    Part fishPart;  //Declaramos una parte que va ser el pez

    public Fish(Map map) {  //Al constructor le pasamos el mapa
        this.map = map;  //e igualamos el de la clase con el que nos pasan

        fishTexture = new Texture("fishPink.png");  //Asigamos la textura y el sprite
        fishSprite = new Sprite(fishTexture);
//        map.map[randomNumber()][randomNumber()] = 2;  //Innecesario
        fishPart = new Part(randomNumber(), randomNumber());
        //Como fish es una parte le pasamos componentes como hacíamos con la serpiente
    }

    public void respawnFish(ArrayList<Part> body) { //Método que reasigna a las componentes del pez valores para que aparwzca en otro lado
//        fishPart = new Part(randomNumber(), randomNumber());  //Más ineficiente, habría que instanciarlo muchas veces
        boolean isTouchingSnake;  //este boolean se encarga de que el pez no spawne en la snake
        do {  //le damos valores a leatorios a la serpiente y establecemos que no se tocan (eso es lo que pone en el do)
            fishPart.x = randomNumber();
            fishPart.y = randomNumber();
            isTouchingSnake = false;
            for (Part part : body) {  //Comprobamos en un for each que ninguna parte toque la fruta segun spawne
                if (part.x == fishPart.x && part.y == fishPart.y) {  //Si alguna parte la toca, el boolean se vuelve true
                    isTouchingSnake = true;
//                    System.out.println("El pez toca a la serpiente asi que se vuelve a crear");  //Esto era para verlo por pantalla
                }
            }
        } while (isTouchingSnake);  //Repetimos el ciclo anterior mientras alla tocamiento de la fruta y la snake
    }

    public int randomNumber() {  //Método que genera números aleatorios entre el 1 y el 49
//        int i = (int) Math.round(Math.random());
//
//        while(i < 1 || i > 49) {
//            i = (int) Math.round(Math.random());  //Comentado así por usar un camino corto (ctrl + e)
//        }
//        return i;
        /*Para obtener un num entre 1 y 49--> num random entre 0 y 1 por (50 - 2) == num random entre 48 y 0
        a ese num entre 48 y 1 le sumamos 1 y asi es entre 1 y 49. Más sencillo que con bucles (como hice yo arriba) */
        return (int)( (Math.random()* (Map.MAP_SIZE - 2)) + 1);  //Más efectivo

    }


    public void draw(SpriteBatch spriteBatch) {  //Método que dibuja al pez
        //Se emplean las mismas correciones que con la serpiente pero con los componentes del pez
        fishSprite.setBounds(fishPart.x * Map.MAP_OFFSET + Map.MAP_FIX_X, fishPart.y * Map.MAP_OFFSET + Map.MAP_FIX_Y, Map.TILE_SIZE, Map.TILE_SIZE);
        fishSprite.draw(spriteBatch);
    }


}
