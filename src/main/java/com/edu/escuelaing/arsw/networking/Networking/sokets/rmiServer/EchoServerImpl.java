package com.edu.escuelaing.arsw.networking.Networking.sokets.rmiServer;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Implementación del servidor Echo.
 * Implementa la interfaz remota y publica el servicio en el registro RMI.
 */
public class EchoServerImpl extends UnicastRemoteObject implements EchoServer {

    /**
     * Constructor que lanza RemoteException requerido por RMI.
     * @throws RemoteException si ocurre un error al exportar el objeto.
     */
    public EchoServerImpl() throws RemoteException {
        super();
    }

    /**
     * Implementación del método remoto echo.
     * @param cadena Cadena recibida del cliente.
     * @return Cadena con prefijo desde el servidor.
     * @throws RemoteException si ocurre un error remoto.
     */
    @Override
    public String echo(String cadena) throws RemoteException {
        return "desde el servidor: " + cadena;
    }

    /**
     * Método principal que registra el objeto remoto en el RMI Registry.
     */
    public static void main(String[] args) {
        try {
            EchoServerImpl obj = new EchoServerImpl();

            // Crear o acceder al registro RMI en el puerto por defecto (1099)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Registrar el servicio con el nombre "EchoService"
            registry.rebind("EchoService", obj);

            System.out.println("Servidor RMI listo...");
        } catch (RemoteException e) {
            System.err.println("Error en el servidor RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
