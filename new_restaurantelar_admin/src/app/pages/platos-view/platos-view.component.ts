import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CreatePlatoDto } from 'src/app/models/create_plato_dto';
import { Plato } from 'src/app/models/plato_dto';
import { AuthService } from 'src/app/service/auth.service';
import { PlatoService } from 'src/app/service/plato.service';

@Component({
  selector: 'app-platos-view',
  templateUrl: './platos-view.component.html',
  styleUrls: ['./platos-view.component.css']
})
export class PlatosViewComponent implements OnInit {

  constructor(private authService: AuthService, private platoService: PlatoService) { }

  

  platos: Plato[] = [];

  roleSelected!: string;

  displayedColumns: string[] = ['id', 'name', 'description', 'imageUrl', 'price', 'nameCookMan'];

  dataSource = new MatTableDataSource<Plato>(this.platos);
  

  page: number = 0;


  @ViewChild(MatPaginator) paginator!: MatPaginator;



  ngOnInit(): void {
    this.getPlatos(this.page)
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  getPlatos(page:number) {
    return this.platoService.getPlatos(page).subscribe((u) => {
      this.platos = u.content;
      this.dataSource = new MatTableDataSource<Plato>(this.platos)
      
    })
  }

}
