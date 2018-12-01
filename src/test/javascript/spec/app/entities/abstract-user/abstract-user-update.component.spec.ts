/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FoodTestModule } from '../../../test.module';
import { AbstractUserUpdateComponent } from 'app/entities/abstract-user/abstract-user-update.component';
import { AbstractUserService } from 'app/entities/abstract-user/abstract-user.service';
import { AbstractUser } from 'app/shared/model/abstract-user.model';

describe('Component Tests', () => {
    describe('AbstractUser Management Update Component', () => {
        let comp: AbstractUserUpdateComponent;
        let fixture: ComponentFixture<AbstractUserUpdateComponent>;
        let service: AbstractUserService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [AbstractUserUpdateComponent]
            })
                .overrideTemplate(AbstractUserUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AbstractUserUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AbstractUserService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new AbstractUser('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.abstractUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new AbstractUser();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.abstractUser = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
