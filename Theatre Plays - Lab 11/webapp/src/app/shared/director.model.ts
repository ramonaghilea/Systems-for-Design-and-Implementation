import {Office} from "./office.model";

export class Director {
  id: number;
  name: string;
  age: number;
  gender: string;
  office: Office;

  constructor(name: string, age: number, gender: string, office: Office) {
    this.name = name;
    this.age = age;
    this.gender = gender;
    this.office = office;
  }
}
