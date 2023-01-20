import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthLoginDto } from '../models/auth_dto';
import { AuthLoginResponse } from '../models/interfaces/auth_interface';

const AUTH_BASE_URL = 'auth';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, ) { }

  login(loginDto: AuthLoginDto): Observable<AuthLoginResponse> {
    let requestUrl = `${environment.apiBaseUrl}/${AUTH_BASE_URL}/login/worker`;

    return this.http.post<AuthLoginResponse>(
      requestUrl,
      loginDto,
      DEFAULT_HEADERS
    )
  }
  
}
