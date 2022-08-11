package com.ll.exam.chat;

import com.ll.exam.Rq;
import com.ll.exam.chat.dto.ChatMessageDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatController {

    private ChatService chatService;

    public ChatController() {
        chatService = new ChatService();
    }


    public void showCreateRoom(Rq rq) {
        rq.view("usr/chat/createRoom");
    }

    public void createRoom(Rq rq) {
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

    public void showRoomList(Rq rq) {
        List<ChatRoomDto> chatRoomDtos = chatService.findAllRooms();

        rq.setAttr("rooms", chatRoomDtos);
        rq.view("usr/chat/roomList");
    }

    public void showModifyRoom(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, -1);

        if ( id == -1 ) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(id);

        if ( chatRoom == null ) {
            rq.historyBack("존재하지 않는 채팅방 입니다.");
            return;
        }

        rq.setAttr("room", chatRoom);
        rq.view("usr/chat/modifyRoom");
    }

    public void modifyRoom(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, -1);

        if ( id == -1 ) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        String title = rq.getParam("title", "");

        if ( title.length() == 0 ) {
            rq.historyBack("제목을 입력해주세요.");
            return;
        }

        String body = rq.getParam("body", "");

        if ( body.length() == 0 ) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(id);

        if ( chatRoom == null ) {
            rq.historyBack("존재하지 않는 채팅방 입니다.");
            return;
        }

        chatService.modifyRoom(id, title, body);

        rq.replace("/usr/chat/room/%d".formatted(id), "%d번 채팅방이 수정되었습니다.".formatted(id));

    }

    public void deleteRoom(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoomDto = chatService.findRoomById(id);

        if (chatRoomDto == null) {
            rq.historyBack("해당 채팅방이 존재하지 않습니다.");
            return;
        }

        chatService.deleteRoom(id);

        rq.replace("/usr/chat/roomList", "%d번 채팅방이 삭제되었습니다.".formatted(id));
    }


    // ajax 방식으로 채팅메세지를 리스팅
    public void showRoom(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, -1);

        if ( id == -1 ) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoomDto = chatService.findRoomById(id);

        if ( chatRoomDto == null ) {
            rq.historyBack("존재하지 않는 채팅방 입니다.");
            return;
        }

        rq.setAttr("room", chatRoomDto);

        rq.view("usr/chat/room");
    }


    public void showRoomManual(Rq rq) {

        long id = rq.getLongPathValueByIndex(0, -1);

        if (id == -1) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoomDto = chatService.findRoomById(id);
        List<ChatMessageDto> chatMessageDtos = chatService.findMessagesByRoomId(chatRoomDto.getId());

        rq.setAttr("room", chatRoomDto);
        rq.setAttr("messages", chatMessageDtos);

        rq.view("usr/chat/roomManual");
    }

    public void writeMessage(Rq rq) {
        long roomId = rq.getLongPathValueByIndex(0, -1);

        if (roomId == -1) {
            rq.historyBack("채팅방 번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(roomId);

        if (chatRoom == null) {
            rq.historyBack("존재하지 않는 채팅방 입니다.");
            return;
        }

        String body = rq.getParam("body", "");

        if (body.trim().length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        chatService.writeMessage(roomId, body);

        rq.replace("/usr/chat/room/%d".formatted(roomId), "메세지가 등록되었습니다.");
    }


    public void getMessages(Rq rq) {
        long roomId = rq.getLongPathValueByIndex(0, -1);

        if (roomId == -1) {
            rq.failJson("채팅방 번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(roomId);

        if (chatRoom == null) {
            rq.failJson("존재하지 않는 채팅방 입니다.");
            return;
        }

        long fromId = rq.getLongParam("fromId", -1);

        List<ChatMessageDto> chatMessageDtos = null;

        if ( fromId == -1 ) {
            chatMessageDtos = chatService.findMessagesByRoomId(roomId);
        }
        else {
            chatMessageDtos = chatService.findMessagesByRoomIdGreaterThan(roomId, fromId);
        }

        rq.successJson(chatMessageDtos);
    }

    public void writeMessageAjax(Rq rq) {
        long roomId = rq.getLongPathValueByIndex(0, -1);

        if (roomId == -1) {
            rq.failJson("채팅방 번호를 입력해주세요.");
            return;
        }

        ChatRoomDto chatRoom = chatService.findRoomById(roomId);

        if (chatRoom == null) {
            rq.failJson("존재하지 않는 채팅방 입니다.");
            return;
        }

        String body = rq.getParam("body", "");

        if (body.trim().length() == 0) {
            rq.historyBack("내용을 입력해주세요.");
            return;
        }

        long newChatMessageId = chatService.writeMessage(roomId, body);

        rq.successJson(newChatMessageId);
    }

    public void deleteMessage(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, 0);

        if (id == 0) {
            rq.historyBack("번호를 입력해주세요.");
            return;
        }

        ChatMessageDto chatMessageDto = chatService.findMessageById(id);

        if (chatMessageDto == null) {
            rq.historyBack("해당 메세지가 존재하지 않습니다.");
            return;
        }

        long roomId = chatMessageDto.getRoomId();

        chatService.deleteMessage(id);

        rq.replace("/usr/chat/room/%d".formatted(roomId), "%d번 메세지가 삭제되었습니다.".formatted(id));

    }

    public void deleteMessageAjax(Rq rq) {
        long id = rq.getLongPathValueByIndex(0, 0);

        if (id == 0) {
            rq.failJson("번호를 입력해주세요.");
            return;
        }

        ChatMessageDto chatMessageDto = chatService.findMessageById(id);

        if (chatMessageDto == null) {
            rq.failJson("해당 메세지가 존재하지 않습니다.");
            return;
        }

        long roomId = chatMessageDto.getRoomId();

        chatService.deleteMessage(id);

        rq.json(id, "S-1", "%d번 메세지가 삭제되었습니다.".formatted(id));
    }
}
