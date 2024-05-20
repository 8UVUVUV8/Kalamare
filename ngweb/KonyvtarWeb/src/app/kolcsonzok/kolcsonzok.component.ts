import { Component } from '@angular/core';
import { BaseService } from '../base.service';

@Component({
  selector: 'app-kolcsonzok',
  templateUrl: './kolcsonzok.component.html',
  styleUrls: ['./kolcsonzok.component.css']
})
export class KolcsonzokComponent {
  
  kolcsonzok: any[] = [];

   latszik: boolean = false;

  constructor( private base : BaseService){}

  ngOnInit(){
    this.getKolcsonzok();
  }

  getKolcsonzok(){
    this.base.getKolcsonzok().subscribe((result:any) => { console.log(result), this.kolcsonzok=result })
  }

  kiIr(id:number){
    console.log(id)
    this.latszik = true;
    console.log(this.latszik)
  }

  bezar(){
      this.latszik = false;
      console.log(this.latszik)
  }
  


}
