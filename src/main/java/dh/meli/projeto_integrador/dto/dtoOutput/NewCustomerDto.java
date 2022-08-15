package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Customer;
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
 * Class used to create a Data Transfer Object for Output Product POJO
 * @author Thiago Frozzi
 * @version 0.0.1
 * @see java.lang.Object
 */
public class NewCustomerDto {
    private long id;
    private String name;
    private String email;

    public NewCustomerDto(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmailAddress();
    }
}
