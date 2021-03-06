package com.camlait.global.erp.delegate.bmq;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.camlait.global.erp.dao.dm.DailyManagementRepository;
import com.camlait.global.erp.delegate.inventory.InventoryManager;
import com.camlait.global.erp.domain.dm.DailyMovement;
import com.camlait.global.erp.domain.document.Document;
import com.camlait.global.erp.domain.document.DocumentDetails;
import com.camlait.global.erp.domain.document.business.sale.CashClientBill;
import com.camlait.global.erp.domain.exception.DataStorageException;

/**
 * Default implementation of the daily movement management interface.
 * 
 * @author Martin Blaise Signe.
 */
@Transactional
@Component
public class DefaultDailyMovementManager implements DailyMovementManager {

    private final DailyManagementRepository dailyManagementRepository;
    private final InventoryManager inventoryManager;

    @Autowired
    public DefaultDailyMovementManager(DailyManagementRepository dailyManagementRepository, InventoryManager inventoryManager) {
        this.dailyManagementRepository = dailyManagementRepository;
        this.inventoryManager = inventoryManager;
    }

    @Override
    public DailyMovement addBmq(final DailyMovement dailyMovement) throws DataStorageException {
        return dailyManagementRepository.save(dailyMovement).lazyInit();
    }

    @Override
    public DailyMovement updateBmq(final DailyMovement dailyMovement) throws DataStorageException {
        final DailyMovement b = retrieveBmq(dailyMovement.getDmId());
        return dailyManagementRepository.saveAndFlush(dailyMovement.merge(b)).lazyInit();
    }

    @Override
    public DailyMovement retrieveBmq(final String bmqId) throws DataStorageException {
        final DailyMovement b = dailyManagementRepository.findOne(bmqId);
        return b == null ? null : b.lazyInit();
    }

    @Override
    public DailyMovement buildBmqDetails(final String bmqId) throws DataStorageException {
        final DailyMovement b = retrieveBmq(bmqId);
        return updateBmq(b.buildLigne());
    }

    @Override
    public Boolean removeBmq(final String bmqId) throws DataStorageException {
        final DailyMovement b = retrieveBmq(bmqId);
        if (b == null) {
            return false;
        }
        dailyManagementRepository.delete(b);
        return true;
    }

    @Override
    public Page<DailyMovement> retrieveBmqs(final String keyWord, Pageable p) throws DataStorageException {
        return dailyManagementRepository.retrieveBmqs(keyWord, p);
    }

    @Override
    public Page<DailyMovement> retrieveBmqs(final Date start, final Date end, Pageable p) throws DataStorageException {
        return dailyManagementRepository.retrieveBmqs(start, end, p);
    }

    @Override
    public void generateCashSales(final String bmqId) throws DataStorageException {
        final DailyMovement b = retrieveBmq(bmqId);
        if (b == null) {
            return;
        }
        final Document d = CashClientBill.createHeaderFromBmq(b);
        final Collection<DocumentDetails> lines = inventoryManager.getInventoryByStore(b.getStore().getStoreId()).parallelStream().map(s -> {
            return DocumentDetails.builder().document(d).lineUnitPrice(s.getProduct().getDefaultUnitprice()).product(s.getProduct())
                    .productId(s.getProduct().getProductId()).lineQuantity(s.getAvailableQuantity()).operationDirection(d.getOperationDirection()).build();

        }).collect(Collectors.toList());
        d.setDocumentDetails(lines);
        b.getDocuments().add(d);
        updateBmq(b);
    }
}
