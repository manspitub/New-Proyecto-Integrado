import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { StorageService } from 'src/app/service/storage/storage.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  constructor(private route: Router, private storage: StorageService) { }

  ngOnInit(): void {
  }

  doLogOut() {
    //Gestionar el borrado de token
    moment.locale("es")
    this.storage.removeToken();
    this.route.navigate(['/login']);
    alert('Tiempo trabajado: '+moment(localStorage.getItem('time')).fromNow())
  }

}
