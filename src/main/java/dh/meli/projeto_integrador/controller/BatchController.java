package dh.meli.projeto_integrador.controller;

import dh.meli.projeto_integrador.dto.dtoInput.CategoryDto;
import dh.meli.projeto_integrador.dto.dtoOutput.BatchStockDto;
import dh.meli.projeto_integrador.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BatchController {

    @Autowired
    private BatchService batchService;

    @GetMapping("/fresh-products/due-date/section/{sectionId}/number-of-days/{numberOfDays}")
    public ResponseEntity<List<BatchStockDto>> getBatchBySectionOrderedByDueDate(@PathVariable long sectionId,
                                                                    @PathVariable long numberOfDays) {
        List<BatchStockDto> batchStockDtoList = batchService.getBatchBySectionOrderedByDueDate(sectionId,
                numberOfDays);

        return new ResponseEntity<List<BatchStockDto>>(batchStockDtoList, HttpStatus.OK);
    }

    @GetMapping("/fresh-products/due-date/number-of-days/{numberOfDays}/category/{category}")
    public ResponseEntity<List<BatchStockDto>> getBatchByProductTypeOrderedByDueDate(@PathVariable long numberOfDays,
                                                                                  @PathVariable String category) {
        List<BatchStockDto> batchStockDtoList = batchService.getBatchByProductTypeOrderedByDueDate(numberOfDays,
                new CategoryDto(category));

        return new ResponseEntity<List<BatchStockDto>>(batchStockDtoList, HttpStatus.OK);
    }
}
