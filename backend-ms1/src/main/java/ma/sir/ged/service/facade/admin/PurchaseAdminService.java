package ma.sir.ged.service.facade.admin;

import java.util.List;
import ma.sir.ged.bean.core.Purchase;
import ma.sir.ged.dao.criteria.core.PurchaseCriteria;
import ma.sir.ged.dao.criteria.history.PurchaseHistoryCriteria;
import ma.sir.ged.zynerator.service.IService;

import ma.sir.ged.ws.dto.PurchaseDto;
import org.springframework.http.HttpEntity;

public interface PurchaseAdminService extends  IService<Purchase,PurchaseCriteria, PurchaseHistoryCriteria>  {

    List<Purchase> findByClientId(Long id);
    int deleteByClientId(Long id);

    HttpEntity<byte[]> createPdf(PurchaseDto dto) throws Exception;


}
