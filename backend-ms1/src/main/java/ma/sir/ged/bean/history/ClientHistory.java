package ma.sir.ged.bean.history;

import com.fasterxml.jackson.annotation.JsonInclude;
import ma.sir.ged.zynerator.history.HistBusinessObject;
import jakarta.persistence.*;


@Entity
@Table(name = "client")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="client_seq",sequenceName="client_seq",allocationSize=1, initialValue = 1)
public class ClientHistory extends HistBusinessObject  {


    public ClientHistory() {
    super();
    }

    public ClientHistory (Long id) {
    super(id);
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="client_seq")
    public Long getId() {
    return id;
    }
}

