/*import {BaseDto} from 'app/zynerator/dto/BaseDto.model';

import {PurchaseDto} from 'app/controller/model/Purchase.model';
import {ProductDto} from 'app/controller/model/Product.model';*/

import {BaseDto} from "../../zynerator/dto/BaseDto.model";
import {ProductDto} from "./Product.model";
import {PurchaseDto} from "./Purchase.model";

export class PurchaseItemDto extends BaseDto {

    public price: null | number;

    public quantity: null | number;

    public product: ProductDto;
    public purchase: PurchaseDto;


    constructor() {
        super();

        this.price = null;
        this.quantity = null;
        this.product = new ProductDto();
        this.purchase = new PurchaseDto();

    }

}
