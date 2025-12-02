import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

export interface GlobalConfig {
  appName: string;
  logoUrl: string;
  primaryColor: string;
  secondaryColor: string;
  timezone: string;
}

export interface UserPreference {
  userId: string;
  theme: string;
  language: string;
}

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  private apiUrl = '/api/config';

  private configSubject = new BehaviorSubject<GlobalConfig | null>(null);
  config$ = this.configSubject.asObservable();

  constructor(private http: HttpClient) { }

  loadGlobalConfig(): void {
    this.http.get<GlobalConfig>(`${this.apiUrl}/global`).subscribe({
        next: (config) => {
            this.configSubject.next(config);
            this.applyTheme(config);
        },
        error: (err) => console.error('Failed to load config', err)
    });
  }

  private applyTheme(config: GlobalConfig) {
    if (config.primaryColor) {
      document.documentElement.style.setProperty('--primary-color', config.primaryColor);
    }
    // More logic to apply theme colors to PrimeNG if needed
    if (config.appName) {
        document.title = config.appName;
    }
  }

  getGlobalConfig(): Observable<GlobalConfig> {
    return this.http.get<GlobalConfig>(`${this.apiUrl}/global`);
  }
}
