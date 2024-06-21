package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter @Setter
@NoArgsConstructor

@Embeddable
public class Endereco implements Serializable {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String uf;

    public Endereco(String rua, String numero, String bairro, String cidade, String uf) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "rua: " + rua +
                ", numero: " + numero +
                ", bairro: " + bairro +
                ", cidade: " + cidade +
                ", uf: " + uf +
                "}\n";
    }
}
