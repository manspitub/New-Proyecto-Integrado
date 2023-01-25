import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Pedido } from 'src/app/models/pedido_dto';
import { AuthService } from 'src/app/service/auth.service';
import { PedidoService } from 'src/app/service/pedido.service';

@Component({
  selector: 'app-pedidos',
  templateUrl: './pedidos.component.html',
  styleUrls: ['./pedidos.component.css']
})
export class PedidosComponent implements OnInit {

  constructor(private authService: AuthService, private pedidoService: PedidoService) { }

  

  pedidos: Pedido[] = [];

  roleSelected!: string;

  displayedColumns: string[] = ['id', 'fullNameClient', 'date', 'type', 'numTable',  "usernameWaiter", "namePlato", "paymentAmount"];

  dataSource = new MatTableDataSource<Pedido>(this.pedidos);
  

  page: number = 0;


  @ViewChild(MatPaginator) paginator!: MatPaginator;



  ngOnInit(): void {
    this.getPedidos(this.page)
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  getPedidos(page:number) {
    return this.pedidoService.getPedidos(page).subscribe((u) => {
      this.pedidos = u.content;
      this.dataSource = new MatTableDataSource<Pedido>(this.pedidos)
      console.log(this.pedidos)
    })
  }


}
