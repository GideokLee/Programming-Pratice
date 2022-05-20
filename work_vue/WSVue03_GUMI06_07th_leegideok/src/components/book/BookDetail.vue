<template>
    <div class="regist">
        <h1 class="underline">SSAFY 도서 정보</h1>
        <div class="regist_form">
            <label for="isbn">도서번호</label>
            <input type="text" id="isbn" name="isbn" v-model="book.isbn" ref="isbn" disabled/><br/>
            <label for="title">도서명</label>
            <input type="text" id="title" name="title" v-model="book.title" ref="title" disabled/><br/>
            <label for="author">저자</label>
            <input type="text" id="author" name="author"  v-model="book.author" ref="author" disabled/><br/>
            <label for="price">가격</label>
            <input type="number" id="price" name="price" v-model="book.price" ref="price" disabled/><br/>
            <label for="content">설명</label>
            <br />
            <textarea id="content" name="content" v-model="book.content" ref="content" cols="35" rows="5" disabled></textarea><br/>
            <router-link :to ="`/modify?isbn=${book.isbn}`"><button>수정</button></router-link>
            <button @click="deleteList">삭제</button>
            <button @click="moveList">목록</button>
        </div>
    </div>
</template>

<script>
import http from '@/util/http-common';

export default{
    name: "book-detail",
    data: function(){
        return{
            book: {}
        }
    },
    created() {
        http.get(`/book/${this.$route.query.isbn}`).then(({data}) =>{
            this.book = data;
        })
    },
    methods: {
        moveList() {
            this.$router.push('/book');
        },
        deleteList(){
            http
                .delete(`/book/${this.$route.query.isbn}`)
                .then(({ data }) => {
                    let msg = '삭제 처리시 문제가 발생했습니다.';
                    if (data === 'success') {
                    msg = '삭제가 완료되었습니다.';
                    }
                    alert(msg);
                    this.$router.push('/book');
                })
                .catch(() => {
                    alert('삭제 처리시 에러가 발생했습니다.');
                });

            this.$router.push('/book');
        }
    } 
}
</script>
