package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoInput.BatchDto;
import dh.meli.projeto_integrador.dto.dtoInput.OrderEntryDto;
import dh.meli.projeto_integrador.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Class responsible for processing user's requests and generating appropriated HTTP responses;
 * @author Diovana Valim, Thiago Guimarães;
 * @version 0.0.1;
 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {

    /**
     * Dependency Injection of the OrderService.
     */
    @Autowired
    private OrderService orderService;

    /**
     * A post method responsible for saving a new product batch at application database's;
     * @param orderEntryDto a valid OrderEntryDto instance received by the request body;
     * @return Response Entity of type propertyDto and the corresponding HttpStatus;
     */
    @PostMapping("/fresh-products/inboundorder")
    public ResponseEntity<List<BatchDto>> createInboundOrder(@RequestBody OrderEntryDto orderEntryDto) {
        List<BatchDto> batchDtoList = orderService.createInboundOrder(orderEntryDto);

        return new ResponseEntity<List<BatchDto>>(batchDtoList, HttpStatus.CREATED);
    }

    @PutMapping("/fresh-products/inboundorder/{id}")
    public ResponseEntity<List<BatchDto>> updateInboundOrder(@RequestBody OrderEntryDto orderEntryDto, @PathVariable Long id) {
        List<BatchDto> batches = orderService.updateInboundOrder(orderEntryDto, id);

        return new ResponseEntity<List<BatchDto>>(batches, HttpStatus.CREATED);
    }
}
