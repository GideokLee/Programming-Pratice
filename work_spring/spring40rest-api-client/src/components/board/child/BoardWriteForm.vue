<template>
  <b-row class="mb-1">
    <b-col style="text-align: left">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group id="content-group" label="작성자" label-for="userid">
          <b-form-input id="userid" v-model="board.writer" type="text" required></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="제목" label-for="subject">
          <b-form-input id="subject" v-model="board.title" type="text" required></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content">
          <b-form-textarea id="content" v-model="board.content" rows="10" max-rows="15"></b-form-textarea>
        </b-form-group>

        <b-button type="submit" variant="outline-secondary" v-if="this.type === 'register'">등록</b-button>
        <b-button type="submit" variant="outline-secondary" v-else>수정</b-button>
        <b-button type="reset" variant="outline-secondary">목록</b-button>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
export default {
  name: "board-write-form",
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "register") {
      this.$store.dispatch('clearBoard');
    }
    else {
      this.$store.dispatch('getBoard', this.$route.params.no);
    }
  },
  computed: {
    board() {
      return this.$store.getters.getBoard;
    }
  },
  methods: {
    onSubmit(event) {
      event.preventDefault();
      if (this.type === "register") {
        // 추가하기
        let router = this.$router;
        this.$store.dispatch('createBoard', {
          data: this.board,
          callback: function() {
            router.push({ name: "board-list" });
          }
        });

      } else {
        // 수정하기
        let router = this.$router;
        this.$store.dispatch('modifyBoard', {
          data: this.board,
          callback: function() {
            router.push({ name: "board-list" });
          }
        });
      }
    },
    onReset(event) {
      event.preventDefault();
      this.$router.push({ name: "board-list" });
    },
  },
};
</script>

<style scoped>
</style>
