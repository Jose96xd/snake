package com.jose96;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {

	SpriteBatch batch; //Canvas donde se pinta
    Map map;
    Snake snake;
    //Declaracion pez
    Fish fish;
    //Declaracion pez


	@Override
	public void create () {
		batch = new SpriteBatch();
		map = new Map();
		//Constructor prueba pez
		fish = new Fish(map);
		//Constructor prueba pez
		//Instanciar en orden de uso
		snake = new Snake(map, fish);


		for (int i = 0; i < 100; i++) {
			System.out.println(	fish.randomNumber());
		}

	}

	@Override
	public void render () {  //Esto se repite periódicamente
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		map.draw(batch);
		snake.draw(batch);
		//Prueba pez
		fish.draw(batch);
		//Prueba pez
		batch.end();

		snake.update(Gdx.graphics.getDeltaTime());
		snake.input();
	}
	
	@Override
	public void dispose () {  //Ni idea
		batch.dispose();
	}
}
