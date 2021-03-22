webpackJsonp(["main"],{

/***/ "../../../../../src/$$_lazy_route_resource lazy recursive":
/***/ (function(module, exports) {

function webpackEmptyAsyncContext(req) {
	// Here Promise.resolve().then() is used instead of new Promise() to prevent
	// uncatched exception popping up in devtools
	return Promise.resolve().then(function() {
		throw new Error("Cannot find module '" + req + "'.");
	});
}
webpackEmptyAsyncContext.keys = function() { return []; };
webpackEmptyAsyncContext.resolve = webpackEmptyAsyncContext;
module.exports = webpackEmptyAsyncContext;
webpackEmptyAsyncContext.id = "../../../../../src/$$_lazy_route_resource lazy recursive";

/***/ }),

/***/ "../../../../../src/app/app.component.css":
/***/ (function(module, exports, __webpack_require__) {

exports = module.exports = __webpack_require__("../../../../css-loader/lib/css-base.js")(false);
// imports


// module
exports.push([module.i, "", ""]);

// exports


/*** EXPORTS FROM exports-loader ***/
module.exports = module.exports.toString();

/***/ }),

/***/ "../../../../../src/app/app.component.html":
/***/ (function(module, exports) {

module.exports = "<!--The content below is only a placeholder and can be replaced.-->\r\n\r\n<div class=\"container\">\r\n  <div style=\"text-align:center\">\r\n    <h1>\r\n      Welcome to My todos !\r\n    </h1>\r\n    <img width=\"100px\" alt=\"Angular Logo\" src=\"data:image/svg+xml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNTAgMjUwIj4KICAgIDxwYXRoIGZpbGw9IiNERDAwMzEiIGQ9Ik0xMjUgMzBMMzEuOSA2My4ybDE0LjIgMTIzLjFMMTI1IDIzMGw3OC45LTQzLjcgMTQuMi0xMjMuMXoiIC8+CiAgICA8cGF0aCBmaWxsPSIjQzMwMDJGIiBkPSJNMTI1IDMwdjIyLjItLjFWMjMwbDc4LjktNDMuNyAxNC4yLTEyMy4xTDEyNSAzMHoiIC8+CiAgICA8cGF0aCAgZmlsbD0iI0ZGRkZGRiIgZD0iTTEyNSA1Mi4xTDY2LjggMTgyLjZoMjEuN2wxMS43LTI5LjJoNDkuNGwxMS43IDI5LjJIMTgzTDEyNSA1Mi4xem0xNyA4My4zaC0zNGwxNy00MC45IDE3IDQwLjl6IiAvPgogIDwvc3ZnPg==\">\r\n  </div>\r\n\r\n  <div class=\"alert alert-danger\" role=\"alert\" *ngIf=\"globalErrorService.lastError\"> \r\n    <button type=\"button\" class=\"close\" data-dismiss=\"alert\" aria-label=\"Close\" (click)=\"globalErrorService.clear()\">\r\n      <span aria-hidden=\"true\">&times;</span>\r\n    </button>\r\n    {{globalErrorService.lastError}}\r\n  </div>\r\n\r\n  <h2>Login :</h2>\r\n  <div *ngIf=\"userService.session\">\r\n    Hello <span class=\"text-primary font-weight-bold\">{{userService.session.login}}</span>\r\n    <button style=\"min-width: 100px;\" class=\"btn btn-danger ml-2\" (click)=\"logout()\">logout</button>\r\n  </div>\r\n  <form class=\"form-inline\" *ngIf=\"!userService.session\" (submit)=\"submitLogin()\">\r\n    <input class=\"form-control mr-2\" type=\"text\" placeholder=\"login\" [(ngModel)]=\"login\" id=\"login\" name=\"login\">\r\n    <input class=\"form-control mr-2\" type=\"password\" placeholder=\"password\" [(ngModel)]=\"password\" id=\"password\" name=\"password\">\r\n    <input type=\"submit\" style=\"min-width: 100px;\" class=\"btn btn-primary\" value=\"Login\">\r\n  </form>\r\n\r\n  <h2 class=\"mt-4\">{{(!todo.id) ? 'Add' : 'Update'}} a todo :</h2>\r\n  <form class=\"form-inline\" (submit)=\"submitCreateOrUpdate()\">\r\n    <input class=\"form-control mr-2\" type=\"text\" placeholder=\"name\" [(ngModel)]=\"todo.name\" name=\"todoName\">\r\n    <input type=\"submit\" style=\"min-width: 100px;\" class=\"btn btn-primary mr-2\" [value]=\"(!todo.id) ? 'Add' : 'Update'\">\r\n    <input type=\"button\" style=\"min-width: 100px;\" class=\"btn btn-danger\" value=\"Cancel\" (click)=\"cancel()\">\r\n  </form>\r\n\r\n  <h2 class=\"mt-4\">My todos:</h2>\r\n  <div class=\"clearfix mb-2\">\r\n    <div class=\"float-right\">\r\n        <button class=\"btn btn-secondary\" (click)=\"refresh()\">Refresh</button>\r\n        <button class=\"btn btn-danger\" (click)=\"deleteAll()\">Delete all</button>\r\n    </div>\r\n  </div>\r\n  <div *ngIf=\"!todos?.length\" class=\"text-muted\">\r\n    No todo...\r\n  </div>\r\n  <ul class=\"list-group\">\r\n    <li *ngFor=\"let todo of todos\" class=\"list-group-item\">\r\n      <div style=\"display:inline-block;width:20px\">{{todo.id}}</div>\r\n      <span class=\"text-primary font-weight-bold\">{{todo.name}}</span>\r\n      <span *ngIf=\"todo.user\" class=\"ml-2 text-muted\">({{todo.user}})</span>\r\n      <div style=\"margin-left: auto;\">\r\n      <button class=\"btn btn-success mr-2\" (click)=\"complete(todo)\">Done</button>\r\n        <button class=\"btn btn-secondary mr-2\" (click)=\"update(todo)\">Update</button>\r\n        <button class=\"btn btn-danger\" (click)=\"delete(todo)\">X</button>\r\n      </div>\r\n    </li>\r\n  </ul>\r\n\r\n</div>"

/***/ }),

/***/ "../../../../../src/app/app.component.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppComponent; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_todo_service__ = __webpack_require__("../../../../../src/app/services/todo.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__ = __webpack_require__("../../../../../src/app/models/rest-api.model.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_lodash__ = __webpack_require__("../../../../lodash/lodash.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3_lodash___default = __webpack_require__.n(__WEBPACK_IMPORTED_MODULE_3_lodash__);
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__services_global_error_service__ = __webpack_require__("../../../../../src/app/services/global-error.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__services_user_service__ = __webpack_require__("../../../../../src/app/services/user.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};






var AppComponent = (function () {
    function AppComponent(todoService, globalErrorService, userService) {
        this.todoService = todoService;
        this.globalErrorService = globalErrorService;
        this.userService = userService;
        this.todos = [];
        this.todo = new __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__["a" /* TodoDto */]();
    }
    AppComponent.prototype.ngOnInit = function () {
        this.refresh();
    };
    // LOGIN
    AppComponent.prototype.submitLogin = function () {
        var _this = this;
        this.userService.login(this.login, this.password).subscribe(function () {
            _this.login = '';
            _this.password = '';
        });
    };
    AppComponent.prototype.logout = function () {
        this.userService.logout();
    };
    // TODOS
    AppComponent.prototype.refresh = function () {
        var _this = this;
        this.todoService.findAll().subscribe(function (todos) { return _this.todos = todos; });
    };
    AppComponent.prototype.cancel = function () {
        this.todo = new __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__["a" /* TodoDto */]();
    };
    AppComponent.prototype.submitCreateOrUpdate = function () {
        var _this = this;
        if (!this.todo.id) {
            // CREATE
            this.todoService.create(this.todo).subscribe(function () {
                _this.todo = new __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__["a" /* TodoDto */]();
                _this.refresh();
            });
        }
        else {
            // UPDATE
            this.todoService.update(this.todo).subscribe(function () {
                _this.todo = new __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__["a" /* TodoDto */]();
                _this.refresh();
            });
        }
    };
    AppComponent.prototype.complete = function (todo) {
        var _this = this;
        this.todoService.complete(todo).subscribe(function () {
            _this.todo = new __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__["a" /* TodoDto */]();
            _this.refresh();
        });
    };
    AppComponent.prototype.update = function (todo) {
        this.todo = __WEBPACK_IMPORTED_MODULE_3_lodash__["cloneDeep"](todo);
    };
    AppComponent.prototype.delete = function (todo) {
        var _this = this;
        this.todoService.delete(todo).subscribe(function () {
            _this.todo = new __WEBPACK_IMPORTED_MODULE_2__models_rest_api_model__["a" /* TodoDto */]();
            _this.refresh();
        });
    };
    AppComponent.prototype.deleteAll = function () {
        var _this = this;
        this.todoService.deleteAll().subscribe(function (todos) { return _this.todos = todos; });
    };
    AppComponent = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["m" /* Component */])({
            selector: 'app-root',
            template: __webpack_require__("../../../../../src/app/app.component.html"),
            styles: [__webpack_require__("../../../../../src/app/app.component.css")]
        }),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_todo_service__["a" /* TodoService */], __WEBPACK_IMPORTED_MODULE_4__services_global_error_service__["a" /* GlobalErrorService */], __WEBPACK_IMPORTED_MODULE_5__services_user_service__["a" /* UserService */]])
    ], AppComponent);
    return AppComponent;
}());



/***/ }),

/***/ "../../../../../src/app/app.module.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return AppModule; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__ = __webpack_require__("../../../platform-browser/esm5/platform-browser.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_component__ = __webpack_require__("../../../../../src/app/app.component.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__services_todo_service__ = __webpack_require__("../../../../../src/app/services/todo.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_4__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_5__angular_forms__ = __webpack_require__("../../../forms/esm5/forms.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_6__handlers_global_error_handler__ = __webpack_require__("../../../../../src/app/handlers/global-error.handler.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_7__services_global_error_service__ = __webpack_require__("../../../../../src/app/services/global-error.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_8__services_user_service__ = __webpack_require__("../../../../../src/app/services/user.service.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_9__angular_common__ = __webpack_require__("../../../common/esm5/common.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};










var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_1__angular_core__["E" /* NgModule */])({
            declarations: [
                __WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* AppComponent */]
            ],
            imports: [
                __WEBPACK_IMPORTED_MODULE_0__angular_platform_browser__["a" /* BrowserModule */],
                __WEBPACK_IMPORTED_MODULE_4__angular_common_http__["b" /* HttpClientModule */],
                __WEBPACK_IMPORTED_MODULE_5__angular_forms__["a" /* FormsModule */]
            ],
            providers: [
                __WEBPACK_IMPORTED_MODULE_3__services_todo_service__["a" /* TodoService */],
                __WEBPACK_IMPORTED_MODULE_7__services_global_error_service__["a" /* GlobalErrorService */],
                __WEBPACK_IMPORTED_MODULE_8__services_user_service__["a" /* UserService */],
                {
                    provide: __WEBPACK_IMPORTED_MODULE_1__angular_core__["s" /* ErrorHandler */],
                    useClass: __WEBPACK_IMPORTED_MODULE_6__handlers_global_error_handler__["a" /* GlobalErrorHandler */]
                },
                __WEBPACK_IMPORTED_MODULE_9__angular_common__["c" /* DatePipe */]
            ],
            bootstrap: [__WEBPACK_IMPORTED_MODULE_2__app_component__["a" /* AppComponent */]]
        })
    ], AppModule);
    return AppModule;
}());



/***/ }),

/***/ "../../../../../src/app/handlers/global-error.handler.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GlobalErrorHandler; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__services_global_error_service__ = __webpack_require__("../../../../../src/app/services/global-error.service.ts");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var GlobalErrorHandler = (function () {
    function GlobalErrorHandler(globalErrorService) {
        this.globalErrorService = globalErrorService;
    }
    GlobalErrorHandler.prototype.handleError = function (error) {
        this.globalErrorService.handleError(error);
    };
    GlobalErrorHandler = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["w" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__services_global_error_service__["a" /* GlobalErrorService */]])
    ], GlobalErrorHandler);
    return GlobalErrorHandler;
}());



/***/ }),

/***/ "../../../../../src/app/models/rest-api.model.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* unused harmony export SessionDto */
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TodoDto; });
// Generated using typescript-generator version 2.0-SNAPSHOT on 2018-01-25 08:36:57.
var SessionDto = (function () {
    function SessionDto() {
    }
    return SessionDto;
}());

var TodoDto = (function () {
    function TodoDto() {
    }
    return TodoDto;
}());



/***/ }),

/***/ "../../../../../src/app/services/global-error.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return GlobalErrorService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common__ = __webpack_require__("../../../common/esm5/common.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};


var GlobalErrorService = (function () {
    function GlobalErrorService(injector, datePipe) {
        var _this = this;
        this.datePipe = datePipe;
        setTimeout(function () { return _this.appRef = injector.get(__WEBPACK_IMPORTED_MODULE_0__angular_core__["f" /* ApplicationRef */]); });
    }
    GlobalErrorService.prototype.handleError = function (error) {
        var _this = this;
        this.lastError = this.datePipe.transform(new Date(), 'HH:mm:ss.SSS') + " : "
            + (error.message ? error.message : error.toString());
        this.stopClearErrorTimer();
        this.clearErrorTimer = setTimeout(function () {
            _this.lastError = null;
            _this.appRef.tick();
        }, 10000);
        this.appRef.tick();
    };
    GlobalErrorService.prototype.clear = function () {
        this.stopClearErrorTimer();
        this.lastError = null;
        this.appRef.tick();
    };
    GlobalErrorService.prototype.stopClearErrorTimer = function () {
        if (this.clearErrorTimer) {
            clearTimeout(this.clearErrorTimer);
            this.clearErrorTimer = null;
        }
    };
    GlobalErrorService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["w" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_0__angular_core__["y" /* Injector */], __WEBPACK_IMPORTED_MODULE_1__angular_common__["c" /* DatePipe */]])
    ], GlobalErrorService);
    return GlobalErrorService;
}());



/***/ }),

/***/ "../../../../../src/app/services/todo.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return TodoService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_share__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/share.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var TodoService = (function () {
    function TodoService(httpClient) {
        this.httpClient = httpClient;
        this.serviceBaseURL = '/api/todos';
    }
    TodoService.prototype.findAll = function () {
        var url = this.serviceBaseURL + "/";
        var observable = this.httpClient.get(url).share();
        observable.subscribe();
        return observable;
    };
    TodoService.prototype.create = function (todoDto) {
        var url = this.serviceBaseURL + "/";
        var observable = this.httpClient.post(url, todoDto).share();
        observable.subscribe();
        return observable;
    };
    TodoService.prototype.update = function (todoDto) {
        var url = this.serviceBaseURL + "/" + todoDto.id;
        var observable = this.httpClient.put(url, todoDto).share();
        observable.subscribe();
        return observable;
    };
    TodoService.prototype.complete = function (todoDto) {
        console.error('complete2');
        var url = this.serviceBaseURL + "/" + todoDto.id + "/complete";
        var params = new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["c" /* HttpParams */]();
        params = params.append('version', '' + todoDto.version);
        var observable = this.httpClient.post(url, null, { params: params }).share();
        observable.subscribe();
        return observable;
    };
    TodoService.prototype.delete = function (todoDto) {
        var url = this.serviceBaseURL + "/" + todoDto.id;
        var params = new __WEBPACK_IMPORTED_MODULE_1__angular_common_http__["c" /* HttpParams */]();
        params = params.append('version', '' + todoDto.version);
        var observable = this.httpClient.delete(url, { params: params }).share();
        observable.subscribe();
        return observable;
    };
    TodoService.prototype.deleteAll = function () {
        var url = this.serviceBaseURL + "/";
        var observable = this.httpClient.delete(url).share();
        observable.subscribe();
        return observable;
    };
    TodoService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["w" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], TodoService);
    return TodoService;
}());



/***/ }),

/***/ "../../../../../src/app/services/user.service.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return UserService; });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_common_http__ = __webpack_require__("../../../common/esm5/http.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2_rxjs_add_operator_share__ = __webpack_require__("../../../../rxjs/_esm5/add/operator/share.js");
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};



var UserService = (function () {
    function UserService(httpClient) {
        this.httpClient = httpClient;
        this.serviceBaseURL = '/api/users';
    }
    UserService.prototype.login = function (login, password) {
        var _this = this;
        var url = '/login';
        var options = {
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        };
        var body = 'username=' + login + '&password=' + password;
        var observable = this.httpClient.post(url, body, options).share();
        observable.subscribe(function (sessionDto) {
            _this.session = sessionDto;
        });
        return observable;
    };
    UserService.prototype.logout = function () {
        var _this = this;
        var url = '/logout';
        var observable = this.httpClient.post(url, {}, { responseType: 'text' }).share();
        observable.subscribe(function () {
            _this.session = null;
        });
        return observable;
    };
    UserService = __decorate([
        Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["w" /* Injectable */])(),
        __metadata("design:paramtypes", [__WEBPACK_IMPORTED_MODULE_1__angular_common_http__["a" /* HttpClient */]])
    ], UserService);
    return UserService;
}());



/***/ }),

/***/ "../../../../../src/environments/environment.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
/* harmony export (binding) */ __webpack_require__.d(__webpack_exports__, "a", function() { return environment; });
// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.
var environment = {
    production: false
};


/***/ }),

/***/ "../../../../../src/main.ts":
/***/ (function(module, __webpack_exports__, __webpack_require__) {

"use strict";
Object.defineProperty(__webpack_exports__, "__esModule", { value: true });
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_0__angular_core__ = __webpack_require__("../../../core/esm5/core.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__ = __webpack_require__("../../../platform-browser-dynamic/esm5/platform-browser-dynamic.js");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_2__app_app_module__ = __webpack_require__("../../../../../src/app/app.module.ts");
/* harmony import */ var __WEBPACK_IMPORTED_MODULE_3__environments_environment__ = __webpack_require__("../../../../../src/environments/environment.ts");




if (__WEBPACK_IMPORTED_MODULE_3__environments_environment__["a" /* environment */].production) {
    Object(__WEBPACK_IMPORTED_MODULE_0__angular_core__["_7" /* enableProdMode */])();
}
Object(__WEBPACK_IMPORTED_MODULE_1__angular_platform_browser_dynamic__["a" /* platformBrowserDynamic */])().bootstrapModule(__WEBPACK_IMPORTED_MODULE_2__app_app_module__["a" /* AppModule */])
    .catch(function (err) { return console.log(err); });


/***/ }),

/***/ 0:
/***/ (function(module, exports, __webpack_require__) {

module.exports = __webpack_require__("../../../../../src/main.ts");


/***/ })

},[0]);
//# sourceMappingURL=main.bundle.js.map