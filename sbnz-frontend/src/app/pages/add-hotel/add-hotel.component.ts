import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Hotel } from 'src/app/core/model/Hotel';
import { DestinationService } from 'src/app/core/services/destination/destination.service';
import { HotelService } from 'src/app/core/services/hotel/hotel.service';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';


@Component({
  selector: 'app-add-hotel',
  templateUrl: './add-hotel.component.html',
  styleUrls: ['./add-hotel.component.scss']
})
export class AddHotelComponent implements OnInit {

  form!: FormGroup;
  hotel!: Hotel;

  constructor(private fb: FormBuilder,
    private hotelService: HotelService,
    private destinationService: DestinationService,
    private router: Router,
    private toastr: ToastrService,
    public dialogRef: MatDialogRef<AddHotelComponent>,
    @Inject(MAT_DIALOG_DATA) public destId: any) { }

  ngOnInit(): void {
    this.createForm();

  }

  createForm() {
    this.form = this.fb.group({
      'name': ['', Validators.required],
      'stars': ['', Validators.required],
      'childrenDiscount':[false, Validators.required]
    }
    )
  };

  saveHotel(){
    // send dest id as parameter to save it to dest in one request
    this.hotel = this.form.value
    this.hotelService.save(this.hotel as Hotel, this.destId).subscribe(
      result => {
        this.toastr.success('Hotel information saved!');
        this.form.reset();
        this.dialogRef.close();
      },
      error => {
        this.toastr.error("Error saving data!");
      }
    );
  }
  cancel() {
    this.dialogRef.close();
  }

  changeStars(event: any) {
    this.form.controls['stars'].setValue(event.value);
  }
  
  
  toggle(event: MatSlideToggleChange) {
    console.log('Toggle fired');
    this.form.controls['childrenDiscount'].setValue(event.checked);
  }

}
