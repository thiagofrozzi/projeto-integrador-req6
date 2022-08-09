package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Section;
import dh.meli.projeto_integrador.repository.ISectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SectionService {

    @Autowired
    private ISectionRepository sectionRepository;

    public Section findSection(long id) {
        Optional<Section> section = sectionRepository.findById(id);

        if (section.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Could not find valid section for id %d", id));
        }

        return section.get();
    }
}
