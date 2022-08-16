package dh.meli.projeto_integrador.dto.dtoInput;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
public class CustomerDto {
    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "O formato do campo email esta invalido.")
    private String email;

    @NotBlank
    @Pattern(regexp = "^[0-9]{2}-([0-9]{8}|[0-9]{9})", message = "O formato do campo numero esta invalido" )
    private String number;

    @NotBlank
    @Pattern(regexp = "([0-9]{2}[\\.][0-9]{3}[\\.][0-9]{3}[\\/][0-9]{4}[-][0-9]{2})|([0-9]{3}[\\.][0-9]{3}[\\.][0-9]{3}[-][0-9]{2})" , message = "O formato do campo CPF esta invalido.")
    private String cpf;
}
