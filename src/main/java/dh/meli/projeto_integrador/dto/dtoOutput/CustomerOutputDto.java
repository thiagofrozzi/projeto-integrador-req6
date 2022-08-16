package dh.meli.projeto_integrador.dto.dtoOutput;

import dh.meli.projeto_integrador.model.Customer;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;
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
public class CustomerOutputDto {
    private long id;
    private String name;
    private String email;
    private String number;
    private String cpf;
    private List<Long> cart;

    public CustomerOutputDto(Customer customer){
        this.id = customer.getId();
        this.name = customer.getName();
        this.email = customer.getEmailAddress();
        this.number = customer.getPhoneNumber();
        this.cpf = customer.getCpf();
        this.cart =  customer.getCarts().stream().map((cart) -> cart.getId()).collect(Collectors.toList());
    }
}
