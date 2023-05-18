import { Role } from '../enum/role.enum';

export interface User {
  phone: string;
  password: string;
  firstName: string;
  lastName: string;
  email: string;
  role: Role;
}
