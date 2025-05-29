import { Component } from '@angular/core';
import { OrganizationComponent } from '../organization/organization.component';
import { Router , RouterOutlet , RouterLink, RouterLinkActive} from '@angular/router';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { ClaimComponent } from '../claim/claim.component';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

  constructor(private router:Router){}

}
