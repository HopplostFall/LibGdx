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
    Thread newPlayersThread;
    Connect connect;
    Player player;
    int finishButtons = 0;
    int playersFinished = 0;



    public TileHandler(Connect connect)
    {
        int y = 0;
        int x = 0;
        Texture textureForPlacement;
        FileHandle handle = Gdx.files.internal("textFile/map.txt");
        String lines = handle.readString();
        byte[] bytes = handle.readBytes();
        this.connect = connect;
        newPlayersThread = new Thread(new Listen());
        newPlayersThread.setName("Listen");
        newPlayersThread.start();

        for(int i = 0; i < bytes.length;i++)
        {
            if(bytes[i] == 'w') //Wall
            {
                textureForPlacement = new Texture("wall.png");
                Tile wall = new Tile(x*100,450-(100*y),textureForPlacement);
                walls.add(wall);
                x++;
            }else if(bytes[i] == 'f') //Floor
            {
                textureForPlacement = new Texture("Floor.png");
                Tile floor = new Tile(x*100,450-(100*y),textureForPlacement);
                floors.add(floor);
                x++;
            }else if(bytes[i] == 'b') //Button
            {
                textureForPlacement = new Texture("Floor.png");
                Tile floor = new Tile(x*100,450-(100*y),textureForPlacement);
                floors.add(floor);
                int btnNumber = bytesToInt(bytes, i+1);   //Send array + startpos for 2-byte int
                boolean finishButton = false;

                        ArrayList<Integer> linkedDoors = new ArrayList<Integer>();
                        for (int j = i+3; j < bytes.length; j++)
                        {
                            if (bytes[j] == 'd'
                                    ||bytes[j] == 'w'
                                    ||bytes[j] == 'f'
                                    ||bytes[j] == 'b'||bytes[j]=='v')

                            {
                        if(bytes[j]=='v') {
                            finishButton = true;
                            finishButtons++;
                            break;
                        }else {
                            break;
                        }
                    }
                    else {
                        linkedDoors.add(bytesToInt(bytes, j));
                        j++;
                    }
                }

                Button button = new Button(x*100,450-(100*y),btnNumber, linkedDoors,connect,finishButton);
                buttons.add(button);
                x++;


            }else if(bytes[i] == 'd') //Door
            {
                textureForPlacement = new Texture("Floor.png");
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

    public void Draw(SpriteBatch spriteBatch)
    {
        int playerPosX = player.x - Gdx.graphics.getWidth();
        int playerPosY = player.y - Gdx.graphics.getHeight();
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
        player.Draw(spriteBatch);
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
    class Listen implements Runnable
    {
        @Override
        public void run()
        {
            while(true)
            {
                String temp = connect.ListenForMessage();
            if(temp.contains("Welcome to this Server, Your ID is: "))
            {
                MyGdxGame.myID =  Integer.parseInt(temp.split(": ")[1].split(",")[0]);
                int x = Integer.parseInt(temp.split(":")[2].split(",")[0]);
                int y = Integer.parseInt(temp.split(":")[2].split(",")[1]);

                player = new Player(x,y);
            }
            else if(temp.contains("Pressed"))
            {
                int IDButton = Integer.parseInt(temp.split(":")[0]);
                Button button = null;
                for(int i = 0; i < buttons.size();i++)
                {
                    if(buttons.get(i).ID == IDButton)
                    {
                        button = buttons.get(i);
                    }
                }
                for(int i = 0; i < doors.size();i++)
                {
                    for(int b = 0; b < button.linkList.size();b++)
                    {
                        if(doors.get(i).ID == button.linkList.get(b))
                        {
                            doors.get(i).OpenDoor();
                        }
                    }
                }
            }else if(temp.contains("Released")) {
                int IDButton = Integer.parseInt(temp.split(":")[0]);
                Button button = null;
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).ID == IDButton) {
                        button = buttons.get(i);
                    }
                }
                for (int i = 0; i < doors.size(); i++) {
                    for (int b = 0; b < button.linkList.size(); b++) {
                        if (doors.get(i).ID == button.linkList.get(b)) {
                            doors.get(i).CloseDoor();
                        }
                    }
                }
            }else if(temp.contains("Finished")){
                int IDButton = Integer.parseInt(temp.split(":")[0]);
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).ID == IDButton) {
                         buttons.get(i).otherPlayer = true;
                         playersFinished++;
                        }
                     }
                }else if(temp.contains("UnActivate")){
                int IDButton = Integer.parseInt(temp.split(":")[0]);
                for (int i = 0; i < buttons.size(); i++) {
                    if (buttons.get(i).ID == IDButton) {
                        buttons.get(i).otherPlayer = false;
                        playersFinished--;
                    }
                }
            }
            }
        }
    }
    public void updatePlayer()
    {
        player.Update(walls,doors,buttons);
        if(playersFinished == finishButtons){

        }


    }

}
