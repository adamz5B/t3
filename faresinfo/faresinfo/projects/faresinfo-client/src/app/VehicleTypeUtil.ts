import { VehicleType } from "./VehicleTypes";

/**
 * Util for VehicleType enum
 */
export class VehicleTypeUtil {
    /**
     * Getter of graphical vehicle type representation
     * @param type of VehicleType
     * @returns character with graphical reperesentation
     */
    public static vehicleTypeGraphic(type: VehicleType): string {
        //well, I'd say this looks better than just text.
        switch (type) {
            case VehicleType.LIMOUSINE: return "🚕";
            case VehicleType.MINIVAN: return "🚐";
            case VehicleType.SUV: return "🚙";
            case VehicleType.VAN: return "🚚";
            default: return "×";
        }
    }
    /**
     * Getter of vehicle type description
     * @param type of VehicleType
     * @returns description of vehicle type reperesentation
     */
    public static vehicleTypeDescription(type: VehicleType): string {
        switch (type) {
            case VehicleType.LIMOUSINE: return "LIMOUSINE";
            case VehicleType.MINIVAN: return "MINIVAN";
            case VehicleType.SUV: return "SUV CAR";
            case VehicleType.VAN: return "CARGO VAN";
            default: return "×";
        }
    }
}