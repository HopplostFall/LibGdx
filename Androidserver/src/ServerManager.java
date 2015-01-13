import java.awt.List;
import java.io.BufferedReader;
import java.io.Console;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



public class ServerManager {
	
	ServerSocket serverSocket;
	Thread newPlayersThread;
	ArrayList<Player>players = new ArrayList();
	int playerID = 6523;
	
	public ServerManager()
	{
		try
		{
			serverSocket = new ServerSocket(3540);
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		newPlayersThread = new Thread(new Connect());
		newPlayersThread.start();
		while(true)
		{
			ListenForMessages();
		}
		
	}
	class Connect implements Runnable{
		@Override
		public void run()
		{
			while(true)
			{
				try {
					ListenForNewPlayers();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public void ListenForMessages()
	{
		for(int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			if(player != null)
			{
			String temp = player.MessageFromClient();
				if(temp != null)
				{
				if(!temp.matches("failed"))
					{

						if(temp.contains("Pressed"))
						{
							SendToClients(temp.split(",")[0]+ ":Pressed"+"\n");
						}else if(temp.contains("Released"))
						{
							SendToClients(temp.split(",")[0] + ":Released"+"\n");
						}else if(temp.contains("finished"))
						{
							SendToClients(temp.split(",")[0] + ":Finished"+"\n");
							
						}else if(temp.contains("unfinished")){
							SendToClients(temp.split(",")[0] + ":unfinished"+"\n");
						}
						
					}
				}
				if(player.disconnected == true)
				{
					players.remove(i);
					i--;
				}
			
			}
			
			
		}
	}
	public void SendToClients(String textToSend)
	{
		for(int i = 0; i < players.size(); i++)
		{
			players.get(i).SendToPlayer(textToSend);
		}
	}
	public void ListenForNewPlayers() throws IOException
	{
		Socket connectionSocket = serverSocket.accept();
		BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
		String temp = ReadFromNewClient(inFromClient);
		
		if(temp.contains("hi"))
		{
			Player player = new Player(playerID, inFromClient, outToClient, connectionSocket);
			if ( (playerID & 1) == 0 ) {
				player.SendToPlayer("Welcome to this Server, Your ID is: "+ playerID +",Position:"+"4550,200");
				
			} else 
			{  
				player.SendToPlayer("Welcome to this Server, Your ID is: "+ playerID +",Position:"+"200,200");
			}
			playerID++;
			players.add(player);
		}else if(temp.contains("back"))
		{
			int ID = Integer.parseInt(temp.split(",")[1]);
			for(int i = 0; i < players.size(); i++)
			{
				if(players.get(i).playersID == ID)
				{
					players.get(i).inFromClient = inFromClient;
					players.get(i).outToClient = outToClient;
					players.get(i).connection = connectionSocket;
					
				}
			}
		}else 
		{
			connectionSocket.close();
		}
	
	}
	public String ReadFromNewClient(BufferedReader inFromClient)
	{
		
		try {
			String clientSentence;
			return clientSentence = inFromClient.readLine();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print("Failed at read from clients");
			e.printStackTrace();
			return "failed";
		}
	}
	

}
