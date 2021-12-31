export class VehicleType {
    static readonly LIMOUSINE = new VehicleType("🚕", "Limousine", "L");
    static readonly SUV = new VehicleType("🚙", "SUV car", "S");
    static readonly MINIVAN = new VehicleType("🚐", "Minivan", "M");
    static readonly VAN = new VehicleType("🚚", "Cargo van", "V");
    static readonly NONE = new VehicleType("×", "None", "");

    private constructor(public symbol: string, public description: string, public code: string) {
        this.symbol = symbol;
        this.description = description;
        this.code = code;
    }
    public static getMembers(): VehicleType[] {
        return [VehicleType.LIMOUSINE, VehicleType.SUV, VehicleType.MINIVAN, VehicleType.VAN];
    }
}