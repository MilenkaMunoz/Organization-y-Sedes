import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { enviroment } from '../environments/environments';
import { Observable, map } from 'rxjs';
import { claim } from '../shared/claim';

interface ApiResponse {
  status: boolean;
  data: claim[];
}

interface SingleApiResponse {
  status: boolean;
  data: claim;
}

@Injectable({
  providedIn: 'root'
})
export class ClaimService {
  private baseUrl = enviroment.claim;

  constructor(private http: HttpClient) {}

  listClaim(): Observable<claim[]> {
    return this.http.get<ApiResponse>(`${this.baseUrl}/claims`).pipe(
      map(response => response.data)
    );
  }

  getClaimById(id: number): Observable<claim> {
    return this.http.get<SingleApiResponse>(`${this.baseUrl}/claims/${id}`).pipe(
      map(response => response.data)
    );
  }

  createClaim(claim: claim): Observable<claim> {
    return this.http.post<SingleApiResponse>(`${this.baseUrl}/claims`, claim).pipe(
      map(response => response.data)
    );
  }

  updateClaim(id: string, claim: claim): Observable<claim> {
    return this.http.put<SingleApiResponse>(`${this.baseUrl}/claims/${id}`, claim).pipe(
      map(response => response.data)
    );
  }

  deleteClaim(id: number): Observable<boolean> {
    return this.http.delete<{status: boolean}>(`${this.baseUrl}/claims/${id}`).pipe(
      map(response => response.status)
    );
  }
}
