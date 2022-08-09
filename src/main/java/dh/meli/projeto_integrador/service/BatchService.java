package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.exception.InternalServerErrorException;
import dh.meli.projeto_integrador.model.Batch;
import dh.meli.projeto_integrador.repository.IBatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {

    @Autowired
    private IBatchRepository batchRepository;

    public Batch createBatch(Batch batch) {
        try {
            return batchRepository.save(batch);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
