import { Injectable } from '@angular/core';
import { ChatMessageDTO } from '../models/ChatMessageDTO';

@Injectable({
  providedIn: 'root'
})
export class WebSocketService {

  webSocket: WebSocket;
  chatMessages: ChatMessageDTO[] = [];

  constructor() { }

  public openWebSocket(){
    this.webSocket = new WebSocket("ws://localhost:8080/chat");
    this.webSocket.onopen = (event) => {
      console.log('Open: ',event);
    };
    this.webSocket.onmessage = (event) => {
      const chatmessageDTO = JSON.parse(event.data);
      this.chatMessages.push(chatmessageDTO);
    };
    this.webSocket.onclose = (event) => {
      console.log('Close: ',event);
    };
  }

  public sendMessage(chatmessageDTO: ChatMessageDTO){
    this.webSocket.send(JSON.stringify(chatmessageDTO));
  }

  public closeWebSocket(){
    this.webSocket.close();
  }
}
