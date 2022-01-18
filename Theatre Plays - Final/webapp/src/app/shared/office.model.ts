import {Director} from "./director.model";

export class Office {
  id: number;
  officeNumber: string;
  address: string;
  director: Director;

  constructor(officeNumber: string, address: string) {
    this.officeNumber = officeNumber;
    this.address = address;
  }
}
