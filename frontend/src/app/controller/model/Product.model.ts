/*
import {BaseDto} from 'app/zynerator/dto/BaseDto.model';
*/


import {BaseDto} from "../../zynerator/dto/BaseDto.model";

export class ProductDto extends BaseDto{

    public code: string;

    public reference: string;

    

    constructor() {
        super();

        this.code = '';
        this.reference = '';

        }

}
