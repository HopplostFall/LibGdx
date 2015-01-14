package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
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


    public Player(int x, int y)
    {
        this.x = x;
        this.y = y;
        goToY = y;
        goToX = x;
        this.texture = new Texture("character.png");
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

                //Check playercollissions against Walls
                for(int i = 0; i < walls.size();i++)
                {
                    Rectangle wallrectangle = walls.get(i).rectangle;

                    if (tempPlayer.overlaps((wallrectangle))) {
                        collisionFound = true;
                        break;
                    }
                }
                //Check playercollissions against Doors
                for (int i = 0; i < doors.size();i++) {
                    Rectangle doorrectangle = doors.get(i).rectangle;

                    if (tempPlayer.overlaps((doorrectangle))) {
                        collisionFound = true;
                        break;
                    }
                }
                if(!collisionFound)
                {
                    x-=2;
                }

            }else           //If need to move right
            {
                Rectangle tempPlayer = new Rectangle(x +5 ,y,rectangle.width,rectangle.height);
                //Check playercollissions against Walls
                for(int i = 0; i < walls.size();i++)
                    {
                        Rectangle wallrectangle = walls.get(i).rectangle;

                        if (tempPlayer.overlaps((wallrectangle))) {
                            collisionFound = true;
                            break;
                        }
                    }
                //Check playercollissions against Doors
                    for (int i = 0; i < doors.size();i++) {
                    Rectangle doorrectangle = doors.get(i).rectangle;


                    if (tempPlayer.overlaps((doorrectangle))) {
                        collisionFound = true;
                        break;
                    }
                }
                    if(!collisionFound)
                    {
                        x+=2;
                    }

            }
        }
        if(y != goToY)   //If not at goal position Y
        {
            Boolean collisionFound = false;

            rectangle = new Rectangle(x,y,texture.getWidth(),texture.getHeight());
            if(x != goToX)  //If not at goal position X
            {

                if(x > goToX)  //If need to move left
                {
                    Rectangle tempPlayer = new Rectangle(x -8 ,y,rectangle.width,rectangle.height);
                    if(!CheckCollision(tempPlayer,doors,walls)){
                        x-=5;
                    }
                }else           //If need to move right
                {
                    Rectangle tempPlayer = new Rectangle(x +8 ,y,rectangle.width,rectangle.height);
                    if(!CheckCollision(tempPlayer,doors,walls))
                    {
                        x+=5;
                    }
                }
            }
            if(y != goToY)   //If not at goal position Y
            {
                if (y > goToY) //If need to move down
                {
                    Rectangle tempPlayer = new Rectangle(x, y - 8, rectangle.width, rectangle.height);
                    if(!CheckCollision(tempPlayer,doors,walls))
                    {
                        y -= 5;
                    }
                } else           //If need to move up
                {
                    Rectangle tempPlayer = new Rectangle(x, y + 8, rectangle.width, rectangle.height);
                    if(!CheckCollision(tempPlayer,doors,walls)) {
                        y += 5;
                    }
                }
            }
        }

        //Handle input from player
        if(Gdx.input.isTouched())
        {
            float tmpX = Gdx.input.getX();
            float tmpXX = Gdx.graphics.getWidth();
            float tempInX = (tmpX / tmpXX);

            float tmpY = Gdx.input.getY();
            float tmpYY = Gdx.graphics.getHeight();
            float tempInY = (tmpY / tmpYY);

            int xInput = (int)(tempInX * MyGdxGame.WidthToUse);
            int diffX = xInput - x;
            int yInput = (int)(tempInY * MyGdxGame.HeightToUse);
            int diffY = yInput - y;
            goToX = (xInput+ x)-(MyGdxGame.WidthToUse/2);
            goToY = (((yInput - MyGdxGame.HeightToUse) * -1)+y) - (MyGdxGame.HeightToUse/2);
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
