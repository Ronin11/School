package server;

import io.JSON;
import race.Race;
import server.UdpServer.Event;

public class UdpClient {
	UdpServer server;
	int port = 14000;
	
	public UdpClient(Race r){
		JSON parser = new JSON(r);
		server = new UdpServer(port);
		server.addUdpServerListener(new UdpServer.Listener() {
			@Override
			public void packetReceived(Event evt) {
				parser.parse(evt.getPacketAsString());
			}
		});
		server.start();
		System.out.println("Server started on port " + port);
	}

}

