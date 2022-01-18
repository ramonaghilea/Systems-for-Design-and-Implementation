import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayNewComponent } from './play-new.component';

describe('PlayNewComponent', () => {
  let component: PlayNewComponent;
  let fixture: ComponentFixture<PlayNewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PlayNewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlayNewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
