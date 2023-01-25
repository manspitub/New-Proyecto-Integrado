import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CreatePedidoDto } from '../models/create_pedido_dto';
import { PedidoResponse } from '../models/pedido_dto';

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
export class PedidoService {

  constructor(private http: HttpClient) { }

  createPedido(pedidoDto: CreatePedidoDto): Observable<PedidoResponse>{
    let requestUrl = `${environment.apiBaseUrl}/pedidos`;
   
    return this.http.post<PedidoResponse>(requestUrl, pedidoDto, DEFAULT_HEADERS_2)

  }

  getPedidos(page: number):Observable<PedidoResponse> {
    let requestUrl = `${environment.apiBaseUrl}/pedidos?page=${page}`
    
    return this.http.get<PedidoResponse>(
      requestUrl,
      DEFAULT_HEADERS_2
    )
  }

}
