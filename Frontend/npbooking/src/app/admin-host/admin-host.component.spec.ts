import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminHostComponent } from './admin-host.component';

describe('AdminHostComponent', () => {
  let component: AdminHostComponent;
  let fixture: ComponentFixture<AdminHostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminHostComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminHostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
