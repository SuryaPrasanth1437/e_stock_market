import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { StockPriceService } from 'src/app/service/stock-price.service';

@Component({
  selector: 'app-add-stock-price',
  templateUrl: './add-stock-price.component.html',
  styleUrls: ['./add-stock-price.component.css']
})
export class AddStockPriceComponent implements OnInit {
  "addStockPriceForm": FormGroup;
  "stockAdd":boolean;
  "stockFailAdd":boolean;
  constructor(private stockPriceService: StockPriceService, private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.addStockPriceForm = new FormGroup({
      "companyCode": new FormControl(null, [Validators.required]),
      "stckPrice": new FormControl(null, [Validators.required])
    });
    this.route.params.subscribe({
      next: (params) => {
        const companyCode = params['companyCode'];
        this.addStockPriceForm.patchValue({
          "companyCode": companyCode
        })
      }
    })
  }

  addStockPrice(){
    console.log(this.addStockPriceForm.value)
    console.log(this.addStockPriceForm.value.companyCode)
    this.stockPriceService.addStockPrices(this.addStockPriceForm.value,this.addStockPriceForm.value.companyCode).subscribe({
      next:(data:any)=>{
        this.stockAdd = true;
        setTimeout(() => {
          this.stockAdd = false;
          this.router.navigate(['/get-all-detail']);
        }, 2000);
      },
      error: (error) => {
        console.log("error" + JSON.stringify(error));
        this.stockFailAdd = true;
        setTimeout(() => {
          this.stockFailAdd = false;

        }, 2000);

      }
    })
  }

}
