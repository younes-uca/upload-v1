package ma.sir.ged.service.impl.admin;


import ma.sir.ged.bean.core.Client;
import ma.sir.ged.bean.history.ClientHistory;
import ma.sir.ged.dao.criteria.core.ClientCriteria;
import ma.sir.ged.dao.criteria.history.ClientHistoryCriteria;
import ma.sir.ged.dao.facade.core.ClientDao;
import ma.sir.ged.dao.facade.history.ClientHistoryDao;
import ma.sir.ged.dao.specification.core.ClientSpecification;
import ma.sir.ged.service.facade.admin.ClientAdminService;
import ma.sir.ged.zynerator.service.AbstractServiceImpl;
import ma.sir.ged.zynerator.service.Attribute;
import ma.sir.ged.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

import ma.sir.ged.zynerator.util.VelocityPdf;
import ma.sir.ged.ws.dto.ClientDto;
import org.springframework.http.HttpEntity;

import org.springframework.beans.factory.annotation.Autowired;


import ma.sir.ged.service.facade.admin.ClientCategoryAdminService ;


import java.util.List;
@Service
public class ClientAdminServiceImpl extends AbstractServiceImpl<Client,ClientHistory, ClientCriteria, ClientHistoryCriteria, ClientDao,
ClientHistoryDao> implements ClientAdminService {
    public static final String TEMPLATE = "template/client.vm";
    public static final String FILE_NAME = "client.pdf";
public static final List<Attribute> ATTRIBUTES = new ArrayList();
    static{


    ATTRIBUTES.add(new Attribute("fullName"));

    ATTRIBUTES.add(new Attribute("email"));

    }

    @Override
    public HttpEntity<byte[]> createPdf(ClientDto dto) throws Exception{
        return velocityPdf.createPdf(FILE_NAME, TEMPLATE, dto);
    }


    public Client findByReferenceEntity(Client t){
        return  dao.findByEmail(t.getEmail());
    }

    public List<Client> findByClientCategoryId(Long id){
        return dao.findByClientCategoryId(id);
    }
    public int deleteByClientCategoryId(Long id){
        return dao.deleteByClientCategoryId(id);
    }



    @Override
    protected List<Attribute> getAttributes() {
        return ATTRIBUTES;
    }


    public void configure() {
        super.configure(Client.class,ClientHistory.class, ClientHistoryCriteria.class, ClientSpecification.class);
    }


    @Autowired
    private ClientCategoryAdminService clientCategoryService ;
    @Autowired
    private VelocityPdf velocityPdf;

    public ClientAdminServiceImpl(ClientDao dao, ClientHistoryDao historyDao) {
        super(dao, historyDao);
    }

}