import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeesComponent } from './employees/employees.component';
import { FruitsComponent } from './fruits/fruits.component';
import { GarbageComponent } from './garbage/garbage.component';
import { ReportsComponent } from './reports/reports.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'dashboard' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'employees', component: EmployeesComponent },
  { path: 'fruits', component: FruitsComponent },
  { path: 'garbage', component: GarbageComponent },
  { path: 'reports', component: ReportsComponent },
  { path: '**', redirectTo: 'dashboard' }
];
