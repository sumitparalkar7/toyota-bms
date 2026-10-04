import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments';
import { GarbageDailyCount } from '../models/bms.models';

@Injectable({ providedIn: 'root' })
export class GarbageService {
  private readonly http = inject(HttpClient);
  private readonly url = `${environment.apiBaseUrl}/garbage`;

  getAll(): Observable<GarbageDailyCount[]> { return this.http.get<GarbageDailyCount[]>(this.url); }
  getById(id: number): Observable<GarbageDailyCount> { return this.http.get<GarbageDailyCount>(`${this.url}/${id}`); }
  create(data: GarbageDailyCount): Observable<GarbageDailyCount> { return this.http.post<GarbageDailyCount>(this.url, data); }
  update(id: number, data: GarbageDailyCount): Observable<GarbageDailyCount> { return this.http.put<GarbageDailyCount>(`${this.url}/${id}`, data); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
