import { TestBed } from '@angular/core/testing';

import { ElemService } from './elem.service';

describe('ElemService', () => {
  let service: ElemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ElemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
