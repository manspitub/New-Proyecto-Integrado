import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthLoginDto } from 'src/app/models/auth_dto';
import { AuthService } from 'src/app/service/auth.service';
import { StorageService } from 'src/app/service/storage/storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginDto = new AuthLoginDto();

  constructor(
    private authService: AuthService,
    private router: Router,
    private storage: StorageService

  ) { }

  ngOnInit(): void {
  }

  doLogin(){
    this.authService.login(this.loginDto).subscribe((result) =>{
      this.storage.setToken(result.token);
      this.storage.setTime(result.timeWorking)
      this.router.navigate(['/home/user'])
    })
  }

  code: string = '';

  hide: boolean = true;


}
