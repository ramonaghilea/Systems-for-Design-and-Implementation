import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActorFilterComponent } from './actor-filter.component';

describe('ActorFilterComponent', () => {
  let component: ActorFilterComponent;
  let fixture: ComponentFixture<ActorFilterComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActorFilterComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActorFilterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
