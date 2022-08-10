<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">채팅방 리스트</h1>

        <ul class="mt-5">
            <c:forEach items="${rooms}" var="room">
                <li class="flex gap-2">
                    <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/chat/room/${room.id}">${room.id}</a>
                    <a class="hover:underline hover:text-[red] mr-auto" href="/usr/chat/room/${room.id}">
                            ${room.title}
                        #${room.body}
                    </a>
                    <a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" class="hover:underline hover:text-[red] mr-2" href="/usr/chat/delete/${room.id}?_method=DELETE">삭제</a>
                    <a class="hover:underline hover:text-[red]" href="/usr/chat/modify/${room.id}">수정</a>
                </li>
            </c:forEach>
        </ul>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>