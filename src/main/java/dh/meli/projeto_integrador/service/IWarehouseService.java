package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.model.Warehouse;

/**
 * Interface to specify service methods implemented on OrderService class.
 * @author Diovana Valim, Thiago Guimaraes;
 * @version 0.0.1
 */
public interface IWarehouseService {
    /**
     *
     * @param id
     * @return
     */
    Warehouse findWarehouse(long id);
}
