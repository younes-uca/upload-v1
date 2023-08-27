/*import {BaseDto} from 'app/zynerator/dto/BaseDto.model';

import {PurchaseItemDto} from 'app/controller/model/PurchaseItem.model';
import {ClientDto} from 'app/controller/model/Client.model';*/

import {BaseDto} from "../../zynerator/dto/BaseDto.model";
import {ClientDto} from "./Client.model";
import {PurchaseItemDto} from "./PurchaseItem.model";

export class PurchaseDto extends BaseDto{

    public reference: string;

   public purchaseDate: Date;

    public image: string;

    public total: null | number;

    public description: string;

    public client: ClientDto ;
     public purchaseItems: Array<PurchaseItemDto>;
    

    constructor() {
        super();

        this.reference = '';
        this.purchaseDate = null;
        this.image = '';
        this.total = null;
        this.description = '';
        this.client = new ClientDto() ;
        this.purchaseItems = new Array<PurchaseItemDto>();

        }

}
