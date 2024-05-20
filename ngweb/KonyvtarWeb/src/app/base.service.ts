import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class BaseService {

    private url="http://127.0.0.1:8000/api/"

  constructor(private http: HttpClient) { }


  getKolcsonzok(): Observable<any> {
    return this.http.get<any>(this.url+"Kolcsonzok");
  }

  postKolcsonzes(kolcsonzo_id:string, iro:string, mufaj:string, cim:string): Observable<any> {
    const kolcsonzesData = {
      kolcsonzo_id,
      iro,
      mufaj,
      cim
    }
    return this.http.post<any>(this.url+"Kolcsonzesek", kolcsonzesData);
  }

  putKolcsonzes(kolcsonzes_id:any ,kolcsonzo_id:any, iro:string, mufaj:string, cim:string): Observable<any> {
    const kolcsonzesData = {
      kolcsonzes_id,
      kolcsonzo_id,
      iro,
      mufaj,
      cim
    }
    return this.http.put<any>(this.url+"Kolcsonzesek", kolcsonzesData);
  }

  deleteKolcsonzes(id:any): Observable<any> {
    return this.http.delete<any>(this.url+"Kolcsonzesek/"+id)
  }

}
