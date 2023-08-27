/*
import {BaseDto} from 'app/zynerator/dto/BaseDto.model';
*/


import {BaseDto} from "../../zynerator/dto/BaseDto.model";

export class ClientCategoryDto extends BaseDto{

    public reference: string;

    public code: string;

    

    constructor() {
        super();

        this.reference = '';
        this.code = '';

        }

}
