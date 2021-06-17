import { Category } from "./Category";

export interface User{
    id?: number;
    firstName?: string;
    lastName?: string;
    email?: string;
    password?: string;
    active?: boolean;
    verified?: boolean;
    locationLat?: number;
    locationLon?: number;
    dateOfBirth?: Date;
    interests?: Category[]; 
}
