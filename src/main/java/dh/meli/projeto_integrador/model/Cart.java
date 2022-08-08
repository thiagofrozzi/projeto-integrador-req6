package dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "cart")
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnoreProperties("carts")
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatusEnum status;

    private LocalDate date;

    @OneToMany(mappedBy = "cart")
    @JsonIgnoreProperties("cart")
    private Set<BatchCart> batchCarts;
}
