package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Kim on 2015-01-11.
 */
public class Door {

        Texture texture;
        int x,y;
        Boolean activated = false;
        int ID;
        int srcWidth,srcHeight;
        Rectangle rectangle;

        public Door(int x,int y, int ID, Texture texture)
        {
            this.texture =  texture;
            this.x = x;
            this.y = y;
            this.ID = ID;
            srcWidth = texture.getWidth();
            srcHeight = texture.getHeight();
            this.rectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());

        }
        public void Draw(SpriteBatch spriteBatch)
        {

                spriteBatch.draw(texture, x, y,0,0,srcWidth,srcHeight);

        }


    }


