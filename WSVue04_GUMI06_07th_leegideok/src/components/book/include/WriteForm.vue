<template>
    <div class="regist_form">
        <label for="isbn">도서번호</label>
        <input type="text" id="isbn" name="isbn" v-model ="isbn" ref="isbn"/><br/>
        <label for="title">도서명</label>
        <input type="text" id="title" name="title" v-model ="title" ref="title"/><br/>
        <label for="author">저자</label>
        <input type="text" id="author" name="author" v-model ="author" ref="author"/><br/>
        <label for="price">가격</label>
        <input type="number" id="price" name="price" v-model ="price" ref="price"/><br/>
        <label for="content">설명</label>
        <br />
        <textarea id="content" name="content" v-model ="content" ref="content" cols="35" rows="5"></textarea><br/>
        <button
            class="btn btn-primary"
            v-if="type == 'create'"
            @click="checkValue"
        >
            등록
        </button>
        <button class="btn btn-primary" v-else @click="checkValue">수정</button>
        <button @click="moveList">목록</button>
    </div>
</template>

<script>

export default {
    props:{
        type :{type : String},
    },
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
        if(this.type != "create"){
            let book =this.$store.state.books[this.$route.query.index];
            this.isbn = book.isbn;
            this.title = book.title;
            this.author = book.author;
            this.price = book.price;
            this.content = book.content;
        }
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

          if (err) {
            alert(msg);
          }
          else {
            this.type == 'create' ? this.registBook() : this.modify();
          }
        },
        registBook() {
            let book = {
                isbn: this.isbn,
                title: this.title,
                author: this.author,
                price: this.price,
                content: this.content,
            };
            this.$store.dispatch('createBook', book);
            this.moveList();
        },
        modify(){
                        let book = {
                isbn: this.isbn,
                title: this.title,
                author: this.author,
                price: this.price,
                content: this.content,
            };
            this.$store.dispatch('modifyBook', book);
            this.moveList();
        },
        moveList() {
          this.$router.push('/book');
        }
      }
}
</script>
