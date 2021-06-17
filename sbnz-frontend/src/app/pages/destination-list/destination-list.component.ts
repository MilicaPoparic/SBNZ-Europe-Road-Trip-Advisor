import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { DestinationService } from 'src/app/core/services/destination/destination.service';
import { AddHotelComponent } from '../add-hotel/add-hotel.component';

@Component({
  selector: 'app-destination-list',
  templateUrl: './destination-list.component.html',
  styleUrls: ['./destination-list.component.scss']
})
export class DestinationListComponent implements OnInit {
  displayedColumns: string[] = ['name','localFood', 'transportation', 'categories', 'hotels','actions'];
  dataSource!: Array<Destination>;
  constructor(private destService: DestinationService,
    private toastr: ToastrService,
    private dialog: MatDialog
    ) { }

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

  addHotel(destId: any){
    const dialogRef = this.dialog.open(AddHotelComponent , {
      width: '350px',
      data: destId});
    dialogRef.afterClosed().subscribe(result => {

    });  }

  addDestination(){
    // todo: open new dialog form for adding destination
  }

}
