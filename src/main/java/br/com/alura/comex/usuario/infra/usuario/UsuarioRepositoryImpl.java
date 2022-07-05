package br.com.alura.comex.usuario.infra.usuario;

import br.com.alura.comex.usuario.entity.usuario.Usuario;
import br.com.alura.comex.usuario.entity.usuario.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioDAO usuarioDAO;

    @Override
    public Usuario registrarNovoUsuario(Usuario usuario) {
        UsuarioEntity usuarioEntity = usuarioDAO.save(UsuarioEntity.converter(usuario));
        return usuarioEntity.paraUsuario();
    }

    @Override
    public Usuario buscarUsuario(String email) {
        return usuarioDAO.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)).paraUsuario();
    }
}
