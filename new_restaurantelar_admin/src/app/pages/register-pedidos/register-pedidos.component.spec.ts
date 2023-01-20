import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterPedidosComponent } from './register-pedidos.component';

describe('RegisterPedidosComponent', () => {
  let component: RegisterPedidosComponent;
  let fixture: ComponentFixture<RegisterPedidosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterPedidosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterPedidosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
