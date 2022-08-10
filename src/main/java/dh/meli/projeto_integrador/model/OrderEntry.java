package dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "order_entry")
public class OrderEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "orderEntry", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("orderEntry")
    private Set<Batch> batches;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonIgnoreProperties("orderEntries")
    private Section section;
}
