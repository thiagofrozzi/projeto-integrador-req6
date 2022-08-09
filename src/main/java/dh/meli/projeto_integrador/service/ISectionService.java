package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.model.Section;

/**
 * Interface to specify service methods implemented on OrderService class.
 * @author Diovana Valim, Thiago Guimaraes;
 * @version 0.0.1
 */
public interface ISectionService {
    /**
     *
     * @param id
     * @return
     */
    Section findSection(long id);
}
