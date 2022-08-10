<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>

<!-- 보통 구획은 섹션으로 나눈다. -->
<section>
    <!-- container(max-width) : 너비가 너무 넓게 퍼지는 것을 막는다 + 반응형은 잃지 않는다. -->
    <!-- mx-auto : margin-left:auto; margin-right:auto; -->
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">게시물 리스트</h1>

        <ul class="mt-5">
            <c:forEach items="${articles}" var="article">
            <li class="flex">
                <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/detail/free/${article.id}">${article.id}</a>
                <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/${article.id}">${article.title}</a>

                <a onclick="if ( confirm('정말로 삭제하시겠습니까?') ) $(this).next().get(0).submit();" class="hover:underline hover:text-[red] mr-2">삭제</a>
                <form hidden method="POST" action="/usr/article/delete/free/${article.id}"></form>
                <a class="hover:underline hover:text-[red]" href="/usr/article/modify/free/${article.id}">수정</a>

                </li>
            </c:forEach>
        </ul>
    </div>
</section>

<section class="mt-5">
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">테스트</h1>
        <script>
            function Article__loadLatest() {
                fetch('/usr/article/getArticles/free')
                    .then(data => data.json()) // JSON을 파싱
                    .then(responseData => {
                        console.log(responseData);

                        // jquery 찾기 : $
                        $('.place-1').append(responseData.resultCode + "<br />");
                    });
            }
        </script>
        <button onclick="Article__loadLatest();" class="btn btn-xs">최신글 가져오기</button>

        <div class="place-1 border-2 border-[red] min-h-[500px]">

        </div>
    </div>
</section>


<%@ include file="../common/foot.jspf"%>