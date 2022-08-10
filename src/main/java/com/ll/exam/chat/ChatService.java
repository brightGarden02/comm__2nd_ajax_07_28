package com.ll.exam.chat;

public class ChatService {

    private ChatRoomRepository chatRoomRepository;

    public ChatService() {
        chatRoomRepository = new ChatRoomRepository();
    }

    public long createRoom(String title, String body) {
        return chatRoomRepository.create(title, body);
    }
}
