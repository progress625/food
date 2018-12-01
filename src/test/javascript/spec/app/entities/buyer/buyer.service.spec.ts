/* tslint:disable max-line-length */
import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { of } from 'rxjs';
import { take, map } from 'rxjs/operators';
import { BuyerService } from 'app/entities/buyer/buyer.service';
import { IBuyer, Buyer, Membership, Status } from 'app/shared/model/buyer.model';

describe('Service Tests', () => {
    describe('Buyer Service', () => {
        let injector: TestBed;
        let service: BuyerService;
        let httpMock: HttpTestingController;
        let elemDefault: IBuyer;
        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [HttpClientTestingModule]
            });
            injector = getTestBed();
            service = injector.get(BuyerService);
            httpMock = injector.get(HttpTestingController);

            elemDefault = new Buyer('ID', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 0, 0, 0, 0, 'AAAAAAA', Membership.NORMAL, Status.DISABLE);
        });

        describe('Service methods', async () => {
            it('should find an element', async () => {
                const returnedFromService = Object.assign({}, elemDefault);
                service
                    .find('123')
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: elemDefault }));

                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should create a Buyer', async () => {
                const returnedFromService = Object.assign(
                    {
                        id: 'ID'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .create(new Buyer(null))
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'POST' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should update a Buyer', async () => {
                const returnedFromService = Object.assign(
                    {
                        companyNumber: 'BBBBBB',
                        companyName: 'BBBBBB',
                        businessLicense: 'BBBBBB',
                        containerAmountEstimated: 1,
                        credibilityLevel: 1,
                        guaranteeAmount: 1,
                        prepaymentPercent: 1,
                        paymentMethod: 'BBBBBB',
                        membership: 'BBBBBB',
                        status: 'BBBBBB'
                    },
                    elemDefault
                );

                const expected = Object.assign({}, returnedFromService);
                service
                    .update(expected)
                    .pipe(take(1))
                    .subscribe(resp => expect(resp).toMatchObject({ body: expected }));
                const req = httpMock.expectOne({ method: 'PUT' });
                req.flush(JSON.stringify(returnedFromService));
            });

            it('should return a list of Buyer', async () => {
                const returnedFromService = Object.assign(
                    {
                        companyNumber: 'BBBBBB',
                        companyName: 'BBBBBB',
                        businessLicense: 'BBBBBB',
                        containerAmountEstimated: 1,
                        credibilityLevel: 1,
                        guaranteeAmount: 1,
                        prepaymentPercent: 1,
                        paymentMethod: 'BBBBBB',
                        membership: 'BBBBBB',
                        status: 'BBBBBB'
                    },
                    elemDefault
                );
                const expected = Object.assign({}, returnedFromService);
                service
                    .query(expected)
                    .pipe(
                        take(1),
                        map(resp => resp.body)
                    )
                    .subscribe(body => expect(body).toContainEqual(expected));
                const req = httpMock.expectOne({ method: 'GET' });
                req.flush(JSON.stringify([returnedFromService]));
                httpMock.verify();
            });

            it('should delete a Buyer', async () => {
                const rxPromise = service.delete('123').subscribe(resp => expect(resp.ok));

                const req = httpMock.expectOne({ method: 'DELETE' });
                req.flush({ status: 200 });
            });
        });

        afterEach(() => {
            httpMock.verify();
        });
    });
});
