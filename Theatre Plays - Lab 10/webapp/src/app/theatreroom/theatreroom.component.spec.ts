import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TheatreroomComponent } from './theatreroom.component';

describe('TheatreroomComponent', () => {
  let component: TheatreroomComponent;
  let fixture: ComponentFixture<TheatreroomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TheatreroomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TheatreroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
