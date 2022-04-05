<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h2>
          <mark class="sky">SSAFY 글 등록</mark>
        </h2>
      </b-col>
    </b-row>
    <board-write-form type="register" />
  </b-container>
</template>

<script>
import BoardWriteForm from "@/components/board/child/BoardWriteForm.vue";

export default {
  name: "board-write",
  components: {
    BoardWriteForm,
  },
};
</script>

<style scope>
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
