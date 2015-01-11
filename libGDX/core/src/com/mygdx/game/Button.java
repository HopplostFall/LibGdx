package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Kim on 2015-01-11.
 */
public class Button {

    Texture textureNotActivated, textureActivated;
    Rectangle rectangle;
    int x,y;
    Boolean activated = false;
    int ID;

    public Button(int x,int y, int ID)
    {
        this.textureNotActivated =  new Texture("ButtonNotActivated.png");
        this.textureActivated =  new Texture("ButtonActivated.png");
        this.x = x;
        this.y = y;
        this.ID = ID;
        this.rectangle = new Rectangle(x,y,textureActivated.getWidth(),textureActivated.getHeight());
    }
    public void Draw(SpriteBatch spriteBatch)
    {
        if(activated) {
            spriteBatch.draw(textureActivated, x, y);
        }else
        {
            spriteBatch.draw(textureNotActivated, x, y);
        }
    }
    public void ButtonPressed()
    {
        activated = true;
    }
    public void ButtonReleased()
    {
        activated = false;
    }

}
