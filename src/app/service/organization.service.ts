import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { organization } from '../shared/Organization';
import { Observable } from 'rxjs';
import { enviroment } from '../environments/environments';

@Injectable({
  providedIn: 'root'
})
export class OrganizationService {

  private baseUrl = enviroment.organization;
  constructor(private http: HttpClient) { }

  getOrganization(): Observable<organization[]> {
    return this.http.get<organization[]>(`${this.baseUrl}/organizations`);
  }


  updateOrganization(id: string, org: organization): Observable<organization> {
    return this.http.put<organization>(`${this.baseUrl}/organizations/${id}`, org);
  }

  deleteOrganization(id: string): Observable<any> {
    return this.http.patch(`${this.baseUrl}/organizations/${id}/desactivate`, {});
  }

  restoreOrganization(id: string): Observable<any> {
    return this.http.patch(`${this.baseUrl}/organizations/${id}/activate`, {});
  }
  

  createOrganization(org: organization): Observable<organization> {
    return this.http.post<organization>(`${this.baseUrl}/organizations`, org);
  }

  activateOrganization(id: string): Observable<any> {
    return this.http.patch(`${this.baseUrl}/organizations/${id}/activate`, {});
  }
}
