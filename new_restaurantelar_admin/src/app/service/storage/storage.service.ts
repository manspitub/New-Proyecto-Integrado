import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthLoginResponse } from 'src/app/models/interfaces/auth_interface';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class StorageService {


  constructor() { }

  setToken(newToken: string) {
    localStorage.setItem('token', newToken);
  }

  getSalary(salary: number){
    return salary
  }

  setTime(time: string) {
    localStorage.setItem('time', time);
  }


  


  getToken() {
    return localStorage.getItem('token');
  }

  removeToken() {
    return localStorage.removeItem('token');
  }

  /*const AUTHORIZATION_BEARER = {
    headers: new HttpHeaders({
      'Authorization': `BEARER ${getToken()}`
    }),}

  getMyUser(): Observable<AuthLoginResponse>{
    let requestUrl = `${environment.apiBaseUrl}/${AUTH_BASE_URL}/me`

    return this.http.get<AuthLoginResponse>(
      requestUrl,
      
    )
  }*/

}
