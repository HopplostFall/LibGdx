package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Kimpan on 2015-01-09.
 */
public class Tile {

    protected Texture texture;
    protected int x,y;

    public Tile(int x,int y,Texture texture)
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
    }
    public void Draw(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(texture,x,y);
    }
}
