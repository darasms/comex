package br.com.alura.comex.entity.Usuario;

import br.com.alura.comex.entity.Cliente.Cliente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Usuario {

    private String email;
    private String senha;
    private Cliente cliente;
    private List<Perfil> perfis = new ArrayList<>();

    public void adicionarPerfil(String nome){
        this.perfis.add(new Perfil(nome));
    }


}
