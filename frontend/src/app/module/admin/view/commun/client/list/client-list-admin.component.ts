import {Component, OnInit} from '@angular/core';
import {ClientService} from 'src/app/controller/service/Client.service';
import {ClientDto} from 'src/app/controller/model/Client.model';
import {ClientCriteria} from 'src/app/controller/criteria/ClientCriteria.model';
import {AbstractListController} from 'src/app/zynerator/controller/AbstractListController';
import { environment } from 'src/environments/environment';

import {ClientCategoryDto} from 'src/app/controller/model/ClientCategory.model';
import {ClientCategoryService} from 'src/app/controller/service/ClientCategory.service';


@Component({
  selector: 'app-client-list-admin',
  templateUrl: './client-list-admin.component.html'
})
export class ClientListAdminComponent extends AbstractListController<ClientDto, ClientCriteria, ClientService>  implements OnInit {

    fileName = 'Client';

    clientCategorys :Array<ClientCategoryDto>;

constructor( private clientService: ClientService , private clientCategoryService: ClientCategoryService) {
        super(clientService);
        this.pdfName='Client.pdf';
    }

    ngOnInit() : void {
      this.findPaginatedByCriteria();
      this.initExport();
      this.initCol();
      this.loadClientCategory();
    }

    public async loadClients(){
        await this.roleService.findAll();
        const isPermistted = await this.roleService.isPermitted('Client', 'list');
        isPermistted ? this.service.findAll().subscribe(clients => this.items = clients,error=>console.log(error))
        : this.messageService.add({severity: 'error', summary: 'erreur', detail: 'problème d\'autorisation'});
    }


    public initCol() {
        this.cols = [
            {field: 'fullName', header: 'Full name'},
            {field: 'email', header: 'Email'},
            {field: 'clientCategory?.reference', header: 'Client category'},
        ];
    }


    public async loadClientCategory(){
        await this.roleService.findAll();
        const isPermistted = await this.roleService.isPermitted('Client', 'list');
        isPermistted ? this.clientCategoryService.findAllOptimized().subscribe(clientCategorys => this.clientCategorys = clientCategorys,error=>console.log(error))
        : this.messageService.add({severity: 'error', summary: 'Erreur', detail: 'Problème de permission'});
    }

	public initDuplicate(res: ClientDto) {
	}

   public prepareColumnExport() : void {
        this.exportData = this.items.map(e => {
            return {
                 'Full name': e.fullName ,
                 'Email': e.email ,
                'Client category': e.clientCategory?.reference ,
            }
        });

        this.criteriaData = [{
            'Full name': this.criteria.fullName ? this.criteria.fullName : environment.emptyForExport ,
            'Email': this.criteria.email ? this.criteria.email : environment.emptyForExport ,
        //'Client category': this.criteria.clientCategory?.reference ? this.criteria.clientCategory?.reference : environment.emptyForExport ,
        }];
      }
}
