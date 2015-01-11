package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Button;



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
    public void Update()
    {

        player.Update(tileHandler.walls);
        CamUpdate();
    }
	@Override
	public void render () {
        Update();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spritebatch.setProjectionMatrix(cam.combined);
        spritebatch.begin();
        tileHandler.Draw(spritebatch);
		player.Draw(spritebatch);
        spritebatch.end();

	}
    public void CamUpdate()
    {
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(player.x, player.y, 0);
        cam.update();
    }
}
