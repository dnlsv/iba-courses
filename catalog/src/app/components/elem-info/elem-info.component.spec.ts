import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ElemInfoComponent } from './elem-info.component';

describe('ElemInfoComponent', () => {
  let component: ElemInfoComponent;
  let fixture: ComponentFixture<ElemInfoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ElemInfoComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ElemInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
