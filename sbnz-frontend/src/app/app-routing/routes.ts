import { Routes } from '@angular/router';
import { RoleGuard } from '../guards/role/role.service';
import { LoginGuard } from '../guards/login/login.service';
import { LoginPageComponent } from '../pages/auth/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/auth/register-page/register-page.component';
import { HomePageComponent } from '../pages/home-page/home-page.component';
import { ForYouListComponent } from '../pages/for-you-list/for-you-list.component';
import { DestitnationDetailsComponent } from '../pages/destination/destination-details/destination-details.component';import { ProfilePageComponent } from '../pages/profile-page/profile-page.component';
import { VerificationPageComponent } from '../pages/auth/verification-page/verification-page.component';
export const routes: Routes = [
	{
		path: '',
		component: HomePageComponent,
		canActivate: [RoleGuard],
		data: { expectedRoles: 'ROLE_ADMIN|ROLE_REGISTERED_USER' }
	},
	{
		path: 'login',
		component: LoginPageComponent,
		canActivate: [LoginGuard]
	},
	{
		path: 'register',
		component: RegisterPageComponent,
		canActivate: [LoginGuard]
	},
	{
		path: 'forYou',
		component: ForYouListComponent,
		canActivate: [RoleGuard],
	},
{
		path: 'destination/:id',
		component: DestitnationDetailsComponent,
		canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_REGISTERED_USER'}
	},
{
		path: 'profile',
		component: ProfilePageComponent,
		canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGISTERED_USER'}
	},
	{
		path:'verification/:token',
		component:VerificationPageComponent,
		canActivate: [LoginGuard]
	}
];
