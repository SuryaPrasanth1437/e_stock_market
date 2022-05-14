import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, PatternValidator, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { CompanyDetailService } from 'src/app/service/company-detail.service';

@Component({
  selector: 'app-register-company',
  templateUrl: './register-company.component.html',
  styleUrls: ['./register-company.component.css']
})
export class RegisterCompanyComponent implements OnInit {
  "registerCompanyForm": FormGroup;
  "isRegister": boolean;
  "isExist": boolean;
  constructor(private router: Router, private companyService: CompanyDetailService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.registerCompanyForm = new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "companyName": new FormControl(null, [Validators.required]),
      "companyCEO": new FormControl(null, [Validators.required]),
      "companyTurnOver": new FormControl(null, [Validators.required, Validators.pattern('^[1-9][0-9]*$')]),
      "companyWebsite": new FormControl(null, [Validators.required]),
      "stockExchange": new FormControl(null, [Validators.required])
    });
  }
  onSubmitRegisterForm() {
    this.companyService.registerCompanyDetail(this.registerCompanyForm.value).subscribe({
      next: (v) => {
        this.isRegister = true;
        setTimeout(() => {
          this.isRegister = false;
          this.router.navigate(['/get-all-detail']);
        }, 2000);
      },
      error: (error) => {
        console.log("error" + JSON.stringify(error));
        this.isExist = true;
        setTimeout(() => {
          this.isExist = false;

        }, 2000);
        this.isExist = true;
      }

    });


  }
  restrictZero(event: any) {
    console.log(event)
    if (event.target.value.length == 0 && event.key == '0') {
      event.preventDefault();
    }
  }
}

