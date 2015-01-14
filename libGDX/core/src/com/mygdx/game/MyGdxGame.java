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
    ReconnectButton rc;
    public static int myID;

    public static final int HeightToUse = 480;
    public static final int WidthToUse = 640;
    public static final float AspectRatio = (float)WidthToUse/(float)HeightToUse;

	@Override
	public void create () {
        Connect();
        spritebatch = new SpriteBatch();
        cam = new OrthographicCamera(WidthToUse, HeightToUse);
        cam.setToOrtho(false, WidthToUse, HeightToUse);
        cam.position.set(0, 0, 0);
        cam.update();
        rc = new ReconnectButton();


    }
    public void Update()
    {

        tileHandler.updatePlayer();

        CamUpdate();
    }
	@Override
	public void render () {
        if(tileHandler != null && connect.clientSocket.isConnected()) {
            if (tileHandler.player != null) {
                Update();
                Gdx.graphics.setDisplayMode(WidthToUse, HeightToUse, true);
                Gdx.gl.glClearColor(1, 1, 1, 1);
                Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
                spritebatch.setProjectionMatrix(cam.combined);
                spritebatch.begin();
                tileHandler.Draw(spritebatch);
                spritebatch.end();
            }
        }else {
            spritebatch.begin();
            if (rc.Reconnect()) {
                Connect();
            }
            rc.Draw(spritebatch);
            spritebatch.end();
        }

	}
    public void CamUpdate()
    {
        cam.setToOrtho(false, WidthToUse, HeightToUse);
        cam.position.set(tileHandler.player.x, tileHandler.player.y, 0);
        cam.update();
    }
    public void Connect(){
        try {
            connect = new Connect();
            tileHandler = new TileHandler(connect);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

