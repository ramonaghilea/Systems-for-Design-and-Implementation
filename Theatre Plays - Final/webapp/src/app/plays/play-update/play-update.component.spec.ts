import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayUpdateComponent } from './play-update.component';

describe('PlayUpdateComponent', () => {
  let component: PlayUpdateComponent;
  let fixture: ComponentFixture<PlayUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
