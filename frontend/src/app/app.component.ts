import { ChangeDetectionStrategy, Component, signal } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <div class="app-shell">
      <aside class="sidebar" [class.collapsed]="collapsed()">
        <div class="brand">
          <div class="brand-mark" aria-hidden="true">T</div>
          @if (!collapsed()) {
            <div><strong>TOYOTA</strong><span>AUTOMATED LOGISTICS</span></div>
          }
        </div>
        <nav>
          <a routerLink="/dashboard" routerLinkActive="active"><span>⌂</span><b>Dashboard</b></a>
          <a routerLink="/employees" routerLinkActive="active"><span>♟</span><b>Employees</b></a>
          <a routerLink="/fruits" routerLinkActive="active"><span>●</span><b>Fruits</b></a>
          <a routerLink="/garbage" routerLinkActive="active"><span>▣</span><b>Garbage</b></a>
          <a routerLink="/reports" routerLinkActive="active"><span>▤</span><b>Reports</b></a>
        </nav>
        <div class="sidebar-footer">Better Logistics<br>for a Sustainable Future</div>
      </aside>
      <main class="main-area">
        <header class="topbar">
          <button class="icon-btn" (click)="collapsed.set(!collapsed())" aria-label="Toggle menu">☰</button>
          <div class="page-brand">Business Management System</div>
          <div class="top-actions"><span>📅 {{ today }}</span><span class="user">● Sumit Paralkar⌄</span></div>
        </header>
        <section class="page-content"><router-outlet /></section>
      </main>
    </div>
  `,
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class AppComponent {
  collapsed = signal(false);
  today = new Intl.DateTimeFormat('en-IN', { day: '2-digit', month: 'short', year: 'numeric', weekday: 'long' }).format(new Date());
}
