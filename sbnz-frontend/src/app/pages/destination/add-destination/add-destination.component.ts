import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Category } from 'src/app/core/model/Category';
import { Destination } from 'src/app/core/model/Destination';
import { CategoryService } from 'src/app/core/services/category/category.service';
import { DestinationService } from 'src/app/core/services/destination/destination.service';

@Component({
  selector: 'app-destination',
  templateUrl: './add-destination.component.html',
  styleUrls: ['./add-destination.component.scss']
})
export class AddDestinationComponent implements OnInit {
  form!: FormGroup;
  destination!: Destination;
  localFoodArray = ["balkan", "chinese", "italian", "greek", "russian", "spanish", "german", "japanese", "hungarian", "french"];
  transportationArray = ["plane", "train", "bus", "ship", "car"];
  budgetArray = ["low", "medium", "high"];
  accommodationArray = [1, 2, 3, 4, 5];
  categories!: Category[];
  locationLat!: any;
  locationLon!: any;
  city!: any;
  images: string[] = [];

  constructor(private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private destinationService: DestinationService,
    private categoryService: CategoryService,
    public dialogRef: MatDialogRef<AddDestinationComponent>) {
    this.createForm();
  }

  ngOnInit(): void {
    this.categoryService.getCategories().subscribe(
      res => {
        console.log(res);
        this.categories = res as Category[];
      }, error => {
        console.log(error.error);
      }
    );

  }

  createForm() {
    this.form = this.fb.group({
      'localFood': [[]],
      'transportation': [''],
      'hotels': [''],
      'categories': [''],
      'name':[''],
      'images':[]
    }
    )
  };

  submit() {
    this.form.value['images'] = this.images;
    this.destination = this.form.value as Destination;
    this.destination.locationLat = this.locationLat;
    this.destination.locationLon = this.locationLon;
    this.destination.hotels = [];
    this.destination.score = 0;
    console.log(this.destination);
    this.destinationService.create(this.destination as Destination).subscribe(
      result => {
        this.form.reset();
        this.dialogRef.close({ data: result as Destination[] });
      },
      error => {
        this.toastr.error("Something went wrong!");
      }
    );
  }
  cancel() {
    this.dialogRef.close();
  }

  changeFood(event: any) {
    this.form.controls['localFood'].setValue(event.value);
  }

  changeTransportation(event: any) {
    this.form.controls['transportation'].setValue(event);
  }

  onSelectionCategory(event: any) {
    this.form.controls['categories'].setValue(event);
  }

  placeSelected(place: any) {
    this.locationLat = place.properties.lat;
    this.locationLon = place.properties.lon;
    // this.city = place.properties.address_line1 + ' ' + place.properties.address_line2;
  }

  addImage(){
    this.images.push(this.form.value['images']);
    console.log(this.images);
    this.form.value['images'] = [''];
  }



}
