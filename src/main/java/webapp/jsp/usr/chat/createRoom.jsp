<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../common/head.jspf"%>

<script>
    function ChatRoomSave__submitForm(form) {
        form.title.value = form.title.value.trim();
        if ( form.title.value.length == 0 ) {
            alert('제목을 입력해주세요.');
            form.title.focus();
            return;
        }
        form.body.value = form.body.value.trim();
        if ( form.body.value.length == 0 ) {
            alert('내용을 입력해주세요.');
            form.body.focus();
            return;
        }
        form.submit();
    }
</script>

<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">채팅방 작성</h1>
        <form class="grid gap-2" method="POST" onsubmit="ChatRoomSave__submitForm(this); return false;">
            <div class="form-control w-full max-w-sm">
                <label class="label">
                    <span class="label-text">채팅방 이름</span>
                </label>
                <div>
                    <input name="title" type="text" maxlength="50" placeholder="이름을 입력해주세요." class="input input-bordered w-full" />
                </div>
            </div>

            <div class="form-control w-full max-w-sm">
                <label class="label">
                    <span class="label-text">채팅방 주제</span>
                </label>
                <div>
                    <input name="body" type="text" maxlength="50" placeholder="채팅방 주제를 적어주세요." class="input input-bordered w-full" />
                </div>
            </div>

            <div class="form-btns">
                <button type="submit" class="btn btn-outline btn-primary">생성</button>
                <a href="/usr/chat/roomList" class="btn btn-outline">목록</a>
            </div>
        </form>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>