export interface Hotel {
  id: number;
  name: string;
  description: string;
  province: number;
  address: string;
  benefit: string;
  star: number;
  breakfast: boolean;
  distanceCenter: number;
  sustainTour: boolean;
  sea: boolean;
  owner?: string;
}
