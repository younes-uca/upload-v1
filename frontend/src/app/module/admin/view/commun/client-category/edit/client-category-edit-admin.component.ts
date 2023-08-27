import {Component, OnInit, Input} from '@angular/core';


import {AbstractEditController} from 'src/app/zynerator/controller/AbstractEditController';

import {ClientCategoryService} from 'src/app/controller/service/ClientCategory.service';
import {ClientCategoryDto} from 'src/app/controller/model/ClientCategory.model';
import {ClientCategoryCriteria} from 'src/app/controller/criteria/ClientCategoryCriteria.model';



@Component({
  selector: 'app-client-category-edit-admin',
  templateUrl: './client-category-edit-admin.component.html'
})
export class ClientCategoryEditAdminComponent extends AbstractEditController<ClientCategoryDto, ClientCategoryCriteria, ClientCategoryService>   implements OnInit {


    private _validClientCategoryReference = true;
    private _validClientCategoryCode = true;




    constructor( private clientCategoryService: ClientCategoryService ) {
        super(clientCategoryService);
    }

    ngOnInit(): void {
}


    public setValidation(value : boolean){
        this.validClientCategoryReference = value;
        this.validClientCategoryCode = value;
        }
    public validateForm(): void{
        this.errorMessages = new Array<string>();
        this.validateClientCategoryReference();
        this.validateClientCategoryCode();
    }
    public validateClientCategoryReference(){
        if (this.stringUtilService.isEmpty(this.item.reference)) {
            this.errorMessages.push('Reference non valide');
            this.validClientCategoryReference = false;
        } else {
            this.validClientCategoryReference = true;
        }
    }
    public validateClientCategoryCode(){
        if (this.stringUtilService.isEmpty(this.item.code)) {
            this.errorMessages.push('Code non valide');
            this.validClientCategoryCode = false;
        } else {
            this.validClientCategoryCode = true;
        }
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
