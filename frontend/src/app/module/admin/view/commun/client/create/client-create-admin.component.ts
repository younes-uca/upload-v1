import {Component, OnInit, Input} from '@angular/core';

import { AbstractCreateController } from 'src/app/zynerator/controller/AbstractCreateController';

import {ClientService} from 'src/app/controller/service/Client.service';
import {ClientDto} from 'src/app/controller/model/Client.model';
import {ClientCriteria} from 'src/app/controller/criteria/ClientCriteria.model';
import {ClientCategoryDto} from 'src/app/controller/model/ClientCategory.model';
import {ClientCategoryService} from 'src/app/controller/service/ClientCategory.service';
@Component({
  selector: 'app-client-create-admin',
  templateUrl: './client-create-admin.component.html'
})
export class ClientCreateAdminComponent extends AbstractCreateController<ClientDto, ClientCriteria, ClientService>  implements OnInit {



   private _validClientFullName = true;
   private _validClientEmail = true;
    private _validClientCategoryReference = true;
    private _validClientCategoryCode = true;

    constructor( private clientService: ClientService , private clientCategoryService: ClientCategoryService) {
        super(clientService);
    }

    ngOnInit(): void {
        //this.clientCategory = new ClientCategoryDto();
        //this.clientCategoryService.findAll().subscribe((data) => this.clientCategorys = data);
}





    public setValidation(value: boolean){
        this.validClientFullName = value;
        this.validClientEmail = value;
    }



    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateClientFullName();
        this.validateClientEmail();
    }

    public validateClientFullName(){
        if (this.stringUtilService.isEmpty(this.item.fullName)) {
        this.errorMessages.push('Full name non valide');
        this.validClientFullName = false;
        } else {
            this.validClientFullName = true;
        }
    }
    public validateClientEmail(){
        if (this.stringUtilService.isEmpty(this.item.email)) {
        this.errorMessages.push('Email non valide');
        this.validClientEmail = false;
        } else {
            this.validClientEmail = true;
        }
    }


    public async openCreateClientCategory(clientCategory: string) {
    const isPermistted = await this.roleService.isPermitted('ClientCategory', 'add');
    if(isPermistted) {
         this.clientCategory = new ClientCategoryDto();
         this.createClientCategoryDialog = true;
    }else{
        this.messageService.add({
        severity: 'error', summary: 'erreur', detail: 'problème de permission'
        });
     }
    }

    get clientCategory(): ClientCategoryDto {
        return this.clientCategoryService.item;
    }
    set clientCategory(value: ClientCategoryDto) {
        this.clientCategoryService.item = value;
    }
    get clientCategorys(): Array<ClientCategoryDto> {
        return this.clientCategoryService.items;
    }
    set clientCategorys(value: Array<ClientCategoryDto>) {
        this.clientCategoryService.items = value;
    }
    get createClientCategoryDialog(): boolean {
       return this.clientCategoryService.createDialog;
    }
    set createClientCategoryDialog(value: boolean) {
        this.clientCategoryService.createDialog= value;
    }



    get validClientFullName(): boolean {
        return this._validClientFullName;
    }

    set validClientFullName(value: boolean) {
         this._validClientFullName = value;
    }
    get validClientEmail(): boolean {
        return this._validClientEmail;
    }

    set validClientEmail(value: boolean) {
         this._validClientEmail = value;
    }

    get validClientCategoryReference(): boolean {
        return this._validClientCategoryReference;
    }
    set validClientCategoryReference(value: boolean) {
        this._validClientCategoryReference = value;
    }
    get validClientCategoryCode(): boolean {
        return this._validClientCategoryCode;
    }
    set validClientCategoryCode(value: boolean) {
        this._validClientCategoryCode = value;
    }


}
