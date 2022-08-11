package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatMessageDto;
import com.ll.exam.chat.dto.ChatRoomDto;

import java.util.List;

public class ChatService {

    private ChatRoomRepository chatRoomRepository;
    private ChatMessageRepository chatMessageRepository;

    public ChatService() {
        chatRoomRepository = new ChatRoomRepository();
        chatMessageRepository = new ChatMessageRepository();
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

    public long writeMessage(long roomId, String body) {
        return chatMessageRepository.write(roomId, body);
    }

    public List<ChatMessageDto> findMessagesByRoomId(long id) {
        return chatMessageRepository.findByRoomId(id);
    }

    public List<ChatMessageDto> findMessagesByRoomIdGreaterThan(long roomId, long fromId) {
        return chatMessageRepository.findByRoomIdGreaterThan(roomId, fromId);
    }

    public ChatMessageDto findMessageById(long id) {
        return ChatMessageRepository.findById(id);
    }

    public void deleteMessage(long id) {
        chatMessageRepository.deleteMessage(id);
    }

    public void modifyMessage(long id, String body) {
        chatMessageRepository.modifyMessage(id, body);
    }
}
