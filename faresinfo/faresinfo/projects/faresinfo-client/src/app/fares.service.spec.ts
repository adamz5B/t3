import { TestBed } from '@angular/core/testing';

import { FaresService } from './fares.service';

describe('FaresService', () => {
  let service: FaresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FaresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
