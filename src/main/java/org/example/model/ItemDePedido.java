package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "itens_de_pedidos")
public class ItemDePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    private BigDecimal preco;

    private int quantidade;

    private BigDecimal desconto;

    @Enumerated(EnumType.STRING)
    private TipoDescontoProduto tipoDescontoProduto;

    public BigDecimal getTotal() {
        BigDecimal total = preco.multiply(new BigDecimal(quantidade));
        return total.subtract(desconto);
    }
}
