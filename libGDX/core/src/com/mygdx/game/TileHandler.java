package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

/**
 * Created by Kimpan on 2015-01-09.
 */
public class TileHandler {

    ArrayList<Tile> floors = new ArrayList();
    ArrayList<Tile> walls = new ArrayList();

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
            if(bytes[i] == 'w')
            {
                textureForPlacement = new Texture("wall.jpg");
                Tile wall = new Tile(x*100,450-(100*y),textureForPlacement);
                walls.add(wall);
                x++;
            }else if(bytes[i] == 'f')
            {
                textureForPlacement = new Texture("Floor.jpg");
                Tile floor = new Tile(x*100,450-(100*y),textureForPlacement);
                floors.add(floor);
                x++;
            }
            else if(bytes[i] == 10)
            {
                y++;
                x = 0;
            }

        }
    }

    public void Draw(SpriteBatch spriteBatch)
    {
        for(int i = 0; i < floors.size();i++)
        {
            floors.get(i).Draw(spriteBatch);
        }
        for(int i = 0; i < walls.size();i++)
        {
            walls.get(i).Draw(spriteBatch);
        }
    }
}
