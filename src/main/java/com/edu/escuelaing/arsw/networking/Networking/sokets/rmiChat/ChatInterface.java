package com.edu.escuelaing.arsw.networking.Networking.sokets.rmiChat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota que define el contrato del chat.
 */
public interface ChatInterface extends Remote {

    /**
     * Método que será llamado remotamente para enviar un mensaje.
     * @param message Mensaje a mostrar.
     * @throws RemoteException En caso de fallo remoto.
     */
    void sendMessage(String message) throws RemoteException;
}

