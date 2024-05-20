import { Component } from '@angular/core';
import { BaseService } from '../base.service';
import { KolcsonzesekComponent } from '../kolcsonzesek/kolcsonzesek.component';

@Component({
  selector: 'app-kolcsonzok',
  templateUrl: './kolcsonzok.component.html',
  styleUrls: ['./kolcsonzok.component.css']
})
export class KolcsonzokComponent {
  
  kolcsonzok: any[] = [];

   latszikid: any = null;

   kolcsonzoId: any;

  constructor( private base : BaseService){}

  ngOnInit(){
    this.getKolcsonzok();
  }

  getKolcsonzok(){
    this.base.getKolcsonzok().subscribe((result:any) => { console.log(result), this.kolcsonzok=result })
  }

  kiIr(id:number){
    this.kolcsonzoId = id;
    //localStorage.setItem("kolcsonzoid", id.toString())
    this.latszikid = id;

  }

  bezar(){
      this.latszikid = null;
  }

}
