import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FareInfo } from './FareInfo';
import { Observable } from 'rxjs';
import { retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FaresService {
  private serviceUrl: string = "servlet/api/fares";
  constructor(private http: HttpClient) { }
  /**
   * Getter of all fare prices 
   * @returns 
   */
  getAll(): Observable<FareInfo[]> {
    return this.http.get<FareInfo[]>(this.serviceUrl).pipe(retry(1));
  }
}
