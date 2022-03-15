<template>
    <div class="regist">
        <h1 class="underline">SSAFY 도서 정보</h1>
        <view-detail
            :index ="this.$route.query.index"
            :isbn ="book.isbn"
            :title="book.title"
            :author="book.author"
            :price="book.price"
            :content="book.content"
        />
    </div>
</template>

<script>
import ViewDetail from './include/ViewDetail.vue';

export default{
    components: { ViewDetail },
    name: "book-detail",
    data: function(){
        return{
            book: {},
        }
    },
    created() {
        let index = this.$route.query.index;
        this.book =this.$store.state.books[index];
    },
}
</script>
