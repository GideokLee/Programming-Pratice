import Vue from 'vue'
import Vuex from 'vuex'
import http from '@/util/http-common';
import dayjs from 'dayjs';

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    boards: [],
    board: {
      no: 0,
      writer: '',
      title: '',
      content: '',
      regtime: 0
    }
  },
  getters: {
    getBoards(state) {
      return state.boards;
    },
    getBoard(state) {
      return state.board;
    }
  },
  mutations: {
    setBoards(state, boards) {
      for (let board of boards) {
        board.regtime = dayjs(board.regtime).format("YYYY.MM.DD HH:mm");
      }
      state.boards = boards;
    },
    setBoard(state, board) {
      if (board.regtime) {
        board.regtime = dayjs(board.regtime).format("YYYY.MM.DD HH:mm");
      }
      state.board = board;
    }
  },
  actions: {
    getBoards({ commit }) {
      http.get('/board')
        .then((response) => {
          commit('setBoards', response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getBoard({ commit }, no) {
      http.get(`/board/${no}`)
        .then((response) => {
          commit('setBoard', response.data);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    createBoard({ commit }, payload) {
      payload.data.regtime = '';
      http.post('/board', payload.data)
        .then((response) => {
          commit('setBoard', response.data);
          payload.callback();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    modifyBoard({ commit }, payload) {
      payload.data.regtime = '';
      http.put(`/board/${payload.data.no}`, payload.data)
        .then((response) => {
          commit('setBoard', response.data);
          payload.callback();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    removeBoard({ commit }, payload) {
      http.delete(`/board/${payload.data}`)
        .then(() => {
          commit('setBoard', {
            no: 0,
            writer: '',
            title: '',
            content: '',
            regtime: 0
          });
          payload.callback();
        })
        .catch((error) => {
          console.log(error);
        });
    },
    clearBoard({ commit }) {
      commit("setBoard", {
        no: 0,
        writer: '',
        title: '',
        content: '',
        regtime: 0
      });
    }
  },
  modules: {
  }
})
