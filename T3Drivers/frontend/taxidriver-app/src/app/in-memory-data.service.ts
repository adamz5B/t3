import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Driver } from './Driver';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const drivers = [
      {
        "id": 0,
        "name": "a",
        "surname": "a",
        "nameFurigana": "\u30A2",
        "surnameFurigana": "\u30A2",
        "email": "a@b.jp",
        "vehicleType": "L",
        "baseFarePrice": 200,
        "baseFareDistance": 100
      }
    ];
    return { drivers };
  }
 
  genId(drivers: Driver[]): number {
    return drivers.length > 0 ? Math.max(...drivers.map(drvr => drvr.id)) + 1 : 1;
  }
}
