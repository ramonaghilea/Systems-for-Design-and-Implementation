import { Directive } from '@angular/core';
import { AbstractControl, Validator, NG_VALIDATORS } from '@angular/forms';

@Directive({
  selector: '[appAgeValidator]',
  providers: [{
    provide: NG_VALIDATORS,
    useExisting: AgeValidatorDirective,
    multi: true
  }]
})
export class AgeValidatorDirective implements Validator {
  validate(control: AbstractControl) : {[key: string]: any} | null {
    if (control.value && control.value < 5) {
      return { 'ageInvalid': true };
    }
    return null;
  }
}
