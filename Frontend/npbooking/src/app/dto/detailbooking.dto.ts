import { Room } from "../model/room";

export interface DetailBoookingDTO{
  id:number;
  bookingId:number;
  roomId:number;
  quantity:number;
  room: Room;
}
