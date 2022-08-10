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
                    <li> \${article.id} </li>
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

            <hr>

        <button class="btn btn-sm" onclick="Articles__loadMore();">불러오기</button>
    </div>
</section>

<%@ include file="../common/foot.jspf"%>