/*import {BaseDto} from 'app/zynerator/dto/BaseDto.model';

import {ClientCategoryDto} from 'app/controller/model/ClientCategory.model';*/

import {BaseDto} from "../../zynerator/dto/BaseDto.model";
import {ClientCategoryDto} from "./ClientCategory.model";

export class ClientDto extends BaseDto{

    public fullName: string;

    public email: string;

    public clientCategory: ClientCategoryDto ;
    

    constructor() {
        super();

        this.fullName = '';
        this.email = '';
        this.clientCategory = new ClientCategoryDto() ;

        }

}
