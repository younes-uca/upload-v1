import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/zynerator/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {PurchaseService} from 'src/app/controller/service/Purchase.service';
/*
import {PurchaseDto} from 'src/app/controller/model/Purchase.model';
*/
import {PurchaseCriteria} from 'src/app/controller/criteria/PurchaseCriteria.model';

import {PurchaseItemDto} from 'src/app/controller/model/PurchaseItem.model';
import {PurchaseItemService} from 'src/app/controller/service/PurchaseItem.service';
import {ClientDto} from 'src/app/controller/model/Client.model';
import {ClientService} from 'src/app/controller/service/Client.service';
import {ProductDto} from 'src/app/controller/model/Product.model';
import {ProductService} from 'src/app/controller/service/Product.service';
import {PurchaseDto} from "../../../../../../controller/model/Purchase.model";
@Component({
  selector: 'app-purchase-view-admin',
  templateUrl: './purchase-view-admin.component.html'
})
export class PurchaseViewAdminComponent extends AbstractViewController<PurchaseDto, PurchaseCriteria, PurchaseService> implements OnInit {

    purchaseItems = new PurchaseItemDto();
    purchaseItemss: Array<PurchaseItemDto> = [];

    constructor(private purchaseService: PurchaseService, private purchaseItemService: PurchaseItemService, private clientService: ClientService, private productService: ProductService){
        super(purchaseService);
    }

    ngOnInit(): void {
        this.purchaseItems.product = new ProductDto();
        this.productService.findAll().subscribe((data) => this.products = data);
        this.client = new ClientDto();
        this.clientService.findAll().subscribe((data) => this.clients = data);
    }


    get product(): ProductDto {
       return this.productService.item;
    }
    set product(value: ProductDto) {
        this.productService.item = value;
    }
    get products():Array<ProductDto> {
       return this.productService.items;
    }
    set products(value: Array<ProductDto>) {
        this.productService.items = value;
    }
    get client(): ClientDto {
       return this.clientService.item;
    }
    set client(value: ClientDto) {
        this.clientService.item = value;
    }
    get clients():Array<ClientDto> {
       return this.clientService.items;
    }
    set clients(value: Array<ClientDto>) {
        this.clientService.items = value;
    }


}
