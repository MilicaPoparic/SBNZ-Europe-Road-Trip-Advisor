import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'ngx-toastr';
import { Destination } from 'src/app/core/model/Destination';

@Component({
  selector: 'app-image-slider',
  templateUrl: './image-slider.component.html',
  styleUrls: ['./image-slider.component.scss']
})
export class ImageSliderComponent implements OnInit, OnChanges {
  @Input() destination!: Destination;
  @Input() imageDTO!: String[];
  images!: any[];
  slides: any[] = [];

  constructor(private sanitizer: DomSanitizer,
    private toastr: ToastrService) {
  }
  
  ngOnChanges(changes: SimpleChanges): void {
    this.slides = [];
    this.images = changes.imageDTO.currentValue;
    if (this.images !== undefined) {
      this.images.forEach(element => {
        this.slides.push(this.sanitizer.bypassSecurityTrustUrl(element));
      });
    }
  }

  ngOnInit(): void {

  }

}
