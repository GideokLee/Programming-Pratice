<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h2>
          <mark class="sky">SSAFY 글 정보</mark>
        </h2>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col style="text-align: left">
        <b-form>
          <b-form-group id="subject-group" label="글번호" label-for="no">
            <b-form-input id="no" v-model="board.no" type="text" disabled></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="제목" label-for="title">
            <b-form-input id="title" v-model="board.title" type="text" disabled></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="작성자" label-for="writer">
            <b-form-input id="writer" v-model="board.writer" type="text" disabled></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="작성일" label-for="regtime">
            <b-form-input id="regtime" v-model="board.regtime" type="text" disabled></b-form-input>
          </b-form-group>

          <b-form-group id="content-group" label="내용" label-for="content">
            <b-form-textarea id="content" v-model="board.content" rows="10" max-rows="15" disabled></b-form-textarea>
          </b-form-group>
        </b-form>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-secondary" @click="moveModifyBoard">글수정</b-button>
        <b-button variant="outline-secondary" @click="removeBoardConfirm">글삭제</b-button>
        <b-button variant="outline-secondary" @click="moveToListPage">목록</b-button>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
export default {
  created() {
    this.$store.dispatch('getBoard', this.$route.params.no);
  },
  computed: {
    board() {
      return this.$store.getters.getBoard;
    }
  },
  methods: {
    moveToListPage() {
      this.$router.push({ name: "board-list" });
    },
    moveModifyBoard() {
      this.$router.replace({ name: "board-update", params: { no: this.$route.params.no } });
    },
    removeBoardConfirm() {
      if (confirm("정말로 삭제?")) {
        let router = this.$router;
        this.$store.dispatch('removeBoard', {
          data: this.$route.params.no,
          callback: function() {
            router.push({ name: "board-list" });
          }
        });
      }
    }
  }
};
</script>

<style>
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
