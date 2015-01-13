package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

/**
 * Created by Kimpan on 2015-01-09.
 */
public class TileHandler {

    ArrayList<Tile> floors = new ArrayList();
    ArrayList<Tile> walls = new ArrayList();
    ArrayList<Button> buttons = new ArrayList();
    ArrayList<Door> doors = new ArrayList();


    public TileHandler()
    {
        int y = 0;
        int x = 0;
        Texture textureForPlacement;
        FileHandle handle = Gdx.files.internal("textFile/map.txt");
        String lines = handle.readString();
        byte[] bytes = handle.readBytes();

        for(int i = 0; i < bytes.length;i++)
        {
            if(bytes[i] == 'w') //Wall
            {
                textureForPlacement = new Texture("wall.jpg");
                Tile wall = new Tile(x*100,450-(100*y),textureForPlacement);
                walls.add(wall);
                x++;
            }else if(bytes[i] == 'f') //Floor
            {
                textureForPlacement = new Texture("Floor.jpg");
                Tile floor = new Tile(x*100,450-(100*y),textureForPlacement);
                floors.add(floor);
                x++;
            }else if(bytes[i] == 'b') //Button
            {
                textureForPlacement = new Texture("Floor.jpg");
                        Tile floor = new Tile(x*100,450-(100*y),textureForPlacement);
                        floors.add(floor);
                        int btnNumber = bytesToInt(bytes, i+1);   //Send array + startpos for 2-byte int

                        ArrayList<Integer> linkedDoors = new ArrayList<Integer>();
                        for (int j = i+3; j < bytes.length; j++)
                        {
                            if (bytes[j] == 'd'
                                    ||bytes[j] == 'w'
                                    ||bytes[j] == 'f'
                                    ||bytes[j] == 'b')
                            {
                        break;
                    }
                    else {
                        linkedDoors.add(bytesToInt(bytes, j));
                        j++;
                    }
                }

                Button button = new Button(x*100,450-(100*y),btnNumber, linkedDoors);
                buttons.add(button);
                x++;


            }else if(bytes[i] == 'd') //Door
            {
                textureForPlacement = new Texture("Floor.jpg");
                Texture texture = new Texture("Door.png");
                Tile floor = new Tile(x*100,450-(100*y),textureForPlacement);
                floors.add(floor);
                int doorNumber = bytesToInt(bytes, i+1);   //Send array + startpos for 2-byte int
                if(bytes[i-1] == 'f') {
                    texture = new Texture("Door.png");
                    Door door = new Door((x * 100)+((textureForPlacement.getWidth()/2)-texture.getWidth()/2), 450 - (100 * y), doorNumber, texture);
                    doors.add(door);
                }else if(bytes[i-1] == 'w')
                {
                    texture = new Texture("DoorHorizontal.png");
                    Door door = new Door(x * 100, (450 - (100 * y))+((textureForPlacement.getHeight()/2)-texture.getHeight()/2), doorNumber, texture);
                    doors.add(door);
                }

                x++;
            }
            else if(bytes[i] == 10)
            {
                y++;
                x = 0;
            }
        }
    }

    private static int bytesToInt(byte[] bajts, int startpos)
    {
        byte[] tmp = {bajts[startpos], bajts[startpos+1]};
        String nummerStr = new String(tmp);
        int result = Integer.parseInt(nummerStr);
        return result;
    }

    public void Draw(SpriteBatch spriteBatch,int playerPosX,int playerPosY)
    {
        int tempX =playerPosX + (Gdx.graphics.getWidth()*2);
        int tempY =playerPosY + (Gdx.graphics.getHeight()*2);
        for(int i = 0; i < floors.size();i++)
        {
            if(CheckToDraw(floors.get(i).x,floors.get(i).y,tempX,tempY,playerPosX,playerPosY)) {
                floors.get(i).Draw(spriteBatch);
            }
        }
        for(int i = 0; i < walls.size();i++)
        {
            if(CheckToDraw(walls.get(i).x,walls.get(i).y,tempX,tempY,playerPosX,playerPosY)) {
                walls.get(i).Draw(spriteBatch);
            }
        }
        for(int i = 0; i < doors.size();i++)
        {
            if(CheckToDraw(doors.get(i).x,doors.get(i).y,tempX,tempY,playerPosX,playerPosY)) {
                doors.get(i).Draw(spriteBatch);
            }
        }
        for(int i = 0; i < buttons.size();i++)
        {
            if(CheckToDraw(buttons.get(i).x,buttons.get(i).y,tempX,tempY,playerPosX,playerPosY)) {
                buttons.get(i).Draw(spriteBatch);
            }
        }
    }
    public boolean CheckToDraw(int posX,int posY,int fromX,int fromY, int toX, int toY)
    {
        if(posX<fromX  && posX > toX) {
            if(posY < fromY && posY > toY) {
                return true;

            }
        }
        return false;
    }
}
