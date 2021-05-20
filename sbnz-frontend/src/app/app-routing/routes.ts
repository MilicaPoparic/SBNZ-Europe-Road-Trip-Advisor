import { Routes } from '@angular/router';
import { RoleGuard } from '../guards/role/role.service';
import { LoginGuard } from '../guards/login/login.service';
import { LoginPageComponent } from '../pages/auth/login-page/login-page.component';
import { RegisterPageComponent } from '../pages/auth/register-page/register-page.component';

export const routes :Routes = [
	// {
	// 	path: '',
	// 	component: HomePageComponent
	// },
	{
		path: '',
		component: LoginPageComponent,
		canActivate: [LoginGuard]
	},
	{
		path: 'register',
		component: RegisterPageComponent,
		canActivate: [LoginGuard]
	}
];
