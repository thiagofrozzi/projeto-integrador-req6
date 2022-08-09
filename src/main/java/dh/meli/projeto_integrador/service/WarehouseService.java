package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Warehouse;
import dh.meli.projeto_integrador.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {

    @Autowired
    private IWarehouseRepository warehouseRepository;

    public Warehouse findWarehouse(long id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);

        if (warehouse.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Could not find valid warehouse for id %d", id));
        }

        return warehouse.get();
    }
}
