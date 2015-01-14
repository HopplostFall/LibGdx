package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kimpan on 2015-01-14.
 */
public class ReconnectButton {

    Texture texture;
    int x,y;
    public ReconnectButton()
    {
        texture = new Texture("Reconnect.png");
    }
    public boolean Reconnect()
    {
        if(Gdx.input.isTouched()) {
            return true;
        }
        return false;
    }
    public void Draw(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(texture,x,y);
    }
}
