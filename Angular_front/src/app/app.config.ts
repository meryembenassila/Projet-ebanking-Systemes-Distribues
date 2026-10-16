import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideMarkdown} from 'ngx-markdown';
import {provideHttpClient, withInterceptors} from '@angular/common/http';
import {loadingInterceptorInterceptor} from './interceptors/loading-interceptor-interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),
    provideMarkdown(),//pour utilser les markdown qu'on a installer via npm
    provideHttpClient(withInterceptors([loadingInterceptorInterceptor]))//on peut ne pas fait provider pour http avec angular 21 mais si on veut utilser l'intercepteur  il faut le declarer
  ]
};
