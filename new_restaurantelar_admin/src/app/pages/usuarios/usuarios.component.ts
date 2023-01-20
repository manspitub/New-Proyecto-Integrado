import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { UserDto} from 'src/app/models/interfaces/users_response';
import { AuthService } from 'src/app/service/auth.service';

interface Food {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  foods: Food[] = [
    {value: 'admin', viewValue: 'ADMIN'},
    {value: 'user', viewValue: 'USER'},
    {value: 'worker', viewValue: 'WORKER'},
  ];

  users: UserDto[] = [];

  roleSelected!: string;

  displayedColumns: string[] = ['id', 'fullName', 'username', 'email', 'role'];

  dataSource = new MatTableDataSource<UserDto>(this.users);
  

  page: number = 0;


  @ViewChild(MatPaginator) paginator!: MatPaginator;


  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.getUsers(this.page)
  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

  getUsers(page:number) {
    return this.authService.getUsersByRole(page, this.roleSelected).subscribe((u) => {
      this.users = u.content;
      this.dataSource = new MatTableDataSource<UserDto>(this.users)
      console.log(this.foods)
    })
  }

}
