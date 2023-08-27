import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/zynerator/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {ClientCategoryService} from 'src/app/controller/service/ClientCategory.service';
import {ClientCategoryDto} from 'src/app/controller/model/ClientCategory.model';
import {ClientCategoryCriteria} from 'src/app/controller/criteria/ClientCategoryCriteria.model';

@Component({
  selector: 'app-client-category-view-admin',
  templateUrl: './client-category-view-admin.component.html'
})
export class ClientCategoryViewAdminComponent extends AbstractViewController<ClientCategoryDto, ClientCategoryCriteria, ClientCategoryService> implements OnInit {


    constructor(private clientCategoryService: ClientCategoryService){
        super(clientCategoryService);
    }

    ngOnInit(): void {
    }




}
