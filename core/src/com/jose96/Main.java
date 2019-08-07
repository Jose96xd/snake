package com.jose96;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends ApplicationAdapter {

	SpriteBatch batch; //Canvas donde se pinta
    Map map;  //Declaración del mapa
    Snake snake;  //Declaración de la serpiente
    Fish fish;  //Declaración del pez

	BitmapFont titleBitmapFont;  //Declaración de un mapa de bits utilizable para: Título
	BitmapFont creatorsBitmapFont;  //Declaración de un mapa de bits utilizable para: Creadores
	BitmapFont scoreBitmapFont;  //Declaración de un mapa de bits utilizable para: Puntuación
	BitmapFont inputsBitmapFont;  //Declaración de un mapa de bits utilizable para: Controles
	//Pantalla para cuando muera todo implementar la muerte
	BitmapFont deadBitmapFont;  //Declaración del mapa de bits que aparecere al morir
	//Pantalla para cuando muera todo implementar la muerte


	@Override
	public void create () {
		batch = new SpriteBatch();  //Instanciamiento del canvas
		map = new Map();  //Instanciamiento del mapa
		fish = new Fish(map);  //Al constructor pez se le pasa como arg el mapa
		snake = new Snake(map, fish);  //Instanciar en orden de uso


		titleBitmapFont = new BitmapFont();  //Instanciamiento del mapa de bits: Título
		titleBitmapFont.setColor(0.99f, 0.0f, 0.4f, 1);  //Coloreamos el título
		titleBitmapFont.getData().setScale(4f);//Accedemos a la data para set (cambiar) el tamaño del título

		creatorsBitmapFont = new BitmapFont();  //Instanciamiento del mapa de bits: Creadores
		creatorsBitmapFont.setColor(0.9f,0.3f,0.1f,1);  //Color de los creadores

		scoreBitmapFont = new BitmapFont();  //Instanciamiento del mapa de bits: Puntuación
		scoreBitmapFont.setColor(0,0.8f,0.2f,1);  //Colores del marcador

		inputsBitmapFont = new BitmapFont();  //Instanciamiento del mapa de bits: Controles
		inputsBitmapFont.setColor(0.0f,0.8f,0.2f,1);  //Adivina de que es este color genio

		deadBitmapFont = new BitmapFont();  //Instanciamiento del mensaje de muerte
		deadBitmapFont.setColor(0.9f,0.6f,0.1f,1);  //Color del mensaje de muerte
		deadBitmapFont.getData().setScale(4.5f);  //Agrandamiento del mensaje de muerte
	}


	@Override
	public void render () {  //Esto se repite periódicamente
		Gdx.gl.glClearColor(0, 0.5f, 0.5f, 1);  //Color del fondo
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();  //Inicio canvas
		map.draw(batch);  //Se dibuja en el canvas el mapa
		snake.draw(batch);  //Se dibuja en el canvas la serpiente
		fish.draw(batch);  //se dibuja en el mapa el pez


		//Nombre del juego en el centro arriba
		titleBitmapFont.draw(batch, ("SNAKE GAME"  ),( Gdx.graphics.getWidth() / 2 - 200), Gdx.graphics.getHeight() - 20);
		//Autores abajo a la derecha
		creatorsBitmapFont.draw(batch, ("By: Lavamancer and Jose96"  ),( Gdx.graphics.getWidth() -300), 60);
		//Puntuación dinámica
		scoreBitmapFont.draw(batch, ("You have " + snake.score + " points"  ),( Gdx.graphics.getWidth() - 200), Gdx.graphics.getHeight() - 20);
		//Controles arriba a la izquierda
		inputsBitmapFont.draw(batch, ("Controls:\n\nArrows-->Move\nR--->Restart"  ),50, Gdx.graphics.getHeight() - 20);

		//Pintar la pantalla de muerte si se cumple la condición dead
		if(snake.dead) {
			deadBitmapFont.draw(batch, "YOU ARE DEAD!!!",(Gdx.graphics.getWidth() / 2 - 275),(Gdx.graphics.getHeight() / 2 + 100));
			deadBitmapFont.draw(batch, "¨R¨ TO RESTART",(Gdx.graphics.getWidth() / 2 - 235),(Gdx.graphics.getHeight() / 2 - 50));
		}
		//Pintar la pantalla de muerte si se cumple la condición dead



		batch.end();  //Fin del canvas

		snake.update(Gdx.graphics.getDeltaTime());  //Llamada a método update al que se le pasa un tiempo
		snake.input();  //lamada al método input

		if (Gdx.input.isKeyPressed(Input.Keys.R)) {  //Este if permite reiniciar el juego con la "R"
			map = new Map();  //Se reinstancia el mapa
			fish = new Fish(map);  //Se reinstancia el pez al que se le pasa el nuevo mapa
			snake = new Snake(map,fish);  //Se reinstancia la serpiente con el nuevo pez y mapa

		}
	}


	@Override
	public void dispose () {  //Ni idea
		batch.dispose();
		bi
	}
}
