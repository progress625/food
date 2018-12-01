/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FoodTestModule } from '../../../test.module';
import { AbstractUserDetailComponent } from 'app/entities/abstract-user/abstract-user-detail.component';
import { AbstractUser } from 'app/shared/model/abstract-user.model';

describe('Component Tests', () => {
    describe('AbstractUser Management Detail Component', () => {
        let comp: AbstractUserDetailComponent;
        let fixture: ComponentFixture<AbstractUserDetailComponent>;
        const route = ({ data: of({ abstractUser: new AbstractUser('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [AbstractUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(AbstractUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AbstractUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.abstractUser).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
