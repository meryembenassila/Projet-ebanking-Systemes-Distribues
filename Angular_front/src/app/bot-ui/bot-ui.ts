import {Component, inject} from '@angular/core';
import {HttpClient, HttpDownloadProgressEvent, HttpEventType} from '@angular/common/http';
import {map, Observable} from 'rxjs';
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

  askAgentStream() {
    console.log("pass")
    this.response$ = this.http.get("http://localhost:8888/CHATBOT-SERVICE/chatStream?query="+this.query,{responseType :"text",observe :"events" ,reportProgress : true})
      .pipe(map(event=>{
        switch (event.type){
          case HttpEventType.Sent:
            return {'type ' : "Sent"}
          case HttpEventType.DownloadProgress:
            return  {type  : "Response" , content :(event as HttpDownloadProgressEvent).partialText}
          case HttpEventType.Response:
            return  {type  : "Response" , content :event.body}
          default :
            return  {type  : "Other" , data :event}

        }
      }))
  }

}
