import { Injectable, ErrorHandler, ApplicationRef } from '@angular/core';
import { GlobalErrorService } from '../services/global-error.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

    constructor(private globalErrorService: GlobalErrorService) {
    }

    handleError(error) {
        this.globalErrorService.handleError(error);
    }
}
