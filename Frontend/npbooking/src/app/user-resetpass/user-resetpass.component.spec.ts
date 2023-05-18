import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserResetpassComponent } from './user-resetpass.component';

describe('UserResetpassComponent', () => {
  let component: UserResetpassComponent;
  let fixture: ComponentFixture<UserResetpassComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserResetpassComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserResetpassComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
