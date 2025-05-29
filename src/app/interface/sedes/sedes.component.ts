import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { sedes } from '../../shared/Sedes';
import { SedesService } from '../../service/sedes.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-sedes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './sedes.component.html',
  styleUrls: ['./sedes.component.css']
})
export class SedesComponent implements OnInit {
  branchs: sedes[] = [];
  filteredSedes: sedes[] = [];
  showingActive = true;
  selectedSede: sedes | null = null;
  showAddModal = false;
  newSede: sedes = {
    branchId: '',
    name: '',
    address: '',
    phone: '',
    organizationId: '',
    status: 'ACTIVE',
    email: ''
  };

  constructor(private sedesService: SedesService) {}

  ngOnInit() {
    this.loadSedes();
  }

  loadSedes() {
    this.sedesService.getBranchs().subscribe({
      next: (data: sedes[]) => {
        console.log('Todas las sedes:', data);
        this.branchs = data;
        this.applyFilter();
      },
      error: (error: any) => {
        console.error("Error al obtener datos de las sedes:", error);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudieron cargar las sedes'
        });
      }
    });
  }

  applyFilter() {
    this.filteredSedes = this.branchs.filter(sede => 
      this.showingActive ? sede.status === 'ACTIVE' : sede.status === 'INACTIVE'
    );
  }

  toggleShowInactive() {
    this.showingActive = !this.showingActive;
    this.applyFilter();
  }

  abrirAgregarModal() {
    this.newSede = {
      branchId: '',
      name: '',
      address: '',
      phone: '',
      organizationId: '',
      status: 'ACTIVE',
      email: ''
    };
    this.showAddModal = true;
  }

  cancelarAgregar() {
    this.showAddModal = false;
  }

  agregarSede() {
    this.sedesService.createSede(this.newSede).subscribe({
      next: (data) => {
        this.branchs.push(data);
        this.applyFilter();
        this.showAddModal = false;
        Swal.fire({
          icon: 'success',
          title: 'Éxito',
          text: 'Sede agregada correctamente'
        });
      },
      error: (error) => {
        console.error('Error al agregar la sede:', error);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'No se pudo agregar la sede'
        });
      }
    });
  }

  selectEdit(sede: sedes) {
    this.selectedSede = { ...sede };
  }

  cancelarEdicion() {
    this.selectedSede = null;
  }

  guardarCambios() {
    if (this.selectedSede) {
      this.sedesService.updateSede(this.selectedSede.branchId, this.selectedSede).subscribe({
        next: (data) => {
          const index = this.branchs.findIndex(s => s.branchId === data.branchId);
          if (index !== -1) {
            this.branchs[index] = data;
            this.applyFilter();
            this.selectedSede = null;
            Swal.fire({
              icon: 'success',
              title: 'Éxito',
              text: 'Sede actualizada correctamente'
            });
          }
        },
        error: (error) => {
          console.error('Error al actualizar la sede:', error);
          Swal.fire({
            icon: 'error',
            title: 'Error',
            text: 'No se pudo actualizar la sede'
          });
        }
      });
    }
  }

  eliminarSede(branchId: string) {
    Swal.fire({
      title: '¿Estás seguro?',
      text: "No podrás revertir esta acción",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, eliminar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        this.sedesService.deleteSede(branchId).subscribe({
          next: () => {
            const index = this.branchs.findIndex(s => s.branchId === branchId);
            if (index !== -1) {
              this.branchs[index].status = 'INACTIVE';
              this.applyFilter();
              Swal.fire(
                'Eliminado!',
                'La sede ha sido eliminada.',
                'success'
              );
            }
          },
          error: (error) => {
            console.error('Error al eliminar la sede:', error);
            Swal.fire({
              icon: 'error',
              title: 'Error',
              text: 'No se pudo eliminar la sede'
            });
          }
        });
      }
    });
  }

  restoreSede(branchId: string) {
    Swal.fire({
      title: '¿Restaurar sede?',
      text: "La sede volverá a estar activa",
      icon: 'question',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Sí, restaurar',
      cancelButtonText: 'Cancelar'
    }).then((result) => {
      if (result.isConfirmed) {
        const sede = this.branchs.find(s => s.branchId === branchId);
        if (sede) {
          sede.status = 'ACTIVE';
          this.sedesService.restoreSede(branchId).subscribe({
            next: (data) => {
              const index = this.branchs.findIndex(s => s.branchId === data.branchId);
              if (index !== -1) {
                this.branchs[index] = data;
                this.applyFilter();
                Swal.fire(
                  'Restaurada!',
                  'La sede ha sido restaurada.',
                  'success'
                );
              }
            },
            error: (error) => {
              console.error('Error al restaurar la sede:', error);
              Swal.fire({
                icon: 'error',
                title: 'Error',
                text: 'No se pudo restaurar la sede'
              });
            }
          });
        }
      }
    });
  }
}