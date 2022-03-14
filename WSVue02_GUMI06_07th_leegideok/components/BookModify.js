export default{
    template: `
    <div class="regist">
        <h1 class="underline">SSAFY 도서 수정</h1>
        <div class="regist_form">
            <label for="isbn">도서번호</label>
            <input type="text" id="isbn" name="isbn" v-model="book.isbn" ref="isbn" readonly/><br/>
            <label for="title">도서명</label>
            <input type="text" id="title" name="title" v-model="book.title" ref="title"/><br/>
            <label for="author">저자</label>
            <input type="text" id="author" name="author"  v-model="book.author" ref="author"/><br/>
            <label for="price">가격</label>
            <input type="number" id="price" name="price" v-model="book.price" ref="price"/><br/>
            <label for="content">설명</label>
            <br />
            <textarea id="content" name="content" v-model="book.content" ref="content" cols="35" rows="5"></textarea><br/>
            <button @click="modify">수정</button>
            <button @click="moveList">목록</button>
        </div>
    </div>
    `, data() {
        return {
            book: {},
            newbooks:{}
        }
    },  
    created() {
        const params = new URL(document.location).searchParams;

        let isbn = params.get('isbn');
        const bookList = localStorage.getItem("bookList");
        let newBook = {
            books: []
        };

        if (bookList) {
            newBook = JSON.parse(bookList);
        }
        else {
            localStorage.setItem("bookList", JSON.stringify(newBook));
        }
        this.newbooks = newBook;

        for(let i = 0; i < newBook.books.length; i++){
            if(newBook.books[i].isbn == isbn){
                this.book = newBook.books[i];
            }
        }
    },
    methods: {
        moveList() {
            location.href = "list.html";
        },
        modify(){
        
            for(let i = 0; i < this.newbooks.books.length; i++){
                if(this.newbooks.books[i].isbn == this.book.isbn){
                    this.newbooks.books[i] = this.book;
                }
            }
            localStorage.setItem("bookList", JSON.stringify(this.newbooks));
            alert("수정이 완료되었습니다.");
            location.href = "list.html";
        }
    }
};