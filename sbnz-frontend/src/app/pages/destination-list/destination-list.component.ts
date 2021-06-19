import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { DestinationService } from 'src/app/core/services/destination/destination.service';
import { AddDestinationComponent } from '../destination/add-destination/add-destination.component';
import { AddHotelComponent } from '../add-hotel/add-hotel.component';
@Component({
  selector: 'app-destination-list',
  templateUrl: './destination-list.component.html',
  styleUrls: ['./destination-list.component.scss']
})
export class DestinationListComponent implements OnInit {
  displayedColumns: string[] = ['name', 'localFood', 'transportation', 'categories', 'hotels', 'actions'];
  dataSource!: Array<Destination>;
  categories: string[] = ['shopping', 'concerts', 'museums','mountains', 'sea', 'night life', 'history', 'spa', 'sailing', 'adventure', 'hiking', 'luna park', 'city'];
  transportations: string[] = ['plain', 'train', 'bus', 'ship', 'car'];
  constructor(private destService: DestinationService,
    private toastr: ToastrService, public dialog: MatDialog) { }
  ngOnInit(): void {
    this.dataSource = [];

    this.destService.getAll().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )

  }

  addHotel(destId: any) {
    const dialogRef = this.dialog.open(AddHotelComponent, {
      width: '350px',
      data: destId
    });
    dialogRef.afterClosed().subscribe(result => {

    });
  }

  addDestination() {
    const dialogRef = this.dialog.open(AddDestinationComponent, {
      backdropClass: 'backdropBackground'
    });
    dialogRef.afterClosed().subscribe(result => {
      this.dataSource.push(result.data);
      window.location.reload();
    });
  }
  getMediterranean() {
    this.destService.getMediterranean().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }

  getEastEurope() {
    this.destService.getEastEurope().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }
  getNorthEurope() {
    this.destService.getNorthEurope().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }
  getBalkan() {
    this.destService.getBalkan().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }
  getFamily() {
    this.destService.getFamily().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }
  
  getTrending() {
    this.destService.getTrending().subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }

  selectCategory(event: any) {
    this.destService.getCategory(event.value).subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }
  selectTransportation(event: any) {
    this.destService.getTransportation(event.value).subscribe(
      response => {
        this.dataSource = response as Destination[];
      }, error => {
        this.toastr.error('Error getting data!');
      }
    )
  }
}
