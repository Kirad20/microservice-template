import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { ConfigService } from '../../config.service';

@Component({
  selector: 'app-config',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, InputTextModule, ButtonModule],
  templateUrl: './config.html',
  styles: [`
    .card {
        padding: 2rem;
        background: #fff;
        border-radius: 6px;
        box-shadow: 0 2px 1px -1px rgba(0,0,0,0.2), 0 1px 1px 0 rgba(0,0,0,0.14), 0 1px 3px 0 rgba(0,0,0,0.12);
    }
  `]
})
export class ConfigComponent implements OnInit {
  configForm: FormGroup;
  loading = false;

  constructor(private fb: FormBuilder, private configService: ConfigService) {
    this.configForm = this.fb.group({
      appName: [''],
      logoUrl: [''],
      primaryColor: [''],
      secondaryColor: [''],
      timezone: ['']
    });
  }

  ngOnInit() {
    this.configService.getGlobalConfig().subscribe(config => {
        this.configForm.patchValue(config);
    });
  }

  onSubmit() {
    // Implement update logic in ConfigService and call it here
    console.log('Update config:', this.configForm.value);
    // this.configService.updateGlobalConfig(this.configForm.value).subscribe(...)
  }
}
