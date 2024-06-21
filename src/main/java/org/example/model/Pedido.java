package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@NoArgsConstructor

@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "data_hora")
    private LocalDateTime dataHora = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ItemDePedido> itens;

    private double desconto;

    @Enumerated(EnumType.STRING)
    private TipoDesconto tipoDesconto;


    @Override
    public String toString() {
        return "\nPedido{" +
                "id: " + id +
                ", data e hora: " + dataHora +
                ", cliente: " + cliente +
                ", itens:" + itens +
                ", desconto: " + desconto +
                ", tipo de desconto: " + tipoDesconto +
                '}';
    }
}
