package dh.meli.projeto_integrador.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "warehouse")
@Builder
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    private Set<Section> sections;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    private Set<Agent> agents;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}
