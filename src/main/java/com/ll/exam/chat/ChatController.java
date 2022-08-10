package com.ll.exam.chat;

import com.ll.exam.Rq;

public class ChatController {
    public void createRoom(Rq rq) {
        rq.view("usr/chat/createRoom");
    }
}
