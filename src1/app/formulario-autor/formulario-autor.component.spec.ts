import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioAutorComponent } from './formulario-autor.component';

describe('FormularioautorComponent', () => {
  let component: FormularioAutorComponent;
  let fixture: ComponentFixture<FormularioAutorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioAutorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioAutorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
