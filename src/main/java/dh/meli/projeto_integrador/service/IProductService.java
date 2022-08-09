package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dtos.dtoOutput.BatchPropertiesDto;

import java.util.Set;

public interface IProductService {
    BatchPropertiesDto getProductBatchProps(Long id);

    Set<BatchPropertiesDto> getOrderedProductBatchProps(Long id, Character c);

}
