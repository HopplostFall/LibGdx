package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.awt.Button;

import javax.swing.JButton;



public class MyGdxGame extends Game {
	SpriteBatch spritebatch;
	Texture img;
    Player player;
    Batch batch;
    ButtonHandler buttonHandler;
    Button button;
    TileHandler tileHandler;
    OrthographicCamera cam;



	@Override
	public void create () {

        spritebatch = new SpriteBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0, 0, 0);
        cam.update();

		img = new Texture("character.png");
        player = new Player(100,100,img);
        tileHandler = new TileHandler();








    }


	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.Update((int)cam.position.x,(int)cam.position.y);
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(player.x, player.y, 0);
        cam.update();
        spritebatch.setProjectionMatrix(cam.combined);
        spritebatch.begin();
        tileHandler.Draw(spritebatch);
		player.Draw(spritebatch);
        spritebatch.end();




	}
}
