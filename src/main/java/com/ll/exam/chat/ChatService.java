package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {

    private ChatRoomRepository chatRoomRepository;
    private ChatMessageRepository chatMessageRepository;

    public ChatService() {
        chatRoomRepository = new ChatRoomRepository();
    }

    public long createRoom(String title, String body) {
        return chatRoomRepository.create(title, body);
    }

    public List<ChatRoomDto> findAllRooms() {
        return chatRoomRepository.findAll();
    }

    public ChatRoomDto findRoomById(long id) {
        return chatRoomRepository.findById(id);
    }

    public void modifyRoom(long id, String title, String body) {
        chatRoomRepository.modify(id, title, body);
    }

    public void deleteRoom(long id) {
        chatRoomRepository.deleteRoom(id);
    }

    public void writeMessage(long roomId, String body) {
        chatMessageRepository.write(roomId, body);
    }
}
