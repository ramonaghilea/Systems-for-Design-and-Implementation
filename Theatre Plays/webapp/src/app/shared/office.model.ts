
export class Office {
  id: number;
  officeNumber: string;
  address: string;

  constructor(officeNumber: string, address: string) {
    this.officeNumber = officeNumber;
    this.address = address;
  }
}
