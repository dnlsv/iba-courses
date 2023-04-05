import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElemItemComponent } from './elem-item.component';

describe('ElemItemComponent', () => {
  let component: ElemItemComponent;
  let fixture: ComponentFixture<ElemItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ElemItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElemItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
