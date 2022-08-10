package com.ll.exam.chat;

import com.ll.exam.Rq;

public class ChatController {

    private ChatService chatService;

    public ChatController() {
        chatService = new ChatService();
    }


    public void createRoom(Rq rq) {
        rq.view("usr/chat/createRoom");
    }

    public void doCreateRoom(Rq rq) {
        String title = rq.getParam("title", "");
        String body = rq.getParam("body", "");

        if(title.length() == 0) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        if(body.length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        long id = chatService.createRoom(title, body);

        rq.replace("/usr/chat/room/%d".formatted(id), "%d번 채팅방이 생성 되었습니다.".formatted(id));

    }
}
