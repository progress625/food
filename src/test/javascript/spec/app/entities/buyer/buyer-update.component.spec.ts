/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { FoodTestModule } from '../../../test.module';
import { BuyerUpdateComponent } from 'app/entities/buyer/buyer-update.component';
import { BuyerService } from 'app/entities/buyer/buyer.service';
import { Buyer } from 'app/shared/model/buyer.model';

describe('Component Tests', () => {
    describe('Buyer Management Update Component', () => {
        let comp: BuyerUpdateComponent;
        let fixture: ComponentFixture<BuyerUpdateComponent>;
        let service: BuyerService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [BuyerUpdateComponent]
            })
                .overrideTemplate(BuyerUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(BuyerUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyerService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Buyer('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.buyer = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Buyer();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.buyer = entity;
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
