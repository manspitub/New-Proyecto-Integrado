import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreatePlatoDto } from '../models/create_plato_dto';
import { Plato, PlatoResponse } from '../models/plato_dto';

const AUTH_BASE_URL = 'auth';

const DEFAULT_HEADERS = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
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
export class PlatoService {

  constructor(private http: HttpClient) { }

  createPlato(platoDto: CreatePlatoDto): Observable<PlatoResponse>{
    let requestUrl = `${environment.apiBaseUrl}/plato`;

    return this.http.post<PlatoResponse>(requestUrl, platoDto, DEFAULT_HEADERS_2)

  }

  updatePlato(platoDto: CreatePlatoDto, platoId: number):Observable<PlatoResponse> {
    let requestUrl = `${environment.apiBaseUrl}/plato/${platoId}`;

    return this.http.put<PlatoResponse>(requestUrl, platoDto, DEFAULT_HEADERS_2)
  }

  getPlatos(page: number):Observable<PlatoResponse> {
    let requestUrl = `${environment.apiBaseUrl}/plato?page=${page}`
    
    return this.http.get<PlatoResponse>(
      requestUrl,
      DEFAULT_HEADERS_2
    )
  }

  getPlatosPed():Observable<PlatoResponse> {
    let requestUrl = `${environment.apiBaseUrl}/plato`
    
    return this.http.get<PlatoResponse>(
      requestUrl,
      DEFAULT_HEADERS_2
    )
  }

  deletePlato(platoId: number) {
    let requestUrl = `${environment.apiBaseUrl}/plato/${platoId}`
    return this.http.delete(requestUrl, DEFAULT_HEADERS_2)
  }

}
