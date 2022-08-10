<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ include file="../common/head.jspf"%>

<script>
    let Articles__lastId = 0;
    function Articles__loadMore() {
        fetch(`/usr/article/getArticles/free?fromId=${Articles__lastId}`)
            .then(data => data.json())
            .then(responseData => {
                console.log(responseData);
                for ( const key in responseData.data ) {
                    const article = responseData.data[key];
                    const html = `
                    <li class="flex">
                        <a class="w-[40px] hover:underline hover:text-[red]" href="/usr/article/detail/free/\${article.id}">\${article.id}</a>
                        <a class="flex-grow hover:underline hover:text-[red]" href="/usr/article/detail/free/\${article.id}">\${article.title}</a>
                        <a onclick="if ( !confirm('정말로 삭제하시겠습니까?') ) return false;" class="hover:underline hover:text-[red] mr-2" href="/usr/article/delete/free/\${article.id}?_method=DELETE">삭제</a>
                        <a class="hover:underline hover:text-[red]" href="/usr/article/modify/free/\${article.id}">수정</a>
                    </li>
                `;
                    $('.articles').append(html);
                }
            });
    }
</script>


<section>
    <div class="container px-3 mx-auto">
        <h1 class="font-bold text-lg">게시물 리스트(오토)</h1>

            <ul class="articles mt-5">
                <!-- 이 부분에 자바스크립트를 통해서 HTML을 채우겠습니다. -->
            </ul>

            <hr class="mt-3 mb-3">

        <button class="btn btn-sm" onclick="Articles__loadMore();">추가로 불러오기</button>
    </div>
</section>

<script>
    Articles__loadMore();
</script>

<%@ include file="../common/foot.jspf"%>