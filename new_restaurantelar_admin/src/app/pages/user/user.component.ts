import { Component, Input, OnInit } from '@angular/core';
import { AuthLoginResponse } from 'src/app/models/interfaces/auth_interface';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage/storage.service';
import * as moment from 'moment';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  @Input() user!: AuthLoginResponse

  constructor(private authService: AuthService, private storage: StorageService) { }

  getTime(){
    moment.locale("es")
    return moment(localStorage.getItem('time'))
  }

  
  ngOnInit(): void {
    this.authService.getMe().subscribe((result) => {
      this.user = result
    })
    
  }

  

}
