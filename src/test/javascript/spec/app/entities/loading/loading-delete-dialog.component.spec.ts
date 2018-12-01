/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FoodTestModule } from '../../../test.module';
import { LoadingDeleteDialogComponent } from 'app/entities/loading/loading-delete-dialog.component';
import { LoadingService } from 'app/entities/loading/loading.service';

describe('Component Tests', () => {
    describe('Loading Management Delete Component', () => {
        let comp: LoadingDeleteDialogComponent;
        let fixture: ComponentFixture<LoadingDeleteDialogComponent>;
        let service: LoadingService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [LoadingDeleteDialogComponent]
            })
                .overrideTemplate(LoadingDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LoadingDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(LoadingService);
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
