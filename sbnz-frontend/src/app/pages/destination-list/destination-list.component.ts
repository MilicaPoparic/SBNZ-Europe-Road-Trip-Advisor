import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { DestinationService } from 'src/app/core/services/destination/destination.service';

@Component({
  selector: 'app-destination-list',
  templateUrl: './destination-list.component.html',
  styleUrls: ['./destination-list.component.scss']
})
export class DestinationListComponent implements OnInit {
  displayedColumns: string[] = ['name','localFood', 'transportation', 'categories', 'hotels','actions'];
  dataSource!: Array<Destination>;
  constructor(private destService: DestinationService,
    private toastr: ToastrService) { }

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
    // todo: open new dialog form for adding hotel to this destination
  }

  addDestination(){
    // todo: open new dialog form for adding destination
  }

}
