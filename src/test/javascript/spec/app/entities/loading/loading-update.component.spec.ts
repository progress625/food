/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FoodTestModule } from '../../../test.module';
import { LoadingUpdateComponent } from 'app/entities/loading/loading-update.component';
import { LoadingService } from 'app/entities/loading/loading.service';
import { Loading } from 'app/shared/model/loading.model';

describe('Component Tests', () => {
    describe('Loading Management Update Component', () => {
        let comp: LoadingUpdateComponent;
        let fixture: ComponentFixture<LoadingUpdateComponent>;
        let service: LoadingService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [LoadingUpdateComponent]
            })
                .overrideTemplate(LoadingUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(LoadingUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LoadingService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Loading('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.loading = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Loading();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.loading = entity;
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
