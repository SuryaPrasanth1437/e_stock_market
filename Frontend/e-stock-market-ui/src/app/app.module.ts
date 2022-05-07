import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './site/header/header.component';
import { LoginComponent } from './site/login/login.component';
import { RouterModule, Routes } from '@angular/router';
import { RecaptchaFormsModule, RecaptchaModule, RecaptchaSettings, RecaptchaV3Module, RECAPTCHA_SETTINGS, RECAPTCHA_V3_SITE_KEY } from 'ng-recaptcha';
import { environment } from 'src/environments/environment';
import { CompanyDetailComponent } from './company-details/company-detail/company-detail.component';
import { CompanyDetailInfoComponent } from './company-details/company-detail-info/company-detail-info.component';
export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  {path:'get-all-detail',component:CompanyDetailComponent}
]
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    CompanyDetailComponent,
    CompanyDetailInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RecaptchaFormsModule,
   RecaptchaModule,
    RecaptchaV3Module,
    RouterModule.forRoot(routes)
  ],
  providers: [
    {
      provide: RECAPTCHA_SETTINGS,
      useValue: {
        siteKey: environment.recaptcha.siteKey,
      } as RecaptchaSettings,
    },
   
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
