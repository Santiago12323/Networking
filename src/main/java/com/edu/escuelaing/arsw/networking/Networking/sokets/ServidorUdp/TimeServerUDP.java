package com.edu.escuelaing.arsw.networking.Networking.sokets.ServidorUdp;

import java.net.*;
import java.time.LocalTime;

/**
 * Servidor UDP que escucha en el puerto 9876.
 * Al recibir un datagrama, responde con la hora actual del sistema sin nanosegundos.
 */
public class TimeServerUDP {

    /**
     * Punto de entrada principal del servidor.
     * Crea un socket UDP que espera paquetes, y responde con la hora actual.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     * @throws Exception si ocurre algún error de red.
     */
    public static void main(String[] args) throws Exception {
        // Crear socket UDP en el puerto 9876
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] receiveBuffer = new byte[1024];

        System.out.println("Servidor UDP listo en el puerto 9876...");

        while (true) {
            // Recibir datagrama del cliente
            DatagramPacket request = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(request);

            String time = LocalTime.now().withNano(0).toString();
            byte[] sendBuffer = time.getBytes();

            DatagramPacket response = new DatagramPacket(
                    sendBuffer,
                    sendBuffer.length,
                    request.getAddress(),
                    request.getPort()
            );

            socket.send(response);
            System.out.println("Hora enviada: " + time);
        }
    }
}
