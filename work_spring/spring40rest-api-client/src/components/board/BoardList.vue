<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h2>
          <mark class="sky">글 목록</mark>
        </h2>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-secondary" @click="movePage('/board/write')">글 등록</b-button>
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table striped hover :items="boards" :fields="fields" @row-clicked="viewBoardDetail"></b-table>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "board-list",
  data() {
    return {
      fields: [
        { key: "no", label: "글번호", tdClass: "tdClass" },
        { key: "title", label: "제목", tdClass: "tdSubject" },
        { key: "writer", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    this.$store.dispatch('getBoards');
  },
  computed: {
    boards() {
      return this.$store.getters.getBoards;
    }
  },
  methods: {
    movePage(url) {
      this.$router.push(url);
    },
    viewBoardDetail(board) {
      this.$router.push({
        name: "board-detail",
        params: { no: board.no }
      });
    }
  },
};
</script>

<style scoped>
.tdClass {
  width: 120px;
  text-align: center;
}
.tdSubject {
  width: 480px;
  text-align: left;
}
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
