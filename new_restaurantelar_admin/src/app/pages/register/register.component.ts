import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthSignUpDto } from 'src/app/models/interfaces/auth_sign_up.dto';
import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  signUpDto = new AuthSignUpDto();

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  doSignUpWorker() {
    this.authService.registerWorker(this.signUpDto).subscribe(signUpResult => {
      alert(`You just have registered and here is your token ${signUpResult.token}`)
      this.router.navigate(['/home/user']);
    });
  }

}
