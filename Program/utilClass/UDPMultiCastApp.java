package utilClass;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import applications.menu.MenuController;
import init.SceneManager;
import interfaces.ControllerMustHave;
import javafx.application.Platform;

public class UDPMultiCastApp {
	private final static int PORT = 9876;
	private final static String MULTICAST_ADRESS = "230.0.0.1";
	private static MulticastSocket socket = null;
	private static BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();
    private static boolean running = true;
   
	@SuppressWarnings("resource")
	public static void joinCanal() {
		try {
			socket = new MulticastSocket(PORT);
	        InetAddress group = InetAddress.getByName(MULTICAST_ADRESS);  // Adresse multicast
	        // Join the multicast group using InetSocketAddress
	        socket.joinGroup(new InetSocketAddress(group, PORT), NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));

	        // Créer un thread pour écouter les paquets UDP de manière concurrente avec javaFX
	        Thread listenerThread = new Thread(() -> {
	            try {
	            	byte[] buffer = new byte[512];
	    	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
	    	        
	                while (running) {
	                    // Réception du paquet
	                    socket.receive(packet);

	                    String command = new String(packet.getData(), 0, packet.getLength());
	                    messageQueue.offer(command);
	                    
	                }
	            } catch (Exception e) {
	            	// Fermeture de l'application donc fin d'écoute du canal
	            }
	        });

	        listenerThread.setDaemon(true);
	        listenerThread.start();  // Démarrer le thread de réception
	        
	        
	        // Lancer un thread pour traiter les messages
            Thread processorThread = new Thread(() -> {
                while (running) {
                    try {
                        String message = messageQueue.take(); // Bloque jusqu'à ce qu'un message soit disponible
                        
                        String[] messageSplit = message.split("-");
                        String command = messageSplit[0];
                        String content = messageSplit[1];
                        System.out.println("command : " + command);
                        System.out.println("content : " + content);
                        if (Enum.valueOf(Command.class,command) == Command.REFRESH) {
                        	String[] sceneTab = content.split(",");
                        	ArrayList<String> sceneList = new ArrayList<String>(Arrays.asList(sceneTab));
    	                    sceneList.stream().map(sceneString -> Enum.valueOf(ScenesMap.class, sceneString.toUpperCase())).forEach(scene -> {
    	                    	Object controller = SceneManager.getController(scene);
    	                    	if (controller != null) {
    	                    		Platform.runLater(() -> {
    	                    			((ControllerMustHave) controller).refreshData();
    	                            });
    	                    	}
    	                    });
                        }else if (Enum.valueOf(Command.class,command) == Command.ADD_CONNECTED_EMPLOYEE) {
                        	Object controller = SceneManager.getController(ScenesMap.MENU);
	                    	if (controller != null) {
	                    		Platform.runLater(() -> {
	                    			((MenuController) controller).addConnectedEmp(Long.valueOf(content));
	                            });
	                    	}
                        }else if (Enum.valueOf(Command.class,command) == Command.RM_CONNECTED_EMPLOYEE) {
                        	Object controller = SceneManager.getController(ScenesMap.MENU);
	                    	if (controller != null) {
	                    		Platform.runLater(() -> {
	                    			((MenuController) controller).removeConnectedEmp(Long.valueOf(content));
	                            });
	                    	}
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });

            processorThread.setDaemon(true);
            processorThread.start();
	        
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sendCommand(String scenes) {
		try {
			DatagramSocket socket = new DatagramSocket();
	        socket.setBroadcast(true);
	        
	        byte[] buffer = scenes.getBytes();

	        InetAddress group = InetAddress.getByName(MULTICAST_ADRESS);  // Adresse multicast
	        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);

	        socket.send(packet);
	        socket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeSocket() {
        if (socket != null && !socket.isClosed()) {
            try {
                InetAddress group = InetAddress.getByName(MULTICAST_ADRESS);
                socket.leaveGroup(new InetSocketAddress(group, PORT), NetworkInterface.getByInetAddress(InetAddress.getLocalHost()));
                socket.close();
                System.out.println("Socket multicast fermé.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
