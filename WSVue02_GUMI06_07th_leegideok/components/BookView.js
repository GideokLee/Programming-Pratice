export default {
    template: `
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
            <button @click="moveModify">수정</button>
            <button @click="deleteList">삭제</button>
            <button @click="moveList">목록</button>
        </div>
    </div>
    `,
    data() {
        let index;
        let newBook;
        return {
            book: {},
            books:[]
        }
    },
    created() {
        const params = new URL(document.location).searchParams;
        console.log(params.get('isbn'));

        let isbn = params.get('isbn');
        // 위 isbn 변수를 사용해서 책 한권 가져오기
        const bookList = localStorage.getItem("bookList");
        newBook = {
            books: []
        };

        if (bookList) {
            newBook = JSON.parse(bookList);
        }
        else {
            localStorage.setItem("bookList", JSON.stringify(newBook));
        }
        this.books = newBook.books;

        for(i = 0; i < newBook.books.length; i++){
            if(newBook.books[i].isbn == isbn){
                this.book = newBook.books[i];
                index = i;
                console.log("index" + index);
            }
        }
        
    },
    methods: {
        moveList() {
            location.href = "list.html";
        },
        moveModify() {
            location.href = "modify.html?isbn=" + this.book.isbn;
        },
        deleteList(){
            this.books.splice(index,1);
            localStorage.setItem("bookList", JSON.stringify(newBook));
            location.href = "list.html";
        }
    } 
    
};
