package ma.sir.ged.dao.facade.history;

import ma.sir.ged.zynerator.repository.AbstractHistoryRepository;
import ma.sir.ged.bean.history.PurchaseItemHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseItemHistoryDao extends AbstractHistoryRepository<PurchaseItemHistory,Long> {

}
