package br.com.alura.comex.usuario.entity.usuario;

public interface UsuarioRepository {
    Usuario registrarNovoUsuario(Usuario usuario);

    Usuario buscarUsuario(String email);
}
