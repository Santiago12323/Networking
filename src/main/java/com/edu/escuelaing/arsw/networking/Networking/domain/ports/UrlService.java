package com.edu.escuelaing.arsw.networking.Networking.domain.ports;

import java.util.List;

public interface UrlService {


    List<String> analyzeUrl();

    String readHtml(String direccion);
}
