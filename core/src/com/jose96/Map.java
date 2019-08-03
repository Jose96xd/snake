package com.jose96;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Map {

    private static final int MAP_SIZE = 50;         //Instancio y declaro muchas variables privadas
    public static final float MAP_OFFSET = 15;
    public static final int TILE_SIZE = 14;
    public static final float MAP_FIX_X = 125;
    public static final float MAP_FIX_Y = 30;


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
            map[MAP_SIZE - 1][i] = 1;
        }
    }

    public void draw(SpriteBatch spriteBatch){  //Función/método de la clase map para dinujar

        for (int i = 0; i < MAP_SIZE; i++) {//Bucle para pintar paredes o "suelos"
            for (int j = 0; j < MAP_SIZE; j++) {
                switch (map[i][j]){
                    case 0:
                        wallSprite.setColor(0,0.5f,0,1);//Si array == 0 --> suelo
                        break;
                    case 1:
                        wallSprite.setColor(1,0,0,1);//si array == 1 --> pared
                        break;
                }
                wallSprite.setBounds(i * MAP_OFFSET + MAP_FIX_X, j * MAP_OFFSET + MAP_FIX_Y, TILE_SIZE, TILE_SIZE);
                wallSprite.draw(spriteBatch);
                //Aqui le decimos a los sprits dónde van en la pantalla y cuan grandes son
            }
        }

    }


}
