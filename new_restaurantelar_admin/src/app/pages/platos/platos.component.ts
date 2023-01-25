import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CreatePlatoDto } from 'src/app/models/create_plato_dto';
import { AuthService } from 'src/app/service/auth.service';
import { PlatoService } from 'src/app/service/plato.service';

@Component({
  selector: 'app-platos',
  templateUrl: './platos.component.html',
  styleUrls: ['./platos.component.css']
})
export class PlatosComponent implements OnInit {
 platoDto = new CreatePlatoDto();


  constructor(private router: Router, private platoService: PlatoService) { }

  ngOnInit(): void {
  }

  registerPlato(){
    this.platoService.createPlato(this.platoDto).subscribe((result) => {
      alert(`Plato registrado correctamente: ${this.platoDto.name}`)
      this.router.navigate(['/home/plato']);
    })
  }

  

}
