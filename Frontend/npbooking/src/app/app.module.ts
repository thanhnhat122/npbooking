import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AvatarModule } from 'primeng/avatar';
import { OverlayPanelModule } from 'primeng/overlaypanel';
import { ButtonModule } from 'primeng/button';
import { BadgeModule } from 'primeng/badge';
import { CarouselModule } from 'primeng/carousel';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { CalendarModule } from 'primeng/calendar';
import { FormsModule } from '@angular/forms';
import { InputTextModule } from 'primeng/inputtext';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthGuard } from './auth/auth.guard';
import { AuthInterceptor } from './auth/auth.interceptor';
import { UserService } from './service/user.service';
import { TabViewModule } from 'primeng/tabview';
import { FilterComponent } from './filter/filter.component';
import { DropdownModule } from 'primeng/dropdown';
import { SkeletonModule } from 'primeng/skeleton';
import { InputSwitchModule } from 'primeng/inputswitch';
import { CheckboxModule } from 'primeng/checkbox';
import { SliderModule } from 'primeng/slider';
import { RegisterComponent } from './register/register.component';
import { AuthComponent } from './auth/auth.component';
import { BaseComponent } from './base/base.component';
import { ToastModule } from 'primeng/toast';
import { ReactiveFormsModule } from '@angular/forms';
import { AdminComponent } from './admin/admin.component';
import { MessagesModule } from 'primeng/messages';
import { AccordionModule } from 'primeng/accordion';
import { UserSettingComponent } from './user-setting/user-setting.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserResetpassComponent } from './user-resetpass/user-resetpass.component';
import { UserHistoryComponent } from './user-history/user-history.component';
import { HotelDetailComponent } from './hotel-detail/hotel-detail.component';
import { GalleriaModule } from 'primeng/galleria';
import { TableModule } from 'primeng/table';
import { SidebarModule } from 'primeng/sidebar';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HostComponent } from './host/host.component';
import { ChipsModule } from 'primeng/chips';
import { DialogModule } from 'primeng/dialog';
import { ToolbarModule } from 'primeng/toolbar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { AdminAdminComponent } from './admin-admin/admin-admin.component';
import { AdminHostComponent } from './admin-host/admin-host.component';
import { AdminUserComponent } from './admin-user/admin-user.component';
import { ActivationComponent } from './activation/activation.component';
import { PaymentStatusComponent } from './payment-status/payment-status.component';
import { ConfirmPopupModule } from 'primeng/confirmpopup';
import { ImageModule } from 'primeng/image';
import { FileUploadModule } from 'primeng/fileupload';
import { BlockUIModule } from 'primeng/blockui';
import { PanelModule } from 'primeng/panel';
import { CardModule } from 'primeng/card';
import { InputNumberModule } from 'primeng/inputnumber';
import { PasswordModule } from 'primeng/password';
import { TreeModule } from 'primeng/tree';
import { ContextMenuModule } from 'primeng/contextmenu';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { RatingModule } from 'primeng/rating';
import { MultiSelectModule } from 'primeng/multiselect';
import { DividerModule } from 'primeng/divider';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    HeaderComponent,
    FooterComponent,
    FilterComponent,
    RegisterComponent,
    AuthComponent,
    BaseComponent,
    AdminComponent,
    UserSettingComponent,
    UserProfileComponent,
    UserResetpassComponent,
    UserHistoryComponent,
    HotelDetailComponent,
    ForbiddenComponent,
    HostComponent,
    AdminAdminComponent,
    AdminHostComponent,
    AdminUserComponent,
    ActivationComponent,
    PaymentStatusComponent,
  ],
  imports: [
    FileUploadModule,
    ImageModule,
    ConfirmPopupModule,
    ConfirmDialogModule,
    ToolbarModule,
    DialogModule,
    ChipsModule,
    SidebarModule,
    TableModule,
    GalleriaModule,
    AccordionModule,
    MessagesModule,
    ToastModule,
    ReactiveFormsModule,
    SliderModule,
    CheckboxModule,
    InputSwitchModule,
    DropdownModule,
    TabViewModule,
    BrowserModule,
    AppRoutingModule,
    AvatarModule,
    OverlayPanelModule,
    ButtonModule,
    BrowserAnimationsModule,
    BadgeModule,
    CarouselModule,
    CalendarModule,
    FormsModule,
    InputTextModule,
    HttpClientModule,
    SkeletonModule,
    BlockUIModule,
    PanelModule,
    CardModule,
    InputNumberModule,
    PasswordModule,
    TreeModule,
    ContextMenuModule,
    InputTextareaModule,
    RatingModule,
    MultiSelectModule,
    DividerModule,
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
