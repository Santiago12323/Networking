package com.edu.escuelaing.arsw.networking.Networking.sokets.rmiChat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementación del receptor de mensajes para el chat.
 */
public class ChatImpl extends UnicastRemoteObject implements ChatInterface {

    private String nombre;

    /**
     * Constructor que recibe el nombre del usuario.
     * @param nombre Nombre del usuario.
     * @throws RemoteException si falla el registro remoto.
     */
    public ChatImpl(String nombre) throws RemoteException {
        super();
        this.nombre = nombre;
    }

    /**
     * Método que imprime el mensaje recibido en consola.
     * @param message Mensaje enviado desde otro cliente.
     */
    @Override
    public void sendMessage(String message) {
        System.out.println(message);
    }
}
