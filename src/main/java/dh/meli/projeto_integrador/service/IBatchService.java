package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.model.Batch;

/**
 * Interface to specify service methods implemented on BatchService class.
 * @author Diovana Valim
 * @version 0.0.1
 */
public interface IBatchService {

    /**
     * Method for to create a Batch
     * @param batch an object of type Batch coming from user request
     * @return an object of type Batch
     */
    Batch createBatch(Batch batch);
}
