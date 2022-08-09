package dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "section")
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<OrderEntry> orderEntries;

    @ManyToOne
    @JoinColumn(name = "warehouse_id", referencedColumnName = "id")
    @JsonIgnoreProperties("sections")
    private Warehouse warehouse;

    @Column(name = "product_type")
    private String productType;

    @Column(name = "max_product_load")
    private long maxProductLoad;

    @Column(name = "current_product_load")
    private long currentProductLoad;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private Set<Product> products;
}
