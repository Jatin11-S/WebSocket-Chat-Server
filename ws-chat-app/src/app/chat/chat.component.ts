import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgForm } from '@angular/forms';
import { WebSocketService } from '../service/web-socket.service';
import { ChatMessageDTO } from '../models/ChatMessageDTO';

@Component({
  selector: 'cf-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit,OnDestroy {

  constructor(public webSocketService: WebSocketService) { }

  ngOnInit(): void {
    this.webSocketService.openWebSocket();
  }

  ngOnDestroy(): void{
    this.webSocketService.closeWebSocket();
  }

  sendMessage(sendForm: NgForm){
    const chatmessageDTO = new ChatMessageDTO(sendForm.value.user,sendForm.value.message);
    this.webSocketService.sendMessage(chatmessageDTO);
    sendForm.controls.message.reset();
  }

}
