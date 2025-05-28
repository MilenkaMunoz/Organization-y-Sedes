import { adressUsers } from "./AdressUsers";
import { rolesUsers } from "./RolesUsers";
import { statusUsers } from "./StatusUsers";
import { waterBoxes } from "./WaterBoxes";
export interface user {
    id:Number;
    branchOfficeId:number;
    documentType:String;
    documentNumber:String;
    firstName:String;
    lastName:String;
    phone:String;
    email:String;
    AddressUsers:adressUsers;
    RolesUsers:rolesUsers;
    StatusUsers:statusUsers;
    registrationDate:String;
    waterBoxes:waterBoxes;
}