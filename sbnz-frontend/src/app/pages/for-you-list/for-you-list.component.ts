import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';
import { DestinationService } from 'src/app/core/services/destination/destination.service';

@Component({
  selector: 'app-for-you-list',
  templateUrl: './for-you-list.component.html',
  styleUrls: ['./for-you-list.component.scss']
})
export class ForYouListComponent implements OnInit {

  destinations!: Destination[];
  

  constructor(private destinationService: DestinationService,
              private toastr: ToastrService) {
  }

  ngOnInit(): void {
    this.destinations  = [];
    this.destinationService.getDestination().subscribe(
            res => {
        this.destinations = res.body as Destination[];
        // this.culturalOffers.forEach(element => {
        // this.images = element.imageDTO as Img[];
        // if (this.images.length === 0){
        //   return;
        // }
        // this.imageService.getImage(this.images[0]?.id).subscribe(
        //   response => {
        //     const base64String = btoa(String.fromCharCode(...new Uint8Array(response.body)));
        //     const objectURL = 'data:image/jpg;base64,' + base64String;
        //     this.base64image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
        // //     element.base64image = this.base64image;

        //   }, error => {
        //     console.log(error.error);

        //   });
      //  });
            }, error => {
        console.log(error.error);
      }
    );

  }

}
