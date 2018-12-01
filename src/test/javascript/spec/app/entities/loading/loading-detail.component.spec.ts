/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FoodTestModule } from '../../../test.module';
import { LoadingDetailComponent } from 'app/entities/loading/loading-detail.component';
import { Loading } from 'app/shared/model/loading.model';

describe('Component Tests', () => {
    describe('Loading Management Detail Component', () => {
        let comp: LoadingDetailComponent;
        let fixture: ComponentFixture<LoadingDetailComponent>;
        const route = ({ data: of({ loading: new Loading('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [LoadingDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LoadingDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LoadingDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.loading).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
