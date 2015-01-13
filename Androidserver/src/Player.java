import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.Timer;


public class Player {
	
	BufferedReader inFromClient;
	DataOutputStream outToClient;
	Socket connection;
	int playersID;
	Thread disconnectPlayer;
	boolean disconnected = false;
	
	public Player(int playersID, BufferedReader inFromClient, DataOutputStream outToClient,Socket connection)
	{
		this.inFromClient = inFromClient;
		this.outToClient = outToClient;
		this.playersID = playersID;
		this.connection = connection;
		disconnectPlayer = new Thread();
	}
	public String MessageFromClient()
	{
		try {
			if(inFromClient.ready())
			{
				
				return inFromClient.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			if(!connection.isConnected())
			{
				disconnectPlayer.start();
			}
			return "failed";
		}
		return "";
	}
	public void SendToPlayer(String textToSend)
	{
		try {
			outToClient.writeBytes(textToSend + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.console().printf("failed to send");
		}
	}
	class countDown implements Runnable
	{
		@Override
		public void run()
		{
			int delay = 30000;
			ActionListener taskPerformer = new ActionListener()
			{
				public void actionPerformed(ActionEvent evt)
				{
					 disconnected = true;
				}
			};
			Timer timer = new Timer(delay,taskPerformer);
		}
	}

}
