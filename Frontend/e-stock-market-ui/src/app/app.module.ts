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
import { RegisterCompanyComponent } from './company-details/register-company/register-company.component';
import { ViewCompanyDetailComponent } from './company-details/view-company-detail/view-company-detail.component';
import { AddStockPriceComponent } from './stocks/add-stock-price/add-stock-price.component';
import { ViewStocksComponent } from './stocks/view-stocks/view-stocks.component';
import {MatFormFieldModule  } from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {MatInputModule} from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxMatDatetimePickerModule, NgxMatNativeDateModule, NgxMatTimepickerModule } from '@angular-material-components/datetime-picker';
import { DatePipe } from '@angular/common';
export const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: '', component: LoginComponent },
  { path: 'get-all-detail', component: CompanyDetailComponent },
  { path: 'register', component: RegisterCompanyComponent },
  { path: 'view-company-detail/:companyCode', component: ViewCompanyDetailComponent },
  { path: 'add-stock-price/:companyCode', component: AddStockPriceComponent },
  { path: 'search', component: ViewStocksComponent }
]
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    CompanyDetailComponent,
    CompanyDetailInfoComponent,
    RegisterCompanyComponent,
    ViewCompanyDetailComponent,
    AddStockPriceComponent,
    ViewStocksComponent,
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
    NgxMatDatetimePickerModule,
    NgxMatTimepickerModule,
    NgxMatNativeDateModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatRadioModule,
    BrowserAnimationsModule,
    MatSelectModule,
    RouterModule.forRoot(routes)
  ],
  providers: [
    {
      provide: RECAPTCHA_SETTINGS,
      useValue: {
        siteKey: environment.recaptcha.siteKey,
      } as RecaptchaSettings,
    },
    DatePipe,

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
