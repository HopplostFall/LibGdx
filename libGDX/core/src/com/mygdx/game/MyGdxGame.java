package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.Button;

import java.io.IOException;

public class MyGdxGame extends Game {
	SpriteBatch spritebatch;
	Texture img;
    Player player;
    Batch batch;
    Button button;
    TileHandler tileHandler;
    OrthographicCamera cam;
    Connect connect;
    public static int myID;

    public static final int HeightToUse = 480;
    public static final int WidthToUse = 640;
    public static final float AspectRatio = (float)WidthToUse/(float)HeightToUse;

	@Override
	public void create () {
        try {
            connect = new Connect();

        } catch (IOException e) {
            e.printStackTrace();
        }
        spritebatch = new SpriteBatch();
        cam = new OrthographicCamera(WidthToUse, HeightToUse);
        cam.setToOrtho(false, WidthToUse, HeightToUse);
        cam.position.set(0, 0, 0);
        cam.update();

		img = new Texture("character.png");
        player = new Player(200,200,img);
        tileHandler = new TileHandler(connect);

    }
    public void Update()
    {
        player.Update(tileHandler.walls,tileHandler.doors,tileHandler.buttons);
        CamUpdate();
    }
	@Override
	public void render () {
        Update();
        Gdx.graphics.setDisplayMode(WidthToUse, HeightToUse, true);
        Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spritebatch.setProjectionMatrix(cam.combined);
        spritebatch.begin();
        tileHandler.Draw(spritebatch,player.x - Gdx.graphics.getWidth(),player.y - Gdx.graphics.getHeight());
		player.Draw(spritebatch);
        spritebatch.end();

	}
    public void CamUpdate()
    {
        cam.setToOrtho(false, WidthToUse, HeightToUse);
        cam.position.set(player.x, player.y, 0);
        cam.update();
    }

}

