package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.exception.ResourceNotFoundException;
import dh.meli.projeto_integrador.model.Agent;
import dh.meli.projeto_integrador.repository.IAgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgentService {

    @Autowired
    private IAgentRepository agentRepository;

    public Agent findAgent(long id) {
        Optional<Agent> agent = agentRepository.findById(id);

        if (agent.isEmpty()) {
            throw new ResourceNotFoundException(String.format("Could not find valid agent for id %d", id));
        }

        return agent.get();
    }
}
