import {
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable()
export class baseUrlInterceptor implements HttpInterceptor {
  constructor(@Inject('base_api_url') private baseurl: string) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    const apireq = request.clone({ url: `${this.baseurl}/${request.url}` });
    return next.handle(apireq);
  }
}
