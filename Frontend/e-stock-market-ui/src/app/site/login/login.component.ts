import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ReCaptchaV3Service } from 'ng-recaptcha';
import { CompanyDetailService } from 'src/app/service/company-detail.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  "isLoggedIn": boolean;
  "isInvalid": boolean;
  "loginClicked": boolean;
  "isValidCaptcha": boolean;
  constructor( private router: Router, private companyService: CompanyDetailService, private route: ActivatedRoute) { }

  ngOnInit(): void {
  }

  onSubmit(form: NgForm) {
    console.log(form);
    console.log("form value", form.value);
    console.log("login id", form.value.username);
    this.companyService.login(form.value).subscribe({
      next: (v) => {
        this.isLoggedIn = true;
        this.isInvalid = false;
        this.loginClicked = true;
        console.log("v ",v)
        this.companyService.setToken(v.token);
        this.companyService.setlogInStatus(this.isLoggedIn);
        this.companyService.setLoginId(form.value.username);
       
        setTimeout(() => {
          this.isLoggedIn = false;
          this.router.navigate(['/get-all-detail']);
        }, 2000);
      },
      error: (error) => {
        console.log("error" + JSON.stringify(error));
        this.isLoggedIn = false;
        this.isInvalid = true;
        this.loginClicked = true;
        this.companyService.setlogInStatus(this.isLoggedIn);
      }

    })
  }

}
