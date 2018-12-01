/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FoodTestModule } from '../../../test.module';
import { BuyerDetailComponent } from 'app/entities/buyer/buyer-detail.component';
import { Buyer } from 'app/shared/model/buyer.model';

describe('Component Tests', () => {
    describe('Buyer Management Detail Component', () => {
        let comp: BuyerDetailComponent;
        let fixture: ComponentFixture<BuyerDetailComponent>;
        const route = ({ data: of({ buyer: new Buyer('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [BuyerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(BuyerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BuyerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.buyer).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
