import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments';
import { FruitDailyCount } from '../models/bms.models';

@Injectable({ providedIn: 'root' })
export class FruitService {
  private readonly http = inject(HttpClient);
  private readonly url = `${environment.apiBaseUrl}/fruits`;

  getAll(): Observable<FruitDailyCount[]> { return this.http.get<FruitDailyCount[]>(this.url); }
  getById(id: number): Observable<FruitDailyCount> { return this.http.get<FruitDailyCount>(`${this.url}/${id}`); }
  create(data: FruitDailyCount): Observable<FruitDailyCount> { return this.http.post<FruitDailyCount>(this.url, data); }
  update(id: number, data: FruitDailyCount): Observable<FruitDailyCount> { return this.http.put<FruitDailyCount>(`${this.url}/${id}`, data); }
  delete(id: number): Observable<void> { return this.http.delete<void>(`${this.url}/${id}`); }
}
