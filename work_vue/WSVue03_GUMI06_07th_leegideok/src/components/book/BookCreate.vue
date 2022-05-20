<template>
     <div class="regist">
        <h1 class="underline">SSAFY 도서 등록</h1>
        <div class="regist_form">
            <label for="isbn">도서번호</label>
            <input type="text" id="isbn" name="isbn" v-model="isbn" ref="isbn" /><br/>
            <label for="title">도서명</label>
            <input type="text" id="title" name="title" v-model="title" ref="title" /><br/>
            <label for="author">저자</label>
            <input type="text" id="author" name="author" v-model="author" ref="author" /><br/>
            <label for="price">가격</label>
            <input type="number" id="price" name="price" v-model="price" ref="price" /><br/>
            <label for="content">설명</label>
            <br />
            <textarea id="content" name="content" v-model="content" ref="content" cols="35" rows="5"></textarea><br/>
            <button @click="checkValue">등록</button>
            <button @click="moveList">목록</button>
        </div>
    </div>
</template>

<script>
import http from '@/util/http-common.js';
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
      methods: {
        checkValue() {
          let err = false;
          let msg = '';

          if (!this.isbn) {
            msg = "isbn 입력해주세요 !!!";
            err = true;
            this.$refs.isbn.focus();
          }
          else if (!this.title) {
            msg = "title 입력해주세요 !!!";
            err = true;
            this.$refs.title.focus();
          }
          // TODO: 나머지 항목은 여러분이 작성하기

          if (err) {
            alert(msg);
          }
          else {
            // 입력한 도서 등록하기
            this.registBook();
          }
        },

        registBook() {
          http
            .post('/book', {
                isbn: this.isbn,
                title: this.title,
                author: this.author,
                price: this.price,
                content: this.content,
            })
             .then(({ data }) => {
                let msg = '등록 처리시 문제가 발생했습니다.';
                if (data === 'success') {
                    msg = '등록이 완료되었습니다.';
                }
                alert(msg);
                this.moveList();
            })
            .catch(() => {
                alert('등록 처리시 에러가 발생했습니다.');
            });
        },
        moveList() {
          this.$router.push('/book');
        }
      }
}
</script>
