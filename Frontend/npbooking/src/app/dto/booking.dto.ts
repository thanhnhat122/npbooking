import { Hotel } from "../model/hotel";

export interface BookingDTO {
  id: number;
  dateIn: string;
  dateOut: string;
  userEmail: number[];
  sumPrice: number;
  status: String;
  hotelId: number;
  hotel: Hotel ;
  priceMax: number;
  numPeople: number;
  distanceCenter: number;
}
