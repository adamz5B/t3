import { VehicleType } from "./VehicleTypes";
/**
 * Fare Information model
 */
export interface FareInfo {
  transportType: string;
  id: number;
  name: string;
  surname: string;
  nameFurigana: string;
  surnameFurigana: string;
  email: string;
  vehicleType: VehicleType;
  farePrice: number;
}