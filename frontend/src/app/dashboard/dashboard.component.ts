import { ChangeDetectionStrategy, Component, computed, inject, signal } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { DashboardService } from '../core/services/dashboard.service';
import { DashboardSummary } from '../core/models/bms.models';

@Component({ selector: 'app-record-table', standalone: true, template: `<div class="panel records"><div class="panel-title">{{ title }} <a>View All</a></div><table><thead><tr><th>Date</th><th>Day</th><th>Count</th><th>Action</th></tr></thead><tbody>@for (r of rows; track r.date) {<tr><td>{{r.date}}</td><td>{{r.day}}</td><td>{{r.value}} {{unit}}</td><td>✎ 🗑</td></tr>}</tbody></table></div>` })
export class RecordTableComponent { title = ''; rows: {date:string;day:string;value:string}[] = []; unit = ''; }

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [DecimalPipe, RecordTableComponent],
  template: `
    <div class="page-head"><div><h1>Dashboard</h1><p>Welcome back, Sumit. Here's what's happening today.</p></div><button class="btn secondary">Export Report</button></div>

    <div class="kpi-grid">
      <article class="kpi blue"><div class="kpi-icon">♟</div><div><span>Today's Employee Count</span><strong>{{ summary().todayEmployees }}</strong><small>Employees</small><em>↑ 5% vs. yesterday</em></div></article>
      <article class="kpi green"><div class="kpi-icon">●</div><div><span>Today's Fruit Consumption</span><strong>{{ summary().todayFruitKg | number:'1.0-1' }} kg</strong><small>Total Fruits</small><em>↑ 12% vs. yesterday</em></div></article>
      <article class="kpi orange"><div class="kpi-icon">▣</div><div><span>Today's Garbage Generated</span><strong>{{ summary().todayGarbageKg | number:'1.0-1' }} kg</strong><small>Total Waste</small><em>↓ 8% vs. yesterday</em></div></article>
    </div>

    <div class="mini-grid">
      <article class="mini-card"><span>Monthly Employees</span><strong>Average: {{ summary().monthlyAverageEmployees | number:'1.0-0' }}/day</strong><span class="trend">↗</span></article>
      <article class="mini-card"><span>Monthly Fruits</span><strong>Total: {{ summary().monthlyFruitKg | number:'1.0-0' }} kg</strong><span class="trend">↗</span></article>
      <article class="mini-card"><span>Monthly Waste</span><strong>Total: {{ summary().monthlyGarbageKg | number:'1.0-0' }} kg</strong><span class="trend">↗</span></article>
    </div>

    <div class="chart-grid">
      <article class="panel"><div class="panel-title">Employee Count - Last 7 Days</div><div class="line-chart"><div class="grid-lines"></div><svg viewBox="0 0 700 260" preserveAspectRatio="none" aria-label="Employee trend"><polyline points="20,195 125,180 230,160 335,142 440,125 545,160 680,135" fill="none" stroke="currentColor" stroke-width="4"/><circle cx="20" cy="195" r="6"/><circle cx="125" cy="180" r="6"/><circle cx="230" cy="160" r="6"/><circle cx="335" cy="142" r="6"/><circle cx="440" cy="125" r="6"/><circle cx="545" cy="160" r="6"/><circle cx="680" cy="135" r="6"/></svg><div class="axis"><span>17 Sep</span><span>18</span><span>19</span><span>20</span><span>21</span><span>22</span><span>23 Sep</span></div></div></article>
      <article class="panel"><div class="panel-title">Fruit Consumption (kg) - Today</div><div class="bars">@for (item of fruits; track item.name) {<div class="bar-item"><div class="bar" [style.height.%]="item.value * 2"></div><strong>{{ item.value }}</strong><span>{{ item.name }}</span></div>}</div></article>
      <article class="panel"><div class="panel-title">Waste Distribution (kg) - Today</div><div class="donut"><div><strong>{{ summary().todayGarbageKg | number:'1.0-1' }} kg</strong><small>Total Waste</small></div></div><div class="legend">@for (item of waste; track item.name) {<span><i [class]="item.cls"></i>{{ item.name }} <b>{{ item.value }} kg</b></span>}</div></article>
    </div>

    <div class="table-grid"><app-record-table title="Recent Employee Records" [rows]="employeeRows" unit="Employees"/><app-record-table title="Recent Fruit Records" [rows]="fruitRows" unit="kg"/><app-record-table title="Recent Garbage Records" [rows]="garbageRows" unit="kg"/></div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DashboardComponent {
  private readonly dashboardService = inject(DashboardService);
  summary = signal<DashboardSummary>({ todayEmployees: 245, todayFruitKg: 142, todayGarbageKg: 96.5, monthlyAverageEmployees: 238, monthlyFruitKg: 3845, monthlyGarbageKg: 2654 });
  fruits = [{ name: 'Apple', value: 25 }, { name: 'Banana', value: 42 }, { name: 'Pear', value: 18 }, { name: 'Orange', value: 35 }, { name: 'Guava', value: 22 }];
  waste = [{ name: 'Paper', value: 15.5, cls: 'c1' }, { name: 'Plastic', value: 21.2, cls: 'c2' }, { name: 'Glass', value: 8.5, cls: 'c3' }, { name: 'Metal', value: 5, cls: 'c4' }, { name: 'Wood', value: 7.5, cls: 'c5' }, { name: 'E-Waste', value: 2, cls: 'c6' }, { name: 'Organic Food', value: 36.8, cls: 'c7' }];
  employeeRows = [{ date:'23-09-2026', day:'Wednesday', value:'245' }, { date:'22-09-2026', day:'Tuesday', value:'238' }, { date:'21-09-2026', day:'Monday', value:'251' }];
  fruitRows = [{ date:'23-09-2026', day:'Wednesday', value:'142' }, { date:'22-09-2026', day:'Tuesday', value:'138' }, { date:'21-09-2026', day:'Monday', value:'126' }];
  garbageRows = [{ date:'23-09-2026', day:'Wednesday', value:'96.5' }, { date:'22-09-2026', day:'Tuesday', value:'104.2' }, { date:'21-09-2026', day:'Monday', value:'98.7' }];
}

