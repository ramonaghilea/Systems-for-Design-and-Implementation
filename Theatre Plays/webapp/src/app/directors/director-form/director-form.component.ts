import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {Director} from "../../shared/director.model";
import {DirectorService} from "../shared/director.service";
import {Location} from "@angular/common";
import {Office} from "../../shared/office.model";

@Component({
  selector: 'app-director-form',
  templateUrl: './director-form.component.html',
  styleUrls: ['./director-form.component.css']
})
export class DirectorFormComponent implements OnInit {
  genders = ['female', 'male'];

  directorForm = new FormGroup( {
    name: new FormControl('',
      [Validators.required, Validators.minLength(2)]),
    age: new FormControl(20,
      [Validators.required, ValidateAge]),
    gender: new FormControl('', Validators.required),
    officeNumber: new FormControl(''),
    address: new FormControl('')
  });

  constructor(
    private location: Location,
    private directorService: DirectorService) { }

  ngOnInit(): void {
  }

  goBack(): void {
    this.location.back();
  }

  onSubmit(): void {
    const office: Office = <Office>{officeNumber: this.officeNumber.value, address: this.address.value};
    const director: Director = <Director>{name: this.name.value,
      age: this.age.value, gender: this.gender.value, office: office};

    this.directorService.addDirector(director)
      .subscribe(() => this.goBack());
  }

  get name() {
    return this.directorForm.get('name');
  }

  get age() {
    return this.directorForm.get('age');
  }

  get gender() {
    return this.directorForm.get('gender');
  }

  get officeNumber() {
    return this.directorForm.get('officeNumber');
  }

  get address() {
    return this.directorForm.get('address');
  }
}

function ValidateAge(control: AbstractControl): {[key: string]: any} | null  {
  if (control.value && control.value < 5) {
    return { 'ageInvalid': true };
  }
  return null;
}
