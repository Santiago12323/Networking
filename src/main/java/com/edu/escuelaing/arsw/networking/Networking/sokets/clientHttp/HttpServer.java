package com.edu.escuelaing.arsw.networking.Networking.sokets.clientHttp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;

/**
 * Servidor HTTP simple que escucha en el puerto 35000.
 * Sirve archivos desde el directorio "src/main/resources".
 *
 * Al recibir una petición GET, busca el archivo solicitado
 * y lo retorna al cliente. Si el archivo no existe, responde con un error 404.
 */
public class HttpServer {

    /**
     * Método principal que arranca el servidor.
     * Maneja conexiones de clientes de manera secuencial (no concurrente).
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     * @throws IOException si ocurre un error al iniciar el servidor.
     */
    public static void main(String[] args) throws IOException {
        int port = 35000;
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Servidor iniciado en el puerto " + port);

        while (true) {
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Cliente conectado");

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream();

                String requestLine = in.readLine();
                if (requestLine == null || requestLine.isEmpty()) continue;

                System.out.println("Petición recibida: " + requestLine);

                String[] parts = requestLine.split(" ");
                if (parts.length < 2) continue;

                String filePath = parts[1];
                if (filePath.equals("/")) {
                    filePath = "/index.html";
                }

                File file = new File("src/main/resources" + filePath);

                if (file.exists() && !file.isDirectory()) {
                    String contentType = Files.probeContentType(file.toPath());

                    byte[] fileContent = Files.readAllBytes(file.toPath());

                    out.write(("HTTP/1.1 200 OK\r\n").getBytes());
                    out.write(("Content-Type: " + contentType + "\r\n").getBytes());
                    out.write(("Content-Length: " + fileContent.length + "\r\n").getBytes());
                    out.write("\r\n".getBytes());

                    out.write(fileContent);
                } else {
                    String notFoundMessage = "<html><body><h1>404 Not Found</h1></body></html>";
                    out.write("HTTP/1.1 404 Not Found\r\n".getBytes());
                    out.write("Content-Type: text/html\r\n".getBytes());
                    out.write(("Content-Length: " + notFoundMessage.length() + "\r\n").getBytes());
                    out.write("\r\n".getBytes());
                    out.write(notFoundMessage.getBytes());
                }

                out.flush();
            } catch (IOException e) {
                System.err.println("Error procesando la solicitud: " + e.getMessage());
            }
        }
    }
}
