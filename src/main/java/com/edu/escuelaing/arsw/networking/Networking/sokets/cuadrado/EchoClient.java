package com.edu.escuelaing.arsw.networking.Networking.sokets.cuadrado;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Cliente TCP que se conecta a un servidor en localhost:35000.
 * Envía números ingresados por el usuario y recibe la respuesta del servidor
 * con el cuadrado de cada número.
 */
public class EchoClient {

    /**
     * Método principal del cliente.
     * Conecta al servidor, lee entrada del usuario y muestra la respuesta.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            socket = new Socket("127.0.0.1", 35000);

            out = new PrintWriter(socket.getOutputStream(), true);

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            System.out.println("Escribe un número y presiona Enter:");

            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);

                String response = in.readLine();
                System.out.println("Respuesta del servidor: " + response);

                System.out.println("Escribe otro número (o Ctrl+C para salir):");
            }

            out.close();
            in.close();
            stdIn.close();
            socket.close();

        } catch (UnknownHostException e) {
            System.err.println("No se puede conectar al host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexión");
            System.exit(1);
        }
    }
}
