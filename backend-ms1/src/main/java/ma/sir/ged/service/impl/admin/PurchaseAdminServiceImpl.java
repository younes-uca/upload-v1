package ma.sir.ged.service.impl.admin;


import ma.sir.ged.bean.core.Purchase;
import ma.sir.ged.bean.core.PurchaseItem;
import ma.sir.ged.bean.history.PurchaseHistory;
import ma.sir.ged.dao.criteria.core.PurchaseCriteria;
import ma.sir.ged.dao.criteria.history.PurchaseHistoryCriteria;
import ma.sir.ged.dao.facade.core.PurchaseDao;
import ma.sir.ged.dao.facade.history.PurchaseHistoryDao;
import ma.sir.ged.dao.specification.core.PurchaseSpecification;
import ma.sir.ged.service.facade.admin.ClientAdminService;
import ma.sir.ged.service.facade.admin.PurchaseAdminService;
import ma.sir.ged.service.facade.admin.PurchaseItemAdminService;
import ma.sir.ged.ws.dto.PurchaseDto;
import ma.sir.ged.zynerator.service.AbstractServiceImpl;
import ma.sir.ged.zynerator.service.Attribute;
import ma.sir.ged.zynerator.util.ListUtil;
import ma.sir.ged.zynerator.util.VelocityPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseAdminServiceImpl extends AbstractServiceImpl<Purchase, PurchaseHistory, PurchaseCriteria, PurchaseHistoryCriteria, PurchaseDao,
        PurchaseHistoryDao> implements PurchaseAdminService {
    public static final String TEMPLATE = "template/purchase.vm";
    public static final String FILE_NAME = "purchase.pdf";
    public static final List<Attribute> ATTRIBUTES = new ArrayList();

    static {
        ATTRIBUTES.add(new Attribute("reference"));
        ATTRIBUTES.add(new Attribute("image"));
        ATTRIBUTES.add(new Attribute("total", "BigDecimal"));
        ATTRIBUTES.add(new Attribute("description"));

    }

    @Override
    public HttpEntity<byte[]> createPdf(PurchaseDto dto) throws Exception {
        return velocityPdf.createPdf(FILE_NAME, TEMPLATE, dto);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Purchase create(Purchase t) {
        super.create(t);
        if (t.getPurchaseItems() != null) {
            t.getPurchaseItems().forEach(element -> {
                element.setPurchase(t);
                purchaseItemService.create(element);
            });
        }
        return t;
    }

    public Purchase findWithAssociatedLists(Long id) {
        Purchase result = dao.findById(id).orElse(null);
        if (result != null && result.getId() != null) {
            result.setPurchaseItems(purchaseItemService.findByPurchaseId(id));
        }
        return result;
    }

    @Transactional
    public void deleteAssociatedLists(Long id) {
        purchaseItemService.deleteByPurchaseId(id);
    }


    public void updateWithAssociatedLists(Purchase purchase) {
        if (purchase != null && purchase.getId() != null) {
            List<List<PurchaseItem>> resultPurchaseItems = purchaseItemService.getToBeSavedAndToBeDeleted(purchaseItemService.findByPurchaseId(purchase.getId()), purchase.getPurchaseItems());
            purchaseItemService.delete(resultPurchaseItems.get(1));
            ListUtil.emptyIfNull(resultPurchaseItems.get(0)).forEach(e -> e.setPurchase(purchase));
            purchaseItemService.update(resultPurchaseItems.get(0), true);
        }
    }

    public Purchase findByReferenceEntity(Purchase t) {
        return dao.findByReference(t.getReference());
    }

    public List<Purchase> findByClientId(Long id) {
        return dao.findByClientId(id);
    }

    public int deleteByClientId(Long id) {
        return dao.deleteByClientId(id);
    }


    @Override
    protected List<Attribute> getAttributes() {
        return ATTRIBUTES;
    }


    public void configure() {
        super.configure(Purchase.class, PurchaseHistory.class, PurchaseHistoryCriteria.class, PurchaseSpecification.class);
    }


    @Autowired
    private ClientAdminService clientService;
    @Autowired
    private PurchaseItemAdminService purchaseItemService;
    @Autowired
    private VelocityPdf velocityPdf;

    public PurchaseAdminServiceImpl(PurchaseDao dao, PurchaseHistoryDao historyDao) {
        super(dao, historyDao);
    }

}