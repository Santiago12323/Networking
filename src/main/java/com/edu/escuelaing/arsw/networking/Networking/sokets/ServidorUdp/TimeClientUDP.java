package com.edu.escuelaing.arsw.networking.Networking.sokets.ServidorUdp;


import java.net.*;

/**
 * Cliente UDP que se conecta a un servidor en localhost:9876
 * para solicitar la hora actual cada 5 segundos.
 * Si el servidor no está disponible, mantiene y muestra la última hora recibida.
 */
public class TimeClientUDP {

    /**
     * Punto de entrada principal del cliente.
     * Intenta conectarse al servidor UDP, recibe la hora y la muestra.
     * Si falla, muestra la última hora válida recibida.
     *
     * @param args Argumentos de línea de comandos (no se utilizan).
     * @throws Exception si ocurre una interrupción por el sleep.
     */
    public static void main(String[] args) throws Exception {
        String lastTime = "Sin hora aún";

        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket();
                socket.setSoTimeout(2000);

                byte[] sendBuffer = "hora".getBytes();
                InetAddress address = InetAddress.getByName("localhost");

                DatagramPacket request = new DatagramPacket(sendBuffer, sendBuffer.length, address, 9876);
                socket.send(request);

                byte[] receiveBuffer = new byte[1024];
                DatagramPacket response = new DatagramPacket(receiveBuffer, receiveBuffer.length);

                socket.receive(response);

                String currentTime = new String(response.getData(), 0, response.getLength());
                lastTime = currentTime;
                System.out.println("Hora recibida: " + currentTime);

                socket.close();
            } catch (Exception e) {
                System.out.println("Servidor no disponible. Manteniendo hora: " + lastTime);
            }

            Thread.sleep(5000);
        }
    }
}

