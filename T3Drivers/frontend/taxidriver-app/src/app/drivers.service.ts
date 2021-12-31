import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Driver } from './Driver';
import { of } from 'rxjs';
import { catchError, retry, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
/**
 * Remote server service
 */
export class DriversService {
  //When mock API, add "s" in the end of "api/driver"
  private serviceUrl: string = "api/driver";
  constructor(private http: HttpClient) { }

  /**
   * Gets all Drivers from server
   * @returns Observable Driver's array.
   */
  getAll(): Observable<Driver[]> {
    return this.http.get<Driver[]>(this.serviceUrl).pipe(
      retry(1)
    );
  }

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };
  /**
   * Updates existing driver
   * @param id driver's ID
   * @param Driver with form values
   * @returns any Observable response 
   */
  updateDriver(id: number, driver: Driver): Observable<any> {
    return this.http.put(`${this.serviceUrl}/${id}`, driver, this.httpOptions).pipe(
      tap(_ => console.log(`Updated Driver id=${driver.id}`))
    );
  }
  /**
   * Creates new driver
   * @param Driver with form values
   * @returns Driver Observable response 
   */
  addDriver(driver: Driver): Observable<Driver> {
    return this.http.post<Driver>(this.serviceUrl, driver, this.httpOptions).pipe(
      tap((newDriver: Driver) => console.log(`added Driver`))
    );
  }
  /**
   * Deletes existing driver
   * @param id driver's ID
   * @returns Driver Observable response 
   */
  deleteDriver(id: number): Observable<Driver> {
    return this.http.delete<Driver>(`${this.serviceUrl}/${id}`, this.httpOptions).pipe(
      tap(_ => console.log(`deleted Driver id=${id}`))
    );
  }
}
