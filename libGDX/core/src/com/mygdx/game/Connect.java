package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Kimpan on 2015-01-13.
 */
public class Connect {

    Socket clientSocket;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    InetAddress serverAddr;


    public Connect() throws IOException {
        //serverAddr = InetAddress.getByName("10.1.19.132");
        serverAddr = InetAddress.getByName("10.0.1.30");
        clientSocket = new Socket(serverAddr, 3540);
        outToServer = new DataOutputStream(clientSocket.getOutputStream());
        inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        WriteToServer("hi");
    }
    public void WriteToServer(String textToSend)
    {
        String sentence = textToSend;
        try {
            outToServer.writeBytes(sentence + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public String ListenForMessage()
    {
        try
        {
           return inFromServer.readLine();

        } catch (IOException e) {
            e.printStackTrace();
            return "failed";
        }
    }

}
