package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.BatchDto;
import dh.meli.projeto_integrador.dto.OrderEntryDto;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class responsible for processing user's requests and generating appropriated HTTP responses;
 * @author Diovana Valim, Thiago Guimarães;
 * @version 0.0.1;
 */
@RestController
@RequestMapping("/api/v1")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * A post method responsible for saving a new product batch at application database's;
     * @param orderEntryDto a valid OrderEntryDto instance received by the request body;
     * @return Response Entity of type propertyDto and the corresponding HttpStatus;
     */
    @PostMapping("/fresh-products/inboundorder")
    public ResponseEntity<List<BatchDto>> createInboundOrder(@RequestBody OrderEntryDto orderEntryDto) {
        Set<Batch> batches = orderService.createInboundOrder(orderEntryDto);

        List<BatchDto> batchDtoList = new ArrayList<BatchDto>();

        batches.forEach(batch -> {
            batchDtoList.add(new BatchDto(batch));
        });

        return new ResponseEntity<List<BatchDto>>(batchDtoList, HttpStatus.CREATED);
    }
}
