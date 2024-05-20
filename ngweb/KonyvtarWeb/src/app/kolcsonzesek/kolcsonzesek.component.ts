import { Component, Input } from '@angular/core';
import { BaseService } from '../base.service';

@Component({
  selector: 'app-kolcsonzesek',
  templateUrl: './kolcsonzesek.component.html',
  styleUrls: ['./kolcsonzesek.component.css']
})
export class KolcsonzesekComponent {


  @Input() kolcsonzoId:any

  kolcsonzesek: any[] = [];

  kolcsonzoNev :any;
  iro:any;
  mufaj:any;
  cim:any;


  constructor( private base : BaseService){}

  ngOnInit(){
    this.getKolcsonzok();
    console.log("sadsadsad"+this.kolcsonzoId)
  }

  getKolcsonzok(){
    this.base.getKolcsonzok().subscribe((result:any) => { 
      result.forEach((element: any) => {
        if (element.kolcsonzo_id == this.kolcsonzoId) {
          this.kolcsonzoNev = element.nev
          this.kolcsonzesek = element.kolcsonzesek
        }
      }); 
    })
  }

  deleteKolcsonzes(id:any){
    this.base.deleteKolcsonzes(id).subscribe((res:any) => { console.log(res), this.getKolcsonzok()})
  }

  postKolcsonzes(){
    this.base.postKolcsonzes(this.kolcsonzoNev, this.iro, this.mufaj, this.cim).subscribe((res:any) => {console.log(res), this.getKolcsonzok()})
  }

}
