package ma.sir.ged.dao.facade.core;

import ma.sir.ged.zynerator.repository.AbstractRepository;
import ma.sir.ged.bean.core.PurchaseItem;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface PurchaseItemDao extends AbstractRepository<PurchaseItem,Long>  {

    List<PurchaseItem> findByProductId(Long id);
    int deleteByProductId(Long id);
    List<PurchaseItem> findByPurchaseId(Long id);
    int deleteByPurchaseId(Long id);

}
