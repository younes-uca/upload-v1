package ma.sir.ged.service.impl.admin;


import ma.sir.ged.bean.core.ClientCategory;
import ma.sir.ged.bean.history.ClientCategoryHistory;
import ma.sir.ged.dao.criteria.core.ClientCategoryCriteria;
import ma.sir.ged.dao.criteria.history.ClientCategoryHistoryCriteria;
import ma.sir.ged.dao.facade.core.ClientCategoryDao;
import ma.sir.ged.dao.facade.history.ClientCategoryHistoryDao;
import ma.sir.ged.dao.specification.core.ClientCategorySpecification;
import ma.sir.ged.service.facade.admin.ClientCategoryAdminService;
import ma.sir.ged.zynerator.service.AbstractServiceImpl;
import ma.sir.ged.zynerator.service.Attribute;
import ma.sir.ged.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

import ma.sir.ged.zynerator.util.VelocityPdf;
import ma.sir.ged.ws.dto.ClientCategoryDto;
import org.springframework.http.HttpEntity;

import org.springframework.beans.factory.annotation.Autowired;




import java.util.List;
@Service
public class ClientCategoryAdminServiceImpl extends AbstractServiceImpl<ClientCategory,ClientCategoryHistory, ClientCategoryCriteria, ClientCategoryHistoryCriteria, ClientCategoryDao,
ClientCategoryHistoryDao> implements ClientCategoryAdminService {
    public static final String TEMPLATE = "template/clientCategory.vm";
    public static final String FILE_NAME = "clientCategory.pdf";
public static final List<Attribute> ATTRIBUTES = new ArrayList();
    static{


    ATTRIBUTES.add(new Attribute("reference"));

    ATTRIBUTES.add(new Attribute("code"));
    }

    @Override
    public HttpEntity<byte[]> createPdf(ClientCategoryDto dto) throws Exception{
        return velocityPdf.createPdf(FILE_NAME, TEMPLATE, dto);
    }


    public ClientCategory findByReferenceEntity(ClientCategory t){
        return  dao.findByCode(t.getCode());
    }




    @Override
    protected List<Attribute> getAttributes() {
        return ATTRIBUTES;
    }


    public void configure() {
        super.configure(ClientCategory.class,ClientCategoryHistory.class, ClientCategoryHistoryCriteria.class, ClientCategorySpecification.class);
    }


    @Autowired
    private VelocityPdf velocityPdf;

    public ClientCategoryAdminServiceImpl(ClientCategoryDao dao, ClientCategoryHistoryDao historyDao) {
        super(dao, historyDao);
    }

}