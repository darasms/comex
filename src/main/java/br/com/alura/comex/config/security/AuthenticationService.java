package br.com.alura.comex.config.security;

import br.com.alura.comex.infra.usuario.UsuarioEntity;
import br.com.alura.comex.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UsuarioEntity> usuario = usuarioRepository.findByEmail(username);

        if (usuario.isPresent()) return usuario.get();

        throw new UsernameNotFoundException("Email inválido!");
    }
}
