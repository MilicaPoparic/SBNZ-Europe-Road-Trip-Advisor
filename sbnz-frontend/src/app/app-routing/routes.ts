import { Routes } from '@angular/router';
import { RoleGuard } from '../guards/role/role.service';
import { LoginGuard } from '../guards/login/login.service';
import { LoginPageComponent } from '../pages/auth/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/auth/register-page/register-page.component';
import { HomePageComponent } from '../pages/home-page/home-page.component';
import { ForYouListComponent } from '../pages/for-you-list/for-you-list.component';

export const routes :Routes = [
	{
		path: '',
		component: HomePageComponent,
		canActivate: [RoleGuard],
		data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGISTERED_USER'}
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
		data: {expectedRoles: 'ROLE_ADMIN|ROLE_REGISTERED_USER'}
	}
];
