package com.edu.escuelaing.arsw.networking.Networking.sokets.rmiChat;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 * Aplicación principal que funciona como cliente y servidor de chat.
 */
public class ChatApp {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.print("Tu nombre: ");
            String nombre = scanner.nextLine();

            System.out.print("Puerto donde publicar tu objeto (ej: 1099): ");
            int localPort = Integer.parseInt(scanner.nextLine());

            System.out.print("IP del otro usuario (ej: localhost): ");
            String remoteHost = scanner.nextLine();

            System.out.print("Puerto del otro usuario: ");
            int remotePort = Integer.parseInt(scanner.nextLine());

            ChatImpl chat = new ChatImpl(nombre);
            Registry localRegistry = LocateRegistry.createRegistry(localPort);
            localRegistry.rebind("ChatService", chat);

            System.out.println("🟢 Esperando mensajes...");

            Registry remoteRegistry = LocateRegistry.getRegistry(remoteHost, remotePort);
            ChatInterface remoteChat = (ChatInterface) remoteRegistry.lookup("ChatService");

            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();
                remoteChat.sendMessage(nombre + ": " + mensaje);
            }

        } catch (Exception e) {
            System.err.println("Error en el chat: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
