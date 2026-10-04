import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeService } from '../core/services/employee.service';
import { EmployeeDailyCount } from '../core/models/bms.models';

@Component({ selector: 'app-employees', standalone: true, imports: [ReactiveFormsModule, DatePipe], template: `
<div class="page-head"><div><h1>Employee Daily Count</h1><p>Capture the daily employee count for the facility.</p></div><button class="btn primary" (click)="openForm()">+ Add Record</button></div>
@if (showForm()) {<section class="form-panel"><div class="panel-title">{{ editingId() ? 'Edit' : 'Add' }} Employee Count</div><form [formGroup]="form" (ngSubmit)="save()"><div class="form-grid"><label>Date<input type="date" formControlName="date"></label><label>Day<input [value]="day" readonly></label><label>Employee Count<input type="number" min="0" formControlName="employeeCount"></label></div><div class="form-actions"><button type="button" class="btn secondary" (click)="closeForm()">Cancel</button><button class="btn primary" [disabled]="form.invalid">Save</button></div></form></section>}
<section class="panel table-panel"><div class="toolbar"><input placeholder="Search date..." (input)="search.set($any($event.target).value)"><button class="btn secondary">Export Excel</button></div><table><thead><tr><th>Date</th><th>Day</th><th>Employee Count</th><th>Actions</th></tr></thead><tbody>@for (row of filtered(); track row.id) {<tr><td>{{row.date}}</td><td>{{row.day}}</td><td><b>{{row.employeeCount}}</b></td><td><button class="link" (click)="edit(row)">Edit</button><button class="danger-link" (click)="remove(row.id!)">Delete</button></td></tr>} @empty {<tr><td colspan="4" class="empty">No records found.</td></tr>}</tbody></table></section>
`, changeDetection: ChangeDetectionStrategy.OnPush })
export class EmployeesComponent {
  private readonly fb = inject(FormBuilder); private readonly api = inject(EmployeeService);
  rows = signal<EmployeeDailyCount[]>([]); search = signal(''); showForm = signal(false); editingId = signal<number | null>(null);
  form = this.fb.nonNullable.group({ date: [this.today(), Validators.required], employeeCount: [0, [Validators.required, Validators.min(0)]] });
  get day(): string { const value = this.form.controls.date.value; return value ? new Intl.DateTimeFormat('en-US', { weekday:'long' }).format(new Date(`${value}T00:00:00`)) : ''; }
  filtered = () => this.rows().filter(r => r.date.includes(this.search()));
  constructor(){ this.load(); }
  load(){ this.api.getAll().subscribe({ next:r=>this.rows.set(r), error:()=>this.rows.set(this.sample()) }); }
  openForm(){ this.editingId.set(null); this.form.reset({date:this.today(), employeeCount:0}); this.showForm.set(true); }
  closeForm(){ this.showForm.set(false); }
  edit(row: EmployeeDailyCount){ this.editingId.set(row.id ?? null); this.form.patchValue({date:row.date, employeeCount:row.employeeCount}); this.showForm.set(true); }
  save(){ if(this.form.invalid)return; const value={...this.form.getRawValue(), day:this.day}; const request=this.editingId()?this.api.update(this.editingId()!, value):this.api.create(value); request.subscribe({next:()=>{this.closeForm();this.load();},error:()=>{const current=this.rows(); const id=this.editingId() ?? Date.now(); this.rows.set([{...value,id},...current.filter(x=>x.id!==id)]); this.closeForm();}}); }
  remove(id:number){ if(!confirm('Delete this employee record?'))return; this.api.delete(id).subscribe({next:()=>this.load(),error:()=>this.rows.update(v=>v.filter(x=>x.id!==id))}); }
  private today(){ return new Date().toISOString().slice(0,10); }
  private sample(): EmployeeDailyCount[]{ return [{id:3,date:'2026-09-23',day:'Wednesday',employeeCount:245},{id:2,date:'2026-09-22',day:'Tuesday',employeeCount:238},{id:1,date:'2026-09-21',day:'Monday',employeeCount:251}]; }
}
