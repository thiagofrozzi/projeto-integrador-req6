package dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("carts")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    @JsonIgnoreProperties("cart")
    private Set<BatchCart> batchCarts;
}
