/**
 * Driver model
 */
export interface Driver {
  id: number;
  name: string;
  surname: string;
  nameFurigana: string;
  surnameFurigana: string;
  email: string;
  vehicleType: string;
  baseFarePrice: number;
  baseFareDistance: number;
}
