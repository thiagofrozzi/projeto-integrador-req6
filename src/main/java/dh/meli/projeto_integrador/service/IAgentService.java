package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.model.Agent;

/**
 * Interface to specify service methods implemented on AgentService class.
 * @author Diovana Valim
 * @version 0.0.1
 */
public interface IAgentService {
    Agent findAgent(long id);
}
