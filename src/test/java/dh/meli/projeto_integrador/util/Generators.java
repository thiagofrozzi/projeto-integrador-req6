package dh.meli.projeto_integrador.util;

import dh.meli.projeto_integrador.dto.BatchDto;
import dh.meli.projeto_integrador.dto.OrderEntryDto;
import dh.meli.projeto_integrador.dto.SectionDto;
import dh.meli.projeto_integrador.model.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Generators {

    public static Set<Batch> createInboundOrder() {
        Batch batch = createBatch();

        HashSet<Batch> batches = new HashSet<Batch>();

        batches.add(batch);

        return batches;
    }

    public static Batch createBatch() {
        Warehouse warehouse = new Warehouse();

        warehouse.setId(0);
        warehouse.setName("Armazém 01");
        warehouse.setAddress("Rua Almeida 259");

        Section section = new Section();

        section.setId(0);
        section.setCurrentProductLoad(130);
        section.setMaxProductLoad(2000);
        section.setProductType("Fresco");
        section.setWarehouse(warehouse);

        HashSet<Section> sections = new HashSet<Section>();

        Agent agent = new Agent();
        agent.setId(0);
        agent.setName("João Maria");
        agent.setPhoneNumber("48 999343899");
        agent.setEmailAddress("joaomaria@gmail.com");
        agent.setWarehouse(warehouse);

        HashSet<Agent> agents = new HashSet<Agent>();
        agents.add(agent);

        sections.add(section);

        warehouse.setSections(sections);
        warehouse.setAgents(agents);

        Product product = new Product();
        product.setPrice(12.20);
        product.setId(0);
        product.setName("Maçã");
        product.setType("Fruta");
        product.setSection(section);

        HashSet<Product> products = new HashSet<Product>();

        products.add(product);

        section.setProducts(products);

        OrderEntry orderEntry = new OrderEntry();

        orderEntry.setId(0);
        orderEntry.setOrderDate(LocalDate.now());
        orderEntry.setSection(section);

        HashSet<OrderEntry> orderEntries = new HashSet<OrderEntry>();

        orderEntries.add(orderEntry);

        section.setOrderEntries(orderEntries);

        Batch batch01 = new Batch();

        batch01.setId(0);
        batch01.setCurrentTemperature(10);
        batch01.setMinimumTemperature(0);
        batch01.setInitialQuantity(100);
        batch01.setCurrentQuantity(80);
        batch01.setManufacturingDate(LocalDate.now());
        batch01.setManufacturingTime(LocalTime.now());
        batch01.setDueDate(LocalDate.now());
        batch01.setProduct(product);
        batch01.setOrderEntry(orderEntry);

        HashSet<Batch> batches = new HashSet<Batch>();

        batches.add(batch01);

        orderEntry.setBatches(batches);

        return batch01;
    }

    public static OrderEntryDto createOrderEntryDto() {
        BatchDto batchDto = new BatchDto();

        batchDto.setBatchId(0);
        batchDto.setProductId(0);
        batchDto.setCurrentQuantity(100);
        batchDto.setInitialQuantity(120);
        batchDto.setCurrentTemperature(10);
        batchDto.setMinimumTemperature(0);
        batchDto.setManufacturingDate(LocalDate.now());
        batchDto.setManufacturingTime(LocalTime.now());
        batchDto.setDueDate(LocalDate.now());

        Set<BatchDto> batchDtoSet = new HashSet<BatchDto>();

        batchDtoSet.add(batchDto);

        SectionDto sectionDto = new SectionDto();

        sectionDto.setSectionId(0);
        sectionDto.setWarehouseId(0);

        OrderEntryDto orderEntryDto = new OrderEntryDto();

        orderEntryDto.setSection(sectionDto);
        orderEntryDto.setAgentId(0);
        orderEntryDto.setBatchStock(batchDtoSet);
        orderEntryDto.setOrderDate(LocalDate.now());

        return orderEntryDto;
    }

    public static OrderEntry getOrderEntry() {
        Batch batch = createBatch();

        return batch.getOrderEntry();
    }

    public static Product getProduct() {
        Batch batch = createBatch();

        return batch.getProduct();
    }

    public static Warehouse getWarehouse() {
        Batch batch = createBatch();

        return batch.getOrderEntry().getSection().getWarehouse();
    }

    public static Section getSection() {
        Batch batch = createBatch();

        return batch.getOrderEntry().getSection();
    }

    public static Section getUnavailableSection() {
        Batch batch = createBatch();

        Section section = batch.getOrderEntry().getSection();

        section.setProductType("Congelado");

        return section;
    }

    public static Agent getAgent() {
        Batch batch = createBatch();

        Set<Agent> agents = batch.getOrderEntry().getSection().getWarehouse().getAgents();
        return new ArrayList<Agent>(agents).get(0);
    }

    public static Agent getUnavailableAgent() {
        Batch batch = createBatch();

        Set<Agent> agents = batch.getOrderEntry().getSection().getWarehouse().getAgents();

        Agent agent = new ArrayList<Agent>(agents).get(0);

        Warehouse agentWarehouse = agent.getWarehouse();

        agentWarehouse.setId(50);

        return agent;
    }

    public static Batch getBatch() {
        return createBatch();
    }
}
