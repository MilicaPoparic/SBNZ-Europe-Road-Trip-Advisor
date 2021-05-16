import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, CanActivate } from '@angular/router';
import { AuthenticationService } from '../../core/services/authentication/authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
	providedIn: 'root'
})
export class RoleGuard implements CanActivate {
	constructor(
		public auth: AuthenticationService,
		public router: Router
	) { }

	canActivate(route: ActivatedRouteSnapshot): boolean {
		const expectedRoles: string = route.data.expectedRoles;
	
		const token = localStorage.getItem('user') || '';
		const jwt: JwtHelperService = new JwtHelperService();
		let accessToken = JSON.parse(token);
		if (!token) {
			this.router.navigate(['/login']);
			return false;
		}
		const roles: string[] = expectedRoles.split('|', 2);
		const info = jwt.decodeToken(token);

		if (roles.indexOf(info.role) === -1) {
			this.router.navigate(['/']);
			return false;
		}
		return true;
	}
}
