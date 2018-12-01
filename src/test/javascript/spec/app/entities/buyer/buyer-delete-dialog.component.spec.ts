/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FoodTestModule } from '../../../test.module';
import { BuyerDeleteDialogComponent } from 'app/entities/buyer/buyer-delete-dialog.component';
import { BuyerService } from 'app/entities/buyer/buyer.service';

describe('Component Tests', () => {
    describe('Buyer Management Delete Component', () => {
        let comp: BuyerDeleteDialogComponent;
        let fixture: ComponentFixture<BuyerDeleteDialogComponent>;
        let service: BuyerService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [BuyerDeleteDialogComponent]
            })
                .overrideTemplate(BuyerDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(BuyerDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(BuyerService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
