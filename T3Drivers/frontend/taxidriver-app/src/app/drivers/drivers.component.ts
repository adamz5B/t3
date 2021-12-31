import { Input } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { Driver } from '../Driver';
import { DriversService } from '../drivers.service';
import { VehicleType } from '../VehicleType';

@Component({
  selector: 'app-drivers',
  templateUrl: './drivers.component.html',
  styleUrls: ['./drivers.component.css']
})
export class DriversComponent implements OnInit {
  drivers: Driver[] = [];
  editableEnabled = false;
  editMode = false;
  modalShow = false;
  toBeDeleted = -1;
  static get templateDriver(): Driver {
    return {
      id: 0,
      name: '',
      surname: '',
      nameFurigana: '',
      surnameFurigana: '',
      email: '',
      vehicleType: '',
      baseFarePrice: 0,
      baseFareDistance: 0
    }
  };
  @Input() editableDriver?: Driver;
  constructor(private drvsService: DriversService) { }

  ngOnInit(): void {
    //Since we want to have all loaded on start
    this.editableDriver = DriversComponent.templateDriver;
    this.getAllDrivers();
  }

  /**
   * Loads all grivers from service
   */
  getAllDrivers(): void {
    this.drvsService.getAll().subscribe(recievedDrvrs => this.drivers = <Driver[]>recievedDrvrs);
  }

  /**
   * Commits new entry to service
   */
  commitNew() {
    if (this.editableDriver) {
      //Uncomment line bellow when mock API enabled
      //this.editableDriver.id = this.drivers.length > 0 ? Math.max(...this.drivers.map(drvr => drvr.id)) + 1 : 1;
      this.add(this.editableDriver);
    }
  }
  /**
   * Commits entry edit to service
   */
  commitEdit(id: number) {
    if (this.editableDriver) {
      this.edit(id, this.editableDriver);
    }
  }
  /**
   * Passes new entry to service
   * @param driver driver object to be created
   */
  add(driver: Driver): void {
    this.drvsService.addDriver(driver).subscribe(d => {
      this.completeOperation();
    });
  }
  /**
   * Passes entry edit to service
   * @param id driver's id to be edited
   * @param driver driver object to be edited
   */
  edit(id: number, driver: Driver): void {
    this.drvsService.updateDriver(id, driver).subscribe(d => {
      this.completeOperation();
      this.toBeDeleted = -1;
    });
  }
  /**
   * Reloads state and closes form
   */
  completeOperation(): void {
    this.getAllDrivers();
    this.editableMode(false);
  }
  /**
   * Passes entry to be deleted to service
   * @param driver driver object to be deleted
   */
  delete(driverId: number): void {
    this.drvsService.deleteDriver(driverId).subscribe(drv => {
      this.drivers = this.drivers.filter(d => d.id !== driverId);
      this.toBeDeleted = -1;
    });
  }
  /**
   * Sets form to create mode
   */
  createMode(): void {
    this.editableDriver = DriversComponent.templateDriver;
    this.editMode = false;
    this.editableMode(true);
  }
  /**
   * Sets form to edit mode
   * @param id id of Driver to be edited
   */
  editModeFor(id: number): void {
    this.editableDriver = JSON.parse(JSON.stringify(this.drivers.find(d => d.id === id)));
    this.editMode = true;
    this.editableMode(true);
  }
  /**
   * Handling general form submit
   * @param edMode flag for edit mode
   */
  onSubmit(edMode: boolean): void {
    if (edMode) {
      this.commitEdit(this.editableDriver ? this.editableDriver.id : 0)
    }
    else {
      this.commitNew();
    }
  }
  /**
   * Sets delete mode
   * @param id of Driver to be deleted
   */
  deleteModeFor(id: number): void {
    this.toBeDeleted = id;
    this.modalShow = true;
  }
  /**
   * Cancels delete state
   */
  cancelDelete(): void {
    this.modalShow = false;
    this.toBeDeleted = -1;
  }
  /**
   * Commits delete of entry
   * @param id of Driver to be deleted
   */
  commitDelete(id: number): void {
    this.modalShow = false;
    var drvr = this.drivers.find(d => d.id === id);
    if (drvr) {
      this.delete(id);
    }
  }
  /**
   * Gets e-mail value for given id
   * @param id of Driver
   * @returns e-mail address in string form
   */
  getEmail(id: number): string {
    var drvr = this.drivers.find(d => d.id === id);
    if (drvr) {
      return drvr.email;
    }
    return '';
  }
  /**
   * Sets form in editable mode depending of flag value
   * @param flag for edit mode
   */
  editableMode(flag: boolean): void {
    this.editableEnabled = flag;
  }
  /**
   * Gets vehicle type for given vehicle type code
   * @param code for vehicle type
   * @returns VehicleType object
   */
  vehicleType(code: string): VehicleType {
    return this.vehicleTypes.find(vt => vt.code == code) || VehicleType.NONE;
  }
  /**
   * Getter of vehicle types
   */
  get vehicleTypes() {
    return VehicleType.getMembers();
  }

}
