<template>
    <div class="regist_form">
        <label for="isbn">도서번호</label>
        <input type="text" id="isbn" name="isbn" :placeholder ="isbn" ref="isbn" disabled/><br/>
        <label for="title">도서명</label>
        <input type="text" id="title" name="title" :placeholder ="title" ref="title" disabled/><br/>
        <label for="author">저자</label>
        <input type="text" id="author" name="author"  :placeholder ="author" ref="author" disabled/><br/>
        <label for="price">가격</label>
        <input type="number" id="price" name="price" :placeholder ="price" ref="price" disabled/><br/>
        <label for="content">설명</label>
        <br />
        <textarea id="content" name="content" :placeholder ="content" cols="35" rows="5" disabled></textarea><br/>
        <router-link :to ="`/modify?index=${index}`"><button>수정</button></router-link>
        <button @click="deleteList">삭제</button>
        <button @click="moveList">목록</button>
    </div>
</template>

<script>
export default {
    name: 'view-detail',
    props:{
        index: {type: String},
        isbn: {type: String},
        title : {type: String},
        author: {type: String},
        price: {type: Number},
        content:{type: String}
    },
    methods: {
        moveList() {
            this.$router.push('/book');
        },
        deleteList(){
            this.$store.dispatch('deleteBook', this.isbn);
            
            this.$store.dispatch('getBookList');

            this.moveList();
        }
    } 
}
</script>

