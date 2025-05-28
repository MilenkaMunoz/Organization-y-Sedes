import { Routes } from '@angular/router';
import { DashboardComponent } from './interface/dashboard/dashboard.component';
import { ClaimComponent } from './interface/claim/claim.component';
import { OrganizationComponent } from './interface/organization/organization.component';

export const routes: Routes = [

    {
        path: 'claims', component: ClaimComponent
    },
    {
        path: 'organizations', component: OrganizationComponent
    },
    {
        path: '', component: DashboardComponent
    }

];
