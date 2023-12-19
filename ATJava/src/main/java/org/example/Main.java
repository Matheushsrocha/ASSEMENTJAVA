package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import controller.UsuarioController;
import service.UsuarioService;

import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {

        UsuarioService usuarioService = new UsuarioService();
        ObjectMapper objectMapper = new ObjectMapper();


        UsuarioController usuarioController = new UsuarioController(usuarioService, objectMapper);
        
        usuarioController.respostasRequisicoes();
    }
}
