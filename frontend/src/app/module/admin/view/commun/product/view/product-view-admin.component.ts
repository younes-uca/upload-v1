import {Component, OnInit} from '@angular/core';


import {AbstractViewController} from 'src/app/zynerator/controller/AbstractViewController';
import { environment } from 'src/environments/environment';

import {ProductService} from 'src/app/controller/service/Product.service';
import {ProductDto} from 'src/app/controller/model/Product.model';
import {ProductCriteria} from 'src/app/controller/criteria/ProductCriteria.model';

@Component({
  selector: 'app-product-view-admin',
  templateUrl: './product-view-admin.component.html'
})
export class ProductViewAdminComponent extends AbstractViewController<ProductDto, ProductCriteria, ProductService> implements OnInit {


    constructor(private productService: ProductService){
        super(productService);
    }

    ngOnInit(): void {
    }




}
