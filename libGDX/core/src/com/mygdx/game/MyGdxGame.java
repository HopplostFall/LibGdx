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

        tileHandler = new TileHandler(connect);

    }
    public void Update()
    {
<<<<<<< HEAD
        tileHandler.updatePlayer();
=======
        player.Update(tileHandler.walls,tileHandler.doors,tileHandler.buttons);
>>>>>>> origin/master
        CamUpdate();
    }
	@Override
	public void render () {
        Update();
        Gdx.graphics.setDisplayMode(WidthToUse, HeightToUse, true);
        Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(tileHandler.player != null) {
            spritebatch.setProjectionMatrix(cam.combined);
            spritebatch.begin();
            tileHandler.Draw(spritebatch);
            spritebatch.end();
        }

	}
    public void CamUpdate()
    {
<<<<<<< HEAD
        cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(tileHandler.player.x, tileHandler.player.y, 0);
=======
        cam.setToOrtho(false, WidthToUse, HeightToUse);
        cam.position.set(player.x, player.y, 0);
>>>>>>> origin/master
        cam.update();
    }

}

