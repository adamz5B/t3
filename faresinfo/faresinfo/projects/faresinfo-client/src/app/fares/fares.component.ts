import { Component, OnInit } from '@angular/core';
import { FareInfo } from '../FareInfo';
import { FaresService } from '../fares.service';
import { VehicleType } from '../VehicleTypes';
import { VehicleTypeUtil } from '../VehicleTypeUtil';

@Component({
  selector: 'app-fares',
  templateUrl: './fares.component.html',
  styleUrls: ['./fares.component.css']
})
export class FaresComponent implements OnInit {
  faresInfo: FareInfo[] = [];
  constructor(private fareService: FaresService) { }

  /**
   * Loads all fare prices from service
   */
  getFares(): void {
    this.fareService.getAll().subscribe(recievedFares => {
      this.faresInfo = recievedFares.sort((a, b) => a.farePrice - b.farePrice);
    });
  }
  /**
   * Getter of graphical vehicle type representation
   * @param type of VehicleType
   * @returns character with graphical reperesentation
   */
  vehicleTypeGraphic(type: VehicleType): string {
    return VehicleTypeUtil.vehicleTypeGraphic(type);
  }
  /**
   * Getter of vehicle type description
   * @param type of VehicleType
   * @returns description of vehicle type reperesentation
   */
  vehicleTypeDescription(type: VehicleType): string {
    return VehicleTypeUtil.vehicleTypeDescription(type);
  }
  ngOnInit(): void {
    this.getFares();
  }

}
