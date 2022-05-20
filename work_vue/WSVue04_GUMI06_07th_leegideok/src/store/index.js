import Vue from 'vue'
import Vuex from 'vuex'
import http from '@/util/http-common';
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    books: []
  },
  mutations: {
    SET_BOOK_LIST(state, books){
        state.books = books;
        state.books.sort((a, b) => {
            return -(a.price - b.price);
        });
    },
  },
  
  actions: {
    getBookList(context){
        http
        .get('/book')
        .then(({data}) => {
            context.commit('SET_BOOK_LIST', data);      
        })
        .catch(() => {
            alert('에러가 발생했습니다.');
        });
    },
    deleteBook(context,index){
        http
        .delete(`/book/${index}`)
        .then(({ data }) => {
            let msg = '삭제 처리시 문제가 발생했습니다.';
            if (data === 'success') {
            msg = '삭제가 완료되었습니다.';
            }
            alert(msg);
        })
        .catch(() => {
            alert('삭제 처리시 에러가 발생했습니다.');
        });
    },
    createBook(context,book){
        http
        .post('/book', {
            isbn: book.isbn,
            title: book.title,
            author: book.author,
            price: book.price,
            content: book.content,
        })
         .then(({ data }) => {
            let msg = '등록 처리시 문제가 발생했습니다.';
            if (data === 'success') {
                msg = '등록이 완료되었습니다.';
            }
            alert(msg);
        })
        .catch(() => {
            alert('등록 처리시 에러가 발생했습니다.');
        });
    },
    modifyBook(context,book){
        http
        .put(`/book/${this.isbn}`, {
            isbn : book.isbn,
            title : book.title,
            author: book.author,
            price: book.price,
            content: book.content
        })
        .then(({ data }) => {
        let msg = '수정 처리시 문제가 발생했습니다.';
        if (data === 'success') {
            msg = '수정이 완료되었습니다.';
        }
        alert(msg);
        })
        .catch(() => {
        alert('수정 처리시 에러가 발생했습니다.');
        });
    }
  },
})
