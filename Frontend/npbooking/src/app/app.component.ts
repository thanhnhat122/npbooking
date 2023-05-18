import { Component, OnInit } from '@angular/core';
import { catchError, map, Observable, of, startWith } from 'rxjs';
import { DataState } from './enum/data.state.enum';
import { AppState } from './model/app-state';
import { CustomResponse } from './model/custom.response';
import { UserService } from './service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  
}
