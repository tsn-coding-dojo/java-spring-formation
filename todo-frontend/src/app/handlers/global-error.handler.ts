import { Injectable, ErrorHandler } from '@angular/core';
import { GlobalErrorService } from '../services/global-error.service';

@Injectable()
export class GlobalErrorHandler implements ErrorHandler {

    constructor(private globalErrorService: GlobalErrorService) {
    }

    handleError(error: any) {
        this.globalErrorService.handleError(error);
    }
}
