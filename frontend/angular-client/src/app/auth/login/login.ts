import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { AuthService } from '../auth.service';
import { ConfigService, GlobalConfig } from '../../config.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CardModule,
    InputTextModule,
    ButtonModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.scss'
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  config$: Observable<GlobalConfig | null>;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private configService: ConfigService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
    this.config$ = this.configService.config$;
  }

  ngOnInit() {
    this.configService.loadGlobalConfig();
  }

  onSubmit() {
    if (this.loginForm.valid) {
        this.loading = true;
        this.authService.login(this.loginForm.value).subscribe({
            next: () => {
                this.loading = false;
                this.router.navigate(['/']); // Navigate to home
            },
            error: (err) => {
                console.error('Login failed', err);
                this.loading = false;
                // Ideally show a toast message here
            }
        });
    }
  }
}
