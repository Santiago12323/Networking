package com.edu.escuelaing.arsw.networking.Networking.domain.usecase;


import com.edu.escuelaing.arsw.networking.Networking.domain.ports.UrlService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {

    @Override
    public List<String> analyzeUrl() {
        List<String> componentes = new ArrayList<>();

        try {
            URL url = new URL("https://www.facebook.com");



            componentes.add("Protocol: " + url.getProtocol());
            componentes.add("Authority: " + url.getAuthority());
            componentes.add("Host: " + url.getHost());
            componentes.add("Port: " + url.getPort());
            componentes.add("Path: " + url.getPath());
            componentes.add("Query: " + url.getQuery());
            componentes.add("File: " + url.getFile());
            componentes.add("Ref: " + url.getRef());

            componentes.forEach(System.out::println);

        } catch (Exception e) {
            componentes.add("Error al analizar la URL: " + e.getMessage());
        }
        return componentes;
    }

    @Override
    public String readHtml(String direccion) {
        StringBuilder contenido = new StringBuilder();

        try {
            if (!direccion.startsWith("http://") && !direccion.startsWith("https://")) {
                direccion = "https://" + direccion;
            }

            URL url = new URL(direccion);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }

            reader.close();
            return contenido.toString();

        } catch (Exception e) {
            return "Error al procesar la URL: " + e.getMessage();
        }
    }


}
