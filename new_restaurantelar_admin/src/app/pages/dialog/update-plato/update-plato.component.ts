import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CreatePlatoDto } from 'src/app/models/create_plato_dto';
import { Plato } from 'src/app/models/plato_dto';
import { PlatoService } from 'src/app/service/plato.service';


export interface DialodData{
  plato: Plato
}

@Component({
  selector: 'app-update-plato',
  templateUrl: './update-plato.component.html',
  styleUrls: ['./update-plato.component.css']
})
export class UpdatePlatoComponent implements OnInit {

  plato!: Plato

  platoDto = new CreatePlatoDto();

  

  constructor(private router: Router, @Inject (MAT_DIALOG_DATA) private data: DialodData, private platoService: PlatoService) { }

  ngOnInit(): void {
    this.plato = this.data.plato
    this.platoDto.description = this.plato.description;
    this.platoDto.name = this.plato.name
    this.platoDto.imageUrl = this.plato.imageUrl
    this.platoDto.price = this.plato.price
  }

  updatePlato(){
    this.platoService.updatePlato(this.platoDto, this.plato.id).subscribe((result) => {
      alert(`Plato editado correctamente: ${this.platoDto.name}`)
      this.router.navigate(['/home/plato']);
    })
  }

}
