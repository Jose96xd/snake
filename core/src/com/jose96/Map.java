package com.jose96;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    public static final int MAP_SIZE = 20;         //Instancio y declaro muchas variables privadas
    public static final float MAP_OFFSET = 15 * 2;  //
    public static final int TILE_SIZE = 14 * 2;  //Tamaño de los cuadraditos
    public static final float MAP_FIX_X = 200;  //Ajuste del mapa en el canvas: eje x
    public static final float MAP_FIX_Y = 100;  //Ajuste del mapa en el canvas: eje y


    int[][] map = new int[MAP_SIZE][MAP_SIZE]; //Creo un array de ints de dimensiones fijas

    Texture wallTexture;  //Declaro una textura y una sprit
    Sprite wallSprite;


    public Map() { //Constructor de mapas

        wallTexture = new Texture("grassBlock_hit.png");
        wallSprite = new Sprite(wallTexture);  //Asigno imágen a la textura y textura al sprite

        for (int i = 0; i < MAP_SIZE; i++) {  //Bucle de rellenado para saber que es pared y que no
            map[i][0] = 1;
            map[i][MAP_SIZE - 1] = 1;
            map[0][i] = 1;
            map[MAP_SIZE - 1][i] = 1;  //Asigna 1 a las paredes pora dibujar en ellas el muro
        }
    }


    public void draw(SpriteBatch spriteBatch){  //Función de la clase map para dinujar

        for (int i = 0; i < MAP_SIZE; i++) {//Bucle para pintar paredes y suelos
            for (int j = 0; j < MAP_SIZE; j++) {
                switch (map[i][j]){  //Empleamos un for anidado para reccorer to-do el mapa y pintar to-do
                    case 0:
                        wallSprite.setColor(0,0.5f,0,1);//Si array == 0 --> suelo
                        break;
                    case 1:
                        wallSprite.setColor(1,0,0,1);//si array == 1 --> pared
                        break;
                }
                //Lo de abajo hace dos cosas. 1-Ajusta el mapa al fondo. 2-Lo dibuja
                wallSprite.setBounds(i * MAP_OFFSET + MAP_FIX_X, j * MAP_OFFSET + MAP_FIX_Y, TILE_SIZE, TILE_SIZE);
                wallSprite.draw(spriteBatch);
            }
        }

    }


}
