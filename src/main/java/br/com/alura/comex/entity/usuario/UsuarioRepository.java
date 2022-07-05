package br.com.alura.comex.entity.usuario;

public interface UsuarioRepository {
    Usuario registrarNovoUsuario(Usuario usuario);

    Usuario buscarUsuario(String email);
}
