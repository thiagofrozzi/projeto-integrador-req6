package dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "current_temperature")
    private float currentTemperature;

    @Column(name = "minimum_temperature")
    private float minimumTemperature;

    @Column(name = "initial_quantity")
    private int initialQuantity;

    @Column(name = "current_quantity")
    private int currentQuantity;

    @Column(name = "manufacturing_date")
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time")
    private LocalTime manufacturingTime;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    @JsonIgnoreProperties("batches")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_entry_id", referencedColumnName = "id")
    @JsonIgnoreProperties("batches")
    private OrderEntry orderEntry;

    @OneToMany(mappedBy = "batch")
    @JsonIgnoreProperties("batch")
    private Set<BatchCart> batchCarts;
}
