import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AuthLoginDto } from '../models/auth_dto';
import { AuthLoginResponse } from '../models/interfaces/auth_interface';
import { UsersResponse } from '../models/interfaces/users_response';
import { StorageService } from './storage/storage.service';

const AUTH_BASE_URL = 'auth';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
  }),
};

const DEFAULT_HEADERS_2 = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('token')}`,
  }),
};


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  

  constructor(private http: HttpClient, private storage: StorageService) { }

  BEARER_AUTHENTICATION = {
    headers: new HttpHeaders({
      'Authorization': `Bearer ${localStorage.getItem('token')}`,
    }),
  };

  login(loginDto: AuthLoginDto): Observable<AuthLoginResponse> {
    let requestUrl = `${environment.apiBaseUrl}/${AUTH_BASE_URL}/login/worker`;

    return this.http.post<AuthLoginResponse>(
      requestUrl,
      loginDto,
      DEFAULT_HEADERS
    )
  }

  getMe(): Observable<AuthLoginResponse> {
    let requestUrl = `${environment.apiBaseUrl}/${AUTH_BASE_URL}/me`;
    console.log(localStorage.getItem)
    
    return this.http.get<AuthLoginResponse>(
      requestUrl,
      this.BEARER_AUTHENTICATION
    )
  }
  //añadir lo de rol
  getUsersByRole(page: number, role: string): Observable<UsersResponse> {
    let requestUrl = `${environment.apiBaseUrl}/auth/roles/${role}?page=${page}`

    return this.http.get<UsersResponse>(
      requestUrl,
      DEFAULT_HEADERS_2
    )
  }
  
}
