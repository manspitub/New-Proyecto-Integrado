import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CreatePlatoDto } from 'src/app/models/create_plato_dto';
import { Plato } from 'src/app/models/plato_dto';
import { AuthService } from 'src/app/service/auth.service';
import { PlatoService } from 'src/app/service/plato.service';
import { UpdatePlatoComponent } from '../dialog/update-plato/update-plato.component';

@Component({
  selector: 'app-platos-view',
  templateUrl: './platos-view.component.html',
  styleUrls: ['./platos-view.component.css']
})
export class PlatosViewComponent implements OnInit {

  constructor(private dialog: MatDialog, private authService: AuthService, private platoService: PlatoService) { }

  platoDto =  new CreatePlatoDto();


  platos: Plato[] = [];

  roleSelected!: string;

  displayedColumns: string[] = ['id', 'name', 'description', 'imageUrl', 'price', 'nameCookMan', 'actions'];

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

  onDeletePlato(platoid: number) {
      
    this.platoService.deletePlato(platoid)
    this.getPlatos(5);
      
  }

  onEditClicked(id:number){
     let currentDish = this.platos.find((p) => {return p.id === id})

     this.dialog.open(UpdatePlatoComponent, {
       width: '500px',
       disableClose: false,
       data: {plato: currentDish}
     })
  }



}
