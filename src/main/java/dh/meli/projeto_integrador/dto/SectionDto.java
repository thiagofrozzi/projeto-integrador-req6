package dh.meli.projeto_integrador.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Method Getter implemented by Lombok lib for get access the private attributes of the Product Class
 */
@Getter
/**
 * Method Setter implemented by Lombok lib for set the private attributes of the Product Class
 */
@Setter
/**
 * Method Default Constructor implemented by Lombok lib
 */
@NoArgsConstructor
/**
 * Method Constructor with all arguments implemented by Lombok lib
 */
@AllArgsConstructor
/**
 * Method builder implemented by Lombok lib
 */
@Builder
/**
 * Class used to create a Data Transfer Object for Section POJO
 * @author Diovana Valim, Thiago Almeida
 * @version 0.0.2
 * @see java.lang.Object
 */
public class SectionDto {

    @JsonProperty("sectionCode")
    private long sectionId;

    @JsonProperty("warehouseCode")
    private long warehouseId;
}
