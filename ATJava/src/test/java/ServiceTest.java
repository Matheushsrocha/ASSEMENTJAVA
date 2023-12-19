import dto.UsuarioDTOInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.UsuarioService;

public class ServiceTest {

    @Test
    public void testInserirUsuario() {

        UsuarioService usuarioService = new UsuarioService();
        UsuarioDTOInput usuarioDTOInput = new UsuarioDTOInput();
        usuarioDTOInput.setId(1);
        usuarioDTOInput.setNome("Exemplo");
        usuarioDTOInput.setSenha("senha123");

        usuarioService.inserir(usuarioDTOInput);

        int tamanhoLista = usuarioService.listar().size();
        Assertions.assertEquals(1, tamanhoLista, "A inserção do usuário falhou.");
    }
}
