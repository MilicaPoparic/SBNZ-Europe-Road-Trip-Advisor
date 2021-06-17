import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { DestinationService } from 'src/app/core/services/destination/destination.service';
import { PersonalizeSearchComponent } from '../personalize-search/personalize-search.component';

@Component({
  selector: 'app-for-you-list',
  templateUrl: './for-you-list.component.html',
  styleUrls: ['./for-you-list.component.scss']
})
export class ForYouListComponent implements OnInit {

  destinations!: Destination[];
  

  constructor(private destinationService: DestinationService,
              private toastr: ToastrService,
              public dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.destinations  = [];
    this.destinationService.getDestination().subscribe(
            res => {
        this.destinations = res.body as Destination[];

        let bestScore = this.destinations[0].score;
        console.log(bestScore);
        var silverScore = 0;

        this.destinations.forEach(element => {
          let imagess = element.images as string[];
          element.image = imagess[0];
          if(element.score == bestScore) {
            element.gold = true;
          } 
          if(!element.gold && element.score! >= silverScore){
            silverScore = element.score!;
            element.silver = true;
          }

          console.log(silverScore);
        });
      }, error => {
        console.log(error.error);
      }
    );

  }

  onButtonClicked(){
    const dialogRef = this.dialog.open(PersonalizeSearchComponent);
    dialogRef.afterClosed().subscribe(result => {
      this.destinations = result.data;
      let bestScore = this.destinations[0].score;
        console.log(bestScore);
        var silverScore = 0;

        this.destinations.forEach(element => {
          let imagess = element.images as string[];
          element.image = imagess[0];
          if(element.score == bestScore) {
            element.gold = true;
          } 
          if(!element.gold && element.score! >= silverScore){
            silverScore = element.score!;
            element.silver = true;
          }


        });
        console.log(silverScore);

    });
  }

}
