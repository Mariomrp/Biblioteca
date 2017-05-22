import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioRetiradaComponent } from './formulario-retirada.component';

describe('FormularioRetiradaComponent', () => {
  let component: FormularioRetiradaComponent;
  let fixture: ComponentFixture<FormularioRetiradaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioRetiradaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioRetiradaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
