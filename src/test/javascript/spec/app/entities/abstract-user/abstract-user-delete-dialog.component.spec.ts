/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { FoodTestModule } from '../../../test.module';
import { AbstractUserDeleteDialogComponent } from 'app/entities/abstract-user/abstract-user-delete-dialog.component';
import { AbstractUserService } from 'app/entities/abstract-user/abstract-user.service';

describe('Component Tests', () => {
    describe('AbstractUser Management Delete Component', () => {
        let comp: AbstractUserDeleteDialogComponent;
        let fixture: ComponentFixture<AbstractUserDeleteDialogComponent>;
        let service: AbstractUserService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [FoodTestModule],
                declarations: [AbstractUserDeleteDialogComponent]
            })
                .overrideTemplate(AbstractUserDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(AbstractUserDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AbstractUserService);
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
