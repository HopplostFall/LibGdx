package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Kimpan on 2015-01-08.
 */
public class Player {

    int x,y,goToX,goToY;
    Texture texture;
    Rectangle rectangle;


    public Player(int x, int y, Texture texture)
    {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.rectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());

    }
    public void Update(int camX,int camY)
    {

        if(x != goToX)
        {
            if(x > goToX)
            {
                x -= 2;
            }else
            {
                x += 2;
            }
        }
        if(y != goToY)
        {
            if(y > goToY)
            {
                y -= 2;
            }else
            {
                y += 2;
            }
        }
        if(Gdx.input.isTouched()) {
            goToX = (Gdx.input.getX() + x);
            goToY = (Gdx.input.getY() - Gdx.graphics.getHeight()) * -1;
        }






    }
    public void Draw(SpriteBatch spriteBatch)
    {
        spriteBatch.draw(texture,x,y,
                2, 2,
                texture.getWidth(),texture.getHeight(),1.0f,1.0f,
                0,0,0,texture.getWidth(),texture.getHeight(),
                false,false);
    }
}
