<template>
    <div class="regist">
        <h1 class="underline">SSAFY 도서 수정</h1>
        <div class="regist_form">
            <label for="isbn">도서번호</label>
            <input type="text" id="isbn" name="isbn" v-model="isbn" ref="isbn" readonly/><br/>
            <label for="title">도서명</label>
            <input type="text" id="title" name="title" v-model="title" ref="title"/><br/>
            <label for="author">저자</label>
            <input type="text" id="author" name="author"  v-model="author" ref="author"/><br/>
            <label for="price">가격</label>
            <input type="number" id="price" name="price" v-model="price" ref="price"/><br/>
            <label for="content">설명</label>
            <br />
            <textarea id="content" name="content" v-model="content" ref="content" cols="35" rows="5"></textarea><br/>
            <button @click="modify">수정</button>
            <button @click="moveList">목록</button>
        </div>
    </div>
</template>

<script>
import http from '@/util/http-common';

export default {
    data: function() {
        return {
          isbn: '',
          title: '',
          author: '',
          price: '',
          content: ''
        };
    },
    created() {
        http
        .get(`/book/${this.$route.query.isbn}`)
        .then(({ data }) => {
            this.isbn = data.isbn;
            this.title = data.title;
            this.author = data.author;
            this.price = data.price;
            this.content = data.content;
        })
        .catch(() => {
          alert('에러가 발생했습니다.');
        });
    },
    methods :{
        modify() {
             http
                .put(`/book/${this.isbn}`, {
                    isbn : this.isbn,
                    title : this.title,
                    author: this.author,
                    price: this.price,
                    content: this.content
                })
                .then(({ data }) => {
                let msg = '수정 처리시 문제가 발생했습니다.';
                if (data === 'success') {
                    msg = '수정이 완료되었습니다.';
                }
                alert(msg);
                this.moveList();
                })
                .catch(() => {
                alert('수정 처리시 에러가 발생했습니다.');
                });
        },
        moveList() {
            this.$router.push('/book');
        },
    }
};
</script>

