import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-personalize-search',
  templateUrl: './personalize-search.component.html',
  styleUrls: ['./personalize-search.component.scss']
})
export class PersonalizeSearchComponent implements OnInit {
  form!: FormGroup;
  localFoodArray = ["balkan", "chinese", "italian", "greek", "russian", "spanish", "german", "japanese", "hungarian", "french"];
  transportationArray = ["plane", "train", "bus", "ship", "car"];
  budgetArray = ["low", "medium", "high"];
  accommodationArray = ["1-2 stars", "3 stars", "4-5 stars"];


  constructor(private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    public dialogRef: MatDialogRef<PersonalizeSearchComponent>) {
      this.createForm();
     }

  ngOnInit(): void {
  }

  createForm() {
    this.form = this.fb.group({
      'localFood': [[]],
      'transportation': [''],
      'budget':[''],
      'accommodation':[''],
      // kategorije 
      // datum
      // udaljenost
      'children':[''],// deca
      'numberOfPeople':['']// broj osoba
    }
  )};

  submit(){
    // this.authenticationService.registerAdmin(this.user as User).subscribe(
    //   result => {
    //     this.toastr.success('New admin added!');
    //     this.form.reset();
    //     this.dialogRef.close();
    //   },
    //   error => {
    //     this.toastr.error("Email already exists!");
    //   }
    // );
    }
    cancel(){
      this.dialogRef.close();
    }

    changeFood(event: any) {
      this.form.controls['localFood'].setValue(event.value);
    }

    changeTransportation(event: any) {
      this.form.controls['transportation'].setValue(event);
    }

    changeBudget(event: any) {
      this.form.controls['budget'].setValue(event);
    }

    changeAccommodation(event: any) {
      this.form.controls['accommodation'].setValue(event);
    }

    toggle(event: MatSlideToggleChange) {
      console.log('Toggle fired');
      this.form.controls['children'].setValue(event.checked);
    }

}
