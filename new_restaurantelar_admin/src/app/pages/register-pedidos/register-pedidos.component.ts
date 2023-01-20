import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreatePedidoDto } from 'src/app/models/create_pedido_dto';
import { UserDto } from 'src/app/models/interfaces/users_response';
import { Plato } from 'src/app/models/plato_dto';
import { AuthService } from 'src/app/service/auth.service';
import { PedidoService } from 'src/app/service/pedido.service';
import { PlatoService } from 'src/app/service/plato.service';

@Component({
  selector: 'app-register-pedidos',
  templateUrl: './register-pedidos.component.html',
  styleUrls: ['./register-pedidos.component.css']
})
export class RegisterPedidosComponent implements OnInit {
  pedidoDto = new CreatePedidoDto();


  platosList: Plato[] = [];

  selectedIds = [];

  


  constructor(private router: Router, private authService: AuthService, private platoService: PlatoService, private pedidoService: PedidoService) { }

  ngOnInit(): void {
    this.platoService.getPlatosPed().subscribe((result => {
      this.platosList = result.content
      console.log(this.platosList)
    }));
  }

  registerPedido(){
    this.pedidoService.createPedido(this.pedidoDto).subscribe((result) => {
      alert(`Pedido registrado correctamente`)
      this.router.navigate(['/home/pedido']);
    })
  }
}
