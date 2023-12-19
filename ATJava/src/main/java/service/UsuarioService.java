package service;

import dto.UsuarioDTOInput;
import dto.UsuarioDTOOutput;
import lombok.RequiredArgsConstructor;
import model.Usuario;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Collection;
import org.modelmapper.ModelMapper;



import dto.UsuarioDTOInput;
import dto.UsuarioDTOOutput;
import model.Usuario;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioService {
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private ModelMapper modelMapper = new ModelMapper();

    public List<UsuarioDTOOutput> listar() {
        List<UsuarioDTOOutput> listaDTO = new ArrayList<>();
        for (Usuario usuario : listaUsuarios) {
            listaDTO.add(modelMapper.map(usuario, UsuarioDTOOutput.class));
        }
        return listaDTO;
    }

    public void inserir(UsuarioDTOInput usuarioDTOInput) {
        Usuario usuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        listaUsuarios.add(usuario);
    }

    public void alterar(UsuarioDTOInput usuarioDTOInput) {
        Usuario novoUsuario = modelMapper.map(usuarioDTOInput, Usuario.class);
        Optional<Usuario> usuarioExistente = listaUsuarios.stream()
                .filter(u -> u.getId() == novoUsuario.getId())
                .findFirst();

        usuarioExistente.ifPresent(usuario -> listaUsuarios.set(listaUsuarios.indexOf(usuario), novoUsuario));
    }

    public UsuarioDTOOutput buscar(int id) {
        Optional<Usuario> usuarioOptional = listaUsuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();

        return usuarioOptional.map(usuario -> modelMapper.map(usuario, UsuarioDTOOutput.class)).orElse(null);
    }

    public void excluir(int id) {
        listaUsuarios.removeIf(usuario -> usuario.getId() == id);
    }
}
