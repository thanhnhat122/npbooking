import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActivationComponent } from './activation/activation.component';
import { AdminComponent } from './admin/admin.component';
import { AuthComponent } from './auth/auth.component';
import { AuthGuard } from './auth/auth.guard';
import { BaseComponent } from './base/base.component';
import { FilterComponent } from './filter/filter.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { HostComponent } from './host/host.component';
import { HotelDetailComponent } from './hotel-detail/hotel-detail.component';
import { LoginComponent } from './login/login.component';
import { PaymentStatusComponent } from './payment-status/payment-status.component';
import { RegisterComponent } from './register/register.component';
import { UserHistoryComponent } from './user-history/user-history.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserResetpassComponent } from './user-resetpass/user-resetpass.component';
import { UserSettingComponent } from './user-setting/user-setting.component';

const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    children: [
      {
        path: '',
        component: HomeComponent,
      },
      { path: 'hotel/:provinceId', component: FilterComponent },
      { path: 'hotel/:provinceId/:hotelId', component: HotelDetailComponent },
      {
        path: 'user',
        component: UserSettingComponent,
        children: [
          { path: 'profile', component: UserProfileComponent },
          { path: 'reset-password', component: UserResetpassComponent },
          { path: 'history', component: UserHistoryComponent },
        ],
      },
      {
        path: 'pay/:status',
        component: PaymentStatusComponent,
      },
    ],
  },
  {
    path: '',
    component: AuthComponent,
    children: [
      {
        path: 'login',
        component: LoginComponent,
      },
      {
        path: 'register',
        component: RegisterComponent,
      },
      {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AuthGuard],
        data: { roles: 'ROLE_AD' },
      },
      {
        path: 'host',
        component: HostComponent,
        canActivate: [AuthGuard],
        data: { roles: 'ROLE_NV' },
      },
      {
        path: 'forbidden',
        component: ForbiddenComponent,
      },
      {
        path: 'activation',
        component: ActivationComponent,
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
