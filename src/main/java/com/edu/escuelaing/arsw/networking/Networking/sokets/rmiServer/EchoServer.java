package com.edu.escuelaing.arsw.networking.Networking.sokets.rmiServer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoServer extends Remote {
    String echo(String cadena) throws RemoteException;
}

