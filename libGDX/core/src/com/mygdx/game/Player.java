package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

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
        goToY = y;
        goToX = x;
        this.texture = texture;
        this.rectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());

    }

    public void Update(ArrayList<Tile>walls,ArrayList<Door>doors,ArrayList<Button>buttons)
    {
        rectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());

        if(x != goToX)  //If not at goal position X
        {
            Boolean collisionFound = false;

            if(x > goToX)  //If need to move left
            {
                Rectangle tempPlayer = new Rectangle(x -5 ,y,rectangle.width,rectangle.height);

                if(!CheckCollision(tempPlayer,doors,walls)){
                    x-=2;
                }

            }else           //If need to move right
            {
                Rectangle tempPlayer = new Rectangle(x +5 ,y,rectangle.width,rectangle.height);

                if(!CheckCollision(tempPlayer,doors,walls))
                {
                    x+=2;

                }

            }
        }
        if(y != goToY)   //If not at goal position Y
        {

            if (y > goToY) //If need to move down
            {
                Rectangle tempPlayer = new Rectangle(x, y - 5, rectangle.width, rectangle.height);
                if(!CheckCollision(tempPlayer,doors,walls))
                {
                    y -= 2;
                }
            } else           //If need to move up
            {
                Rectangle tempPlayer = new Rectangle(x, y + 5, rectangle.width, rectangle.height);
                if(!CheckCollision(tempPlayer,doors,walls)) {
                    y++;
                }
            }

        }

        //Handle input from player
        if(Gdx.input.isTouched())
        {
            int xInput = Gdx.input.getX();
            int diffX = xInput - x;
            int yInput = Gdx.input.getY();
            int diffY = yInput - y;
            goToX = (xInput+ x)-(Gdx.graphics.getWidth()/2);
            goToY = (((yInput - Gdx.graphics.getHeight()) * -1)+y) - (Gdx.graphics.getHeight()/2);
            goToX = goToX/2;
            goToX = goToX*2;
            goToY = goToY/2;
            goToY = goToY*2;
        }

            for (int i = 0; i < buttons.size();i++)
            {
                Rectangle buttonRectangle = buttons.get(i).rectangle;
                if (rectangle.overlaps((buttonRectangle))) {
                    buttons.get(i).ButtonPressed();
                }else
                {
                    buttons.get(i).ButtonReleased();
                }
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
    public boolean CheckCollision(Rectangle tempPlayer,ArrayList<Door>doors,ArrayList<Tile>walls)
    {
        for(int i = 0; i < walls.size();i++)
        {
            Rectangle wallRectangle = walls.get(i).rectangle;

            if (tempPlayer.overlaps((wallRectangle))) {
                return true;
            }
        }
        //Check playercollissions against Doors
        for (int i = 0; i < doors.size();i++) {
            Rectangle doorRectangle = doors.get(i).rectangle;

            if (tempPlayer.overlaps((doorRectangle))) {
                return true;
            }
        }
        return false;
    }
}
