import { Component, OnInit } from '@angular/core';
import { OrganizationService } from '../../service/organization.service';
import { organization } from '../../shared/Organization';
import { CommonModule, NgFor } from '@angular/common';
import { FormsModule, NgModel } from '@angular/forms';
import Swal, { SweetAlertResult } from 'sweetalert2';
import { DashboardComponent } from "../dashboard/dashboard.component";

@Component({
  selector: 'app-organization',
  standalone: true,
  imports: [NgFor, CommonModule, FormsModule],
  templateUrl: './organization.component.html',
  styleUrl: './organization.component.css'
})
export class OrganizationComponent implements OnInit{
  organizations: organization[] = [];
  filteredOrganizations: organization[] = [];
  selectedOrg: organization | null = null;
  showAddModal: boolean = false;
  newOrg: organization = {} as organization;
  showingActive: boolean = true;

  constructor(private service:OrganizationService){}

  ngOnInit(): void {
    this.getOrganization();
  }

  getOrganization(): void{
    this.service.getOrganization().subscribe(
      (data: organization[]) => {
        console.log('Todas las organizaciones:', data);
        this.organizations = data;
        this.applyFilter();
      },
      (error:any)=>{
        console.log("Error al obtener datos de la organizacion", error);
      }
    )
  }

  applyFilter(){
    this.filteredOrganizations = this.organizations.filter(org => !!org.status === this.showingActive);
    console.log('Organizaciones filtradas (', this.showingActive ? 'Activas' : 'Inactivas', '): ', this.filteredOrganizations);
  }

  selectEdit(org: organization) {
    this.selectedOrg = { ...org };
  }

  cancelarEdicion() {
    this.selectedOrg = null;
  }

  guardarCambios() {
    if (this.selectedOrg && this.selectedOrg.id) {
      this.service.updateOrganization(this.selectedOrg.id, this.selectedOrg).subscribe(
        (resp) => {
          this.getOrganization();
          this.selectedOrg = null;
          Swal.fire('Actualizado!', 'El registro ha sido actualizado.', 'success');
        },
        (error) => {
          console.error('Error al guardar los cambios', error);
          Swal.fire('Error!', 'Hubo un error al actualizar el registro.', 'error');
        }
      );
    }
  }

  eliminarOrganizacion(id: string) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: "¡No podrás revertir esto!",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar!'
    }).then((result: SweetAlertResult) => {
      if (result.isConfirmed) {
        this.service.deleteOrganization(id).subscribe(
          () => {
            this.getOrganization();
            Swal.fire(
              'Eliminado!',
              'El registro ha sido eliminado.',
              'success'
            );
          },
          (error) => {
            console.error('Error al eliminar', error);
            Swal.fire(
              'Error!',
              'Hubo un error al eliminar el registro.',
              'error'
            );
          }
        );
      }
    });
  }

  restaurarOrganizacion(id: string) {
    Swal.fire({
      title: '¿Estás seguro de restaurar?',
      text: "El registro volverá a estar activo.",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, restaurar!'
    }).then((result: SweetAlertResult) => {
      if (result.isConfirmed) {
        this.service.activateOrganization(id).subscribe(
          () => {
            this.getOrganization();
            Swal.fire(
              'Restaurado!',
              'El registro ha sido restaurado.',
              'success'
            );
          },
          (error) => {
            console.error('Error al restaurar', error);
            Swal.fire(
              'Error!',
              'Hubo un error al restaurar el registro.',
              'error'
            );
          }
        );
      }
    });
  }

  toggleShowInactive() {
    this.showingActive = !this.showingActive;
    this.applyFilter();
  }

  abrirAgregarModal() {
    this.newOrg = {} as organization;
    this.showAddModal = true;
  }

  cancelarAgregar() {
    this.showAddModal = false;
  }

  agregarOrganizacion() {
    this.service.createOrganization(this.newOrg).subscribe(
      (resp) => {
        this.getOrganization();
        this.showAddModal = false;
        Swal.fire('Creado!', 'El registro ha sido creado correctamente.', 'success');
      },
      (error) => {
        console.error('Error al crear', error);
        Swal.fire('Error!', 'Hubo un error al crear el registro.', 'error');
      }
    );
  }
}

