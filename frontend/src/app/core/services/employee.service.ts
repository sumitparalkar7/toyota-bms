import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments';
import { EmployeeDailyCount } from '../models/bms.models';

@Injectable({ providedIn: 'root' })
export class EmployeeService {
  private readonly http = inject(HttpClient);
  private readonly url = `${environment.apiBaseUrl}/employees`;

  getAll(): Observable<EmployeeDailyCount[]> { return this.http.get<EmployeeDailyCount[]>(this.url); }
  getById(id: number): Observable<EmployeeDailyCount> { return this.http.get<EmployeeDailyCount>(`${this.url}/${id}`); }
  create(data: EmployeeDailyCount): Observable<EmployeeDailyCount> { return this.http.post<EmployeeDailyCount>(this.url, data); }
  update(id: number, data: EmployeeDailyCount): Observable<EmployeeDailyCount> { return this.http.put<EmployeeDailyCount>(`${this.url}/${id}`, data); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
