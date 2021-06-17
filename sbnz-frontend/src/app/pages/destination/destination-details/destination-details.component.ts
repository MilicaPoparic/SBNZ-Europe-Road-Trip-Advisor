import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Router, ActivatedRoute } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { DestinationService } from 'src/app/core/services/destination/destination.service';


@Component({
  selector: 'app-destination-details',
  templateUrl: './destination-details.component.html',
  styleUrls: ['./destination-details.component.scss']
})
export class DestitnationDetailsComponent implements OnInit {
  role!: string|undefined;
  destination!: Destination;
  images!: String[];
  id: any = ''; // cultural offer id
  result: any;



  constructor(
    private fb: FormBuilder,
    public dialog: MatDialog,
    private destinationService: DestinationService,
    private route: ActivatedRoute,
    private router: Router,
    private toastr: ToastrService) {
    
    // this.subscription = coService.RegenerateData$.subscribe(() =>
    //   this.getCulturalOffer()
    // );
  }
  ngOnInit(): void {
    // this.getRole();
    this.getDestination();
  }
  getDestination(): void {
    this.id = this.route.snapshot.params.id;
    this.destinationService.getOne(this.id).subscribe(
      res => {
        this.destination = res as Destination;
        console.log(this.destination);
        this.images =  this.destination.images as String[];
        console.log(this.destination.images);
      }
    );
  }


  // getRole(): void {
  //   const item = localStorage.getItem('user');
  //   if (!item) {
  //     this.role = undefined;
  //     return;
  //   }
  //   const jwt: JwtHelperService = new JwtHelperService();
  //   this.role = jwt.decodeToken(item).role;
  // }

  // confirmDialog(): void{
  //   const message = `Are you sure you want to do this?`;

  //   const dialogData = new ConfirmDialogModel('Confirm Action', message);

  //   const dialogRef = this.dialog.open(ConfirmationComponent, {
  //     maxWidth: '400px',
  //     data: dialogData
  //   });

  //   dialogRef.afterClosed().subscribe(dialogResult => {
  //     this.result = dialogResult;

  //     if (this.result === true){
  //       this.deleteCulturalOffer();
  //     }
  //   });
  // }


}
