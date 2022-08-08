package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dtos.dtoInput.CartDto;
import dh.meli.projeto_integrador.dtos.dtoInput.ProductDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.TotalPriceDto;
import dh.meli.projeto_integrador.dtos.dtoOutput.UpdateStatusDto;
import dh.meli.projeto_integrador.enumClass.PurchaseOrderStatusEnum;
import dh.meli.projeto_integrador.exception.CartAlreadyFinishedException;
import dh.meli.projeto_integrador.exception.CartNotFoundException;
import dh.meli.projeto_integrador.model.*;
import dh.meli.projeto_integrador.repository.IBatchCartRepository;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import dh.meli.projeto_integrador.repository.ICartRepository;
import dh.meli.projeto_integrador.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    private ICartRepository cartRepository;

    @Autowired
    private IBatchCartRepository batchCartRepository;

    @Autowired
    private IBatchRepository batchRepository;

    @Autowired
    private ICustomerRepository customerRepository;

    public Cart buildCart(CartDto cartDto) {
        Customer customerById = customerRepository.findById(cartDto.getBuyer_id()).get();
        Cart cart = Cart.builder()
                .date(cartDto.getDate())
                .status(cartDto.getOrderStatus())
                .customer(customerById)
                .build();

        return cartRepository.save(cart);
    }

    public void buildBatchCart(Cart savedCart, List<ProductDto> productsList) {
        productsList.forEach(product -> {
            Batch batchById = batchRepository.findById(product.getBatchId()).get();

            BatchCart batchCart = BatchCart.builder()
                    .cart(savedCart)
                    .batch(batchById)
                    .quantity(product.getQuantity())
                    .build();
            batchCartRepository.save(batchCart);
        });
    }

    public TotalPriceDto totalCartPrice(List<ProductDto> productsList) {
        TotalPriceDto total = new TotalPriceDto(0.0);

        productsList.forEach(product -> {
            Batch batchById = batchRepository.findById(product.getBatchId()).get();
            total.setTotalPrice(batchById.getProduct().getPrice() + total.getTotalPrice());
        });
        return total;
    }

    @Override
    @Transactional
    public TotalPriceDto createCart(CartDto cartDto) {
        Cart savedCart = buildCart(cartDto);
        List<ProductDto> productsList = cartDto.getProductList();
        buildBatchCart(savedCart, productsList);
        return totalCartPrice(productsList);
    }

    public UpdateStatusDto updateStatusCart(Long id){
        Cart existCart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException("Cart not found with this id"));

        if(existCart.getStatus() == PurchaseOrderStatusEnum.FINISHED) throw new CartAlreadyFinishedException("Cart already Finished");

        existCart.setStatus(PurchaseOrderStatusEnum.FINISHED);

        cartRepository.save(existCart);

        return new UpdateStatusDto("Cart Finished successfully");
    }
}
