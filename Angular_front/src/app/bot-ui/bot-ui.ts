import {Component, inject} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AsyncPipe} from '@angular/common';
import { FormsModule } from '@angular/forms';
import {MarkdownComponent} from 'ngx-markdown';
import {Loading} from '../services/loading';

@Component({
  selector: 'app-bot-ui',
  imports: [
    AsyncPipe,
    FormsModule,
    MarkdownComponent
  ],
  templateUrl: './bot-ui.html',
  styleUrl: './bot-ui.css',
})
export class BotUi {
  private http = inject(HttpClient);
  public loadingService = inject(Loading);
  query:any;
  response$!: Observable<any>;
  askAgent() {
    console.log("pass")
      this.response$ = this.http.get("http://localhost:8888/CHATBOT-SERVICE/chat?query="+this.query,{responseType :"text"})
  }

}
