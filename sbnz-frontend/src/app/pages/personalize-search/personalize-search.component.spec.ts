import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonalizeSearchComponent } from './personalize-search.component';

describe('PersonalizeSearchComponent', () => {
  let component: PersonalizeSearchComponent;
  let fixture: ComponentFixture<PersonalizeSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalizeSearchComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonalizeSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
