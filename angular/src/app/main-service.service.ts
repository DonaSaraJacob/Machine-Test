import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { VendorContactDetails } from './vendorcontactDetails';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class MainServiceService {

  vId:number;
  constructor(private _httpService: HttpClient,private router:Router) {}
  readonly APIUrl="http://localhost:9090/USTTrainingVendors/api";

  //verify username and password
  verifyUser(username:String,password:String){
    return this._httpService.get<boolean>(this.APIUrl +'/login'+'/'+username+'/'+password);
    
  }

  //get all vendor details
  getVendorDetails(): Observable<VendorContactDetails[]>{
    console.log('dona');
    return this._httpService.get<VendorContactDetails[]>(this.APIUrl+'/vendordetails/null');
  }

  //get vendor details by id
  getVendorDetailsById(): Observable<VendorContactDetails>{
    return this._httpService.get<VendorContactDetails>(this.APIUrl+'/vendorbyid/'+this.vId);
  }


  //serach by name,location,phone number
  search(searchString:String):Observable<VendorContactDetails[]>
  {
    return this._httpService.get<VendorContactDetails[]>(this.APIUrl+'/vendordetails/'+searchString);
  }

  //add vendor details
  add(vendorContactDetails:VendorContactDetails){
    let body = JSON.stringify(vendorContactDetails);
    let headers = new HttpHeaders({'Content-Type': 'application/json'});
    let options = { headers: headers }

    if(vendorContactDetails.vId){
      return this._httpService.put(this.APIUrl +'/updatevendordetails', body, options);
    }
    else{
      return this._httpService.post(this.APIUrl +'/insertvendordetails', body, options); 
  }
    }

  

  //disable vendor by id
 disable(vendordetail:VendorContactDetails,vId:number){
  let body = JSON.stringify(vendordetail);
  let headers = new HttpHeaders({'Content-Type': 'application/json'});
  let options = { headers: headers }
   return this._httpService.put(this.APIUrl +'/disablevendor/'+vId, body, options);
}

edit(vId:number)
  {
     this.vId=vId;
  }
  
}
