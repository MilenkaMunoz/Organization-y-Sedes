import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { sedes } from '../shared/Sedes';
import { enviroment } from '../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class SedesService {
  private baseUrL = enviroment.sedes;
  
  constructor(private http: HttpClient) { }

  getBranchs(): Observable<sedes[]> {
    return this.http.get<sedes[]>(`${this.baseUrL}/branches`);
  }

  createSede(sede: sedes): Observable<sedes> {
    return this.http.post<sedes>(`${this.baseUrL}/branches`, sede);
  }

  updateSede(branchId: string, sede: sedes): Observable<sedes> {
    return this.http.put<sedes>(`${this.baseUrL}/branches/${branchId}`, sede);
  }

  deleteSede(id: string): Observable<any> {
    return this.http.patch(`${this.baseUrL}/branches/${id}/deactivate`, {});
  }
  restoreSede(id: string): Observable<any> {
    return this.http.patch(`${this.baseUrL}/branches/${id}/activate`, {});
  }
}
