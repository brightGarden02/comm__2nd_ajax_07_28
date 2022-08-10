<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%@ include file="../common/head.jspf"%>

<script>
    function ArticleSave__submitForm(form) {
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
    <!-- container(max-width) : 너비가 너무 넓게 퍼지는 것을 막는다 + 반응형은 잃지 않는다. -->
    <!-- mx-auto : margin-left:auto; margin-right:auto; -->
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">게시물 작성</h1>
        <form method="POST" onsubmit="ArticleSave__submitForm(this); return false;">
            <div class="flex gap-3">
                <span>제목</span>
                <div>
                    <input name="title" type="text" maxlength="50" placeholder="제목을 입력해주세요." />
                </div>
            </div>

            <div class="flex gap-3">
                <span>내용</span>
                <div>
                    <input name="body" type="text" maxlength="300" placeholder="내용을 입력해주세요." />
                </div>
            </div>

            <div>
                <div>
                    <input class="hover:underline hover:text-[red] cursor-pointer" type="submit" value="작성" />
                </div>
            </div>
        </form>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>