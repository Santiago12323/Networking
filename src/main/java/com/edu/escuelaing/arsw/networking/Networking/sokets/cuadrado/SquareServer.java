package com.edu.escuelaing.arsw.networking.Networking.sokets.cuadrado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor TCP que escucha en el puerto 35000.
 * Recibe números enviados por el cliente y devuelve su cuadrado.
 */
public class SquareServer {

    /**
     * Método principal del servidor.
     * Acepta una conexión entrante y responde con el cuadrado del número recibido.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws IOException si ocurre un error en la conexión.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(35000);
            System.out.println("Servidor esperando conexiones en el puerto 35000...");
        } catch (IOException e) {
            System.err.println("No se puede abrir el puerto: 35000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Cliente conectado.");
        } catch (IOException e) {
            System.err.println("Error al aceptar la conexión.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;

        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibido del cliente: " + inputLine);
            try {
                int number = Integer.parseInt(inputLine);
                int squared = number * number;
                out.println("El cuadrado de " + number + " es " + squared);
            } catch (NumberFormatException e) {
                out.println("Por favor envía un número válido.");
            }
        }

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}
