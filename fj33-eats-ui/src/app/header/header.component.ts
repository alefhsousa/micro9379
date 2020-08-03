import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';
import { RestauranteService } from '../services/restaurante.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {

  title = 'Caelum Eats';
  isNavbarCollapsed = true;
  user: any;

  constructor(private router: Router,
              private authenticationService: AuthenticationService,
              private restauranteService: RestauranteService) { }

  ngOnInit() {
    this.authenticationService.currentUser.subscribe(user => {
      this.user = user;
      if(user && user.roles && user.roles.includes('PARCEIRO')) {
        this.restauranteService.doUsuario(user)
          .subscribe(restaurante => user.restauranteId = restaurante.id);
      }
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['']);
  }
}
