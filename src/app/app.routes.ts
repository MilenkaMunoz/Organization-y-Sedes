import { Routes } from '@angular/router';
import { DashboardComponent } from './interface/dashboard/dashboard.component';
import { ClaimComponent } from './interface/claim/claim.component';
import { OrganizationComponent } from './interface/organization/organization.component';
import { SedesComponent } from './interface/sedes/sedes.component';

export const routes: Routes = [
  {
    path: '', // Ruta base que cargará el DashboardComponent
    component: DashboardComponent, // DashboardComponent es el layout
    children: [ // Las rutas hijas se cargarán dentro del <router-outlet> del DashboardComponent
      { path: '', redirectTo: 'organizations', pathMatch: 'full' },
      { path: 'organizations', component: OrganizationComponent },
      { path: 'claims', component: ClaimComponent },
      { path: 'sedes', component: SedesComponent }
    ]
  }
];
