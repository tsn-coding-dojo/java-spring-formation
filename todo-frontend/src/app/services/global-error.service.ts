import { Injectable, Injector, ApplicationRef } from '@angular/core';
import { DatePipe } from '@angular/common';

@Injectable()
export class GlobalErrorService {

    private appRef: ApplicationRef;

    lastError: string | null;
    clearErrorTimer: number | null;

    constructor(injector: Injector, private datePipe: DatePipe) {
        setTimeout(() => this.appRef = injector.get(ApplicationRef));
    }

    handleError(error: any) {
        this.lastError = `${this.datePipe.transform(new Date(), 'HH:mm:ss.SSS')} : `
            + JSON.stringify(error);
        this.stopClearErrorTimer();
        this.clearErrorTimer = <any>setTimeout(() => {
            this.lastError = null;
            this.appRef.tick();
        }, 10000);
        this.appRef.tick();
    }

    clear() {
        this.stopClearErrorTimer();
        this.lastError = null;
        this.appRef.tick();
    }

    private stopClearErrorTimer() {
        if (this.clearErrorTimer) {
            clearTimeout(this.clearErrorTimer);
            this.clearErrorTimer = null;
        }
    }

}
