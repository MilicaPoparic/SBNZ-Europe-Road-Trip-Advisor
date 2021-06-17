import { Category } from "./Category";
import { Hotel } from "./Hotel";

export interface Destination{
    id?: number;
    name?: string;
    locationLat?: number;
    locationLon?: number;
    localFood?: string[];
    transportation?: string[];
    score?: number;
    trending?: boolean;
    categories?: Category[];
    hotels?: Hotel[];
    images?: string[];
    image?: string;
    gold?: boolean;
    silver?: boolean;
}
