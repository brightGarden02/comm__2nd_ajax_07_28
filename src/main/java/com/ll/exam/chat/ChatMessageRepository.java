package com.ll.exam.chat;

import com.ll.exam.chat.dto.ChatMessageDto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ChatMessageRepository {
    private static List<ChatMessageDto> datum;
    private static long lastId;

    static {
        datum = new ArrayList<>();
        lastId = 0;

        makeTestData();
    }

    private static void makeTestData() {
        IntStream.rangeClosed(1, 10).forEach(roomId -> {
            IntStream.rangeClosed(1, 10).forEach(id -> {
                String body = "메세지 %d".formatted(id);
                write(roomId, body);
            });
        });
    }

    public static long write(long roomId, String body) {
        long id = ++lastId;
        ChatMessageDto newChatMessageDto = new ChatMessageDto(id, roomId, body);

        datum.add(newChatMessageDto);

        return id;
    }

    public static ChatMessageDto findById(long id) {
        for (ChatMessageDto chatMessageDto : datum) {
            if (chatMessageDto.getId() == id) {
                return chatMessageDto;
            }
        }

        return null;
    }

    public List<ChatMessageDto> findByRoomId(long roomId) {
        return datum
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .collect(Collectors.toList());
    }

    public List<ChatMessageDto> findByRoomIdGreaterThan(long roomId, long fromId) {
        return datum
                .stream()
                .filter(chatMessageDto -> chatMessageDto.getRoomId() == roomId)
                .filter(chatMessageDto -> chatMessageDto.getId() > fromId)
                .collect(Collectors.toList());
    }

    public void deleteMessage(long id) {
        ChatMessageDto chatMessageDto = findById(id);

        if (chatMessageDto == null) {
            return;
        }

        datum.remove(chatMessageDto);
    }
}
