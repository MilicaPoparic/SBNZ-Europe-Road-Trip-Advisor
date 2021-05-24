import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { SearchParams } from 'src/app/core/model/SearchParams';
import { DestinationService } from 'src/app/core/services/destination/destination.service';

@Component({
  selector: 'app-personalize-search',
  templateUrl: './personalize-search.component.html',
  styleUrls: ['./personalize-search.component.scss']
})
export class PersonalizeSearchComponent implements OnInit {
  form!: FormGroup;
  searchParams!: SearchParams;
  localFoodArray = ["balkan", "chinese", "italian", "greek", "russian", "spanish", "german", "japanese", "hungarian", "french"];
  transportationArray = ["plane", "train", "bus", "ship", "car"];
  budgetArray = ["low", "medium", "high"];
  accommodationArray = [1, 2, 3, 4, 5];
  todayDate!: Date;



  constructor(private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private destinationService: DestinationService,
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
      'accommodation':[],
      'startDate': [null],
      'endDate': [null],
      'maxDistance': [],
      'children':[''],
      'numberOfPeople':[]
    }
  )};

  submit(){
    this.searchParams = this.form.value as SearchParams;
    this.destinationService.getDestinationBySearchParams(this.searchParams as SearchParams).subscribe(
      result => {
        this.form.reset();
        this.dialogRef.close({data: result as Destination[]});
      },
      error => {
        this.toastr.error("Something went wrong!");
      }
    );
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
