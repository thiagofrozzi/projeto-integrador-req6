package dh.meli.projeto_integrador.service;

import dh.meli.projeto_integrador.dto.BatchDto;
import dh.meli.projeto_integrador.dto.OrderEntryDto;
import dh.meli.projeto_integrador.exception.ForbiddenException;
import dh.meli.projeto_integrador.exception.InternalServerErrorException;
import dh.meli.projeto_integrador.model.*;
import dh.meli.projeto_integrador.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class OrderService {

    @Autowired
    private IOrderRepository orderRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private WarehouseService warehouseService;

    @Autowired
    private SectionService sectionService;

    @Autowired
    private AgentService agentService;

    @Autowired
    private BatchService batchService;

    public Set<Batch> createInboundOrder(OrderEntryDto orderEntryDto) {
        Warehouse warehouse = warehouseService.findWarehouse(orderEntryDto.getSection().getWarehouseId());

        Section section = sectionService.findSection(orderEntryDto.getSection().getSectionId());

        Agent agent = agentService.findAgent(orderEntryDto.getAgentId());

        long agentWarehouseId = agent.getWarehouse().getId();

        if (agentWarehouseId != warehouse.getId()) {
            throw new ForbiddenException("Agent's warehouse ID does not belong to section's warehouse ID");
        }

        OrderEntry orderEntry = new OrderEntry();

        orderEntry.setSection(section);
        orderEntry.setOrderDate(orderEntryDto.getOrderDate());

        Set<Batch> batches = new HashSet<Batch>();

        int finalQuantity = 0;

        for (BatchDto batchDto : orderEntryDto.getBatchStock()) {
            Batch batch = new Batch();

            Product product = productService.findProduct(batchDto.getProductId());

            String productType = product.getSection().getProductType();

            // Validates if product's section equals request given section
            if (!productType.equals(section.getProductType())) {
                throw new ForbiddenException(String.format("Product's %s section does not equals the given section",
                        product.getName()));
            }

            batch.setCurrentTemperature(batchDto.getCurrentTemperature());
            batch.setMinimumTemperature(batchDto.getMinimumTemperature());
            batch.setInitialQuantity(batchDto.getInitialQuantity());
            batch.setCurrentQuantity(batchDto.getCurrentQuantity());
            batch.setManufacturingDate(batchDto.getManufacturingDate());
            batch.setManufacturingTime(batchDto.getManufacturingTime());
            batch.setDueDate(batchDto.getDueDate());
            batch.setProduct(product);
            batch.setOrderEntry(orderEntry);

            batches.add(batch);
            finalQuantity += batch.getInitialQuantity();
        }

        orderEntry.setBatches(batches);

        // Validates if wanted section has available space for storing new products
        if (section.getCurrentProductLoad() + finalQuantity > section.getMaxProductLoad()) {
            throw new ForbiddenException("Product batches quantity sum overtakes section maximum product load");
        }

        try {
            orderRepository.save(orderEntry);
            batches.forEach(batchService::createBatch);

            section.setCurrentProductLoad(section.getCurrentProductLoad() + finalQuantity);
            sectionService.saveSection(section);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }

        return orderEntry.getBatches();
    }
}
