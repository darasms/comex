package br.com.alura.comex.infra.cliente;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
@Getter
@Setter @Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefoneEntity {

    @Size(min = 2, max = 2)
    @Column(nullable = false)
    private String ddd;

    @Size(min = 9, max = 9)
    @Column(nullable = false)
    private String numero;

}
