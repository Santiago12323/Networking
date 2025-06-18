package com.edu.escuelaing.arsw.networking.Networking.sokets.rmiServer;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Cliente RMI que se conecta al servicio remoto Echo y
 * envía un mensaje para recibir una respuesta desde el servidor.
 */
public class EchoClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");

            EchoServer stub = (EchoServer) registry.lookup("EchoService");

            String respuesta = stub.echo("¡Hola mundo!");

            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (Exception e) {
            System.err.println("Error en el cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

