package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.dtoInput.CartDto;
import dh.meli.projeto_integrador.dto.dtoInput.ProductDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CartOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.CartProductsOutputDto;
import dh.meli.projeto_integrador.dto.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.*;
import dh.meli.projeto_integrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




/**
 * Class responsible for business rules and communication with the Cart Repository layer
 * @author Gabriela Azevedo
 * @version 0.0.1
 */
@Service
public class CartService implements ICartService {

    /**
     * Dependency Injection of the Cart Repository.
     */
    @Autowired
    private ICartRepository cartRepository;

    /**
     * Dependency Injection of the BatchCart Repository.
     */
    @Autowired
    private IProductCartRepository batchCartRepository;

    /**
     * Dependency Injection of the Batch Repository.
     */
    @Autowired
    private IBatchRepository batchRepository;

    /**
     * Dependency Injection of the Customer Repository.
     */
    @Autowired
    private ICustomerRepository customerRepository;


    /**
     * Dependency Injection of the Product Repository.
     */
    @Autowired
    private IProductRepository productRepository;

    /**
     * Method that receives an object of type CartDto, build the cart object and saves on the Cart table.
     * @param cartDto
     * @return
     */
    public Cart buildCart(CartDto cartDto) {
        Customer customerById = customerRepository.findById(cartDto.getBuyerId()).get();
        Cart cart = Cart.builder()
                .date(cartDto.getDate())
                .status(cartDto.getOrderStatus())
                .customer(customerById)
                .build();

        return cartRepository.save(cart);
    }

    /**
     * Method that receives an object of type Cart and a List of objects of type ProductDto and saves the data on the BatchCart table.
     * @param savedCart an object of type Cart
     * @param productsList a list of objects of type ProductDto
     */
    public void buildBatchCart(Cart savedCart, List<ProductDto> productsList) {
        productsList.forEach(product -> {
            Batch batchById = batchRepository.findById(product.getProductId()).get();

            ProductCart productCart = ProductCart.builder()
                    .cart(savedCart)
//                    .batch(batchById)
                    .quantity(product.getQuantity())
                    .build();
            batchCartRepository.save(productCart);
        });
    }

    /**
     * Method that receives a list of type ProductDto and calculates the total price of the cart products.
     * @param productsList List of objects of type ProductDto
     * @return an object of type TotalPriceDto with an attribute totalPrice of type Double.
     */
    public TotalPriceDto totalCartPrice(List<ProductDto> productsList) {
        TotalPriceDto total = new TotalPriceDto(0.0);

        productsList.forEach(product -> {
            Batch batchById = batchRepository.findById(product.getProductId()).get();
            total.setTotalPrice(batchById.getProduct().getPrice() + total.getTotalPrice());
        });
        return total;
    }

    /**
     * Method that calls the other methods of this class and persists the info of the carts on the database and returns the total price for the user.
     * @param cartDto an object of type CartDto
     * @return an object of type TotalPriceDto with an attribute totalPrice of type Double.
     */
    @Override
    @Transactional
    public TotalPriceDto createCart(CartDto cartDto) {
        Cart savedCart = buildCart(cartDto);
        List<ProductDto> productsList = cartDto.getProducts();
        buildBatchCart(savedCart, productsList);
        return totalCartPrice(productsList);
    }

    private List<CartProductsOutputDto> createCartProductList (List<ProductCart> cartProducts) {
        List<CartProductsOutputDto> cartProductsDtos = new ArrayList<>();
        for (ProductCart productCart : cartProducts) {
            Product product = productRepository.findById(productCart.getProduct().getId()).get();
            cartProductsDtos.add(CartProductsOutputDto.builder()
                    .name(product.getName())
                    .type(product.getType())
                    .price(product.getPrice())
                    .quantity(productCart.getQuantity())
                    .subtotal(product.getPrice() * productCart.getQuantity())
                    .build()
            );
        }
        return cartProductsDtos;
    }

    private Double calculateCartTotal(List<CartProductsOutputDto> cartProductsDtos){
        Double total = 0.0;
        for (CartProductsOutputDto productCartDto : cartProductsDtos) {
            total += productCartDto.getSubtotal();
        }
        return total;
    }
    public CartOutputDto getCartById(Long id) {
        Optional<Cart> cart = cartRepository.findById(id);

        if(cart.isEmpty()) throw new ResourceNotFoundException(String.format("Could not find valid cart for id %d", id));

        Customer customer = customerRepository.findById(cart.get().getCustomer().getId()).get();

        List<ProductCart> cartProducts = new ArrayList<>(cart.get().getProductCarts());

        List<CartProductsOutputDto> cartProductsDtos = createCartProductList(cartProducts);

        Double total = calculateCartTotal(cartProductsDtos);
        return CartOutputDto.builder()
                .customerName(customer.getName())
                .status(cart.get().getStatus())
                .date(cart.get().getDate())
                .products(cartProductsDtos)
                .total(total)
                .build();
    }
}
