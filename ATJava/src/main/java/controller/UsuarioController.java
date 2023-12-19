package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dto.UsuarioDTOInput;
import dto.UsuarioDTOOutput;
import service.UsuarioService;

import static spark.Spark.*;

public class UsuarioController {
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public UsuarioController(UsuarioService usuarioService, ObjectMapper objectMapper) {
        this.usuarioService = usuarioService;
        this.objectMapper = objectMapper;
    }

    public void respostasRequisicoes() {

        get("/usuarios", (req, res) -> {
            res.type("application/json");
            return objectMapper.writeValueAsString(usuarioService.listar());
        });


        get("/usuarios/:id", (req, res) -> {
            res.type("application/json");
            int id = Integer.parseInt(req.params(":id"));
            UsuarioDTOOutput usuarioDTOOutput = usuarioService.buscar(id);
            if (usuarioDTOOutput != null) {
                return objectMapper.writeValueAsString(usuarioDTOOutput);
            } else {
                res.status(404);
                return "Usuário não encontrado.";
            }
        });

        // Endpoint para exclusão de um usuário pelo ID
        delete("/usuarios/:id", (req, res) -> {
            int id = Integer.parseInt(req.params(":id"));
            usuarioService.excluir(id);
            res.status(204);
            return "";
        });


        post("/usuarios", (req, res) -> {
            res.type("application/json");
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
            usuarioService.inserir(usuarioDTOInput);
            res.status(201);
            return "Usuário inserido com sucesso.";
        });


        put("/usuarios", (req, res) -> {
            res.type("application/json");
            UsuarioDTOInput usuarioDTOInput = objectMapper.readValue(req.body(), UsuarioDTOInput.class);
            usuarioService.alterar(usuarioDTOInput);
            res.status(200);
            return "Usuário atualizado com sucesso.";
        });
    }

}
