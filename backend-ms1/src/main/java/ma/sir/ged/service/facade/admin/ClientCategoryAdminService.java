package ma.sir.ged.service.facade.admin;

import java.util.List;
import ma.sir.ged.bean.core.ClientCategory;
import ma.sir.ged.dao.criteria.core.ClientCategoryCriteria;
import ma.sir.ged.dao.criteria.history.ClientCategoryHistoryCriteria;
import ma.sir.ged.zynerator.service.IService;

import ma.sir.ged.ws.dto.ClientCategoryDto;
import org.springframework.http.HttpEntity;

public interface ClientCategoryAdminService extends  IService<ClientCategory,ClientCategoryCriteria, ClientCategoryHistoryCriteria>  {


    HttpEntity<byte[]> createPdf(ClientCategoryDto dto) throws Exception;


}
