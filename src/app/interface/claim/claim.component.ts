import { Component, OnInit } from '@angular/core';
import { ClaimService } from '../../service/claim.service';
import { claim } from '../../shared/claim';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-claim',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './claim.component.html',
  styleUrls: ['./claim.component.css']
})
export class ClaimComponent implements OnInit {
  claims: claim[] = [];
  selectedClaim: claim | null = null;
  selectedEditClaim : claim | null = null;
  showModal: boolean = true;
  showEditModal = false;
  today = new Date().toISOString().split('T')[0]; // Para el input de tipo date

  constructor(
    private claimService: ClaimService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.getAllClaim();
  }

  getAllClaim(): void {
    this.claimService.listClaim().subscribe(
      (data: claim[]) => {
        console.log('DATA recibida:', data);
        this.claims = data;
      },
      (error: any) => {
        console.log("Error al obtener datos del claim");
      }
    );
  }

  toggleDetails(claimId: string): void {
    this.selectedClaim = this.claims.find(c => c.claimId === claimId) || null;
    this.showModal = true;
  }

  closeModal(): void {
    this.showModal = false;
    this.selectedClaim = null;
  }
  
  closeEditModal(): void {
    this.showModal = false;
    this.selectedEditClaim = null;
  }

  editClaim(claim: claim): void {
  this.selectedEditClaim = { ...claim }; // Usamos una copia para evitar modificar directamente el objeto de la lista
  this.showEditModal = true;
}


updateClaim(idClaim:string): void {
  if (this.selectedClaim) {
    this.claimService.updateClaim(idClaim ,this.selectedClaim).subscribe(
      (response: any) => {
        // Actualizar la lista después de la edición
        this.getAllClaim();
        this.closeEditModal();
        // Mostrar un mensaje de éxito (opcional: usar SweetAlert o similar)
        alert('Claim actualizado correctamente.');
      },
      (error) => {
        console.error('Error al actualizar el claim', error);
        alert('Hubo un error al actualizar el claim.');
      }
    );
  }
}

  openNewClaimForm(): void {
    this.router.navigate(['/claims/new']);
  }
}
