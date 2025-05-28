import { Component } from '@angular/core';
import { OrganizationComponent } from "../organization/organization.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [OrganizationComponent],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
