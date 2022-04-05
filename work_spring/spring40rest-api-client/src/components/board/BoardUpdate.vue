<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <h2>
          <mark class="sky">SSAFY 글 수정</mark>
        </h2>
      </b-col>
    </b-row>
    <board-write-form type="modify" />
  </b-container>
</template>

<script>
import BoardWriteForm from "@/components/board/child/BoardWriteForm.vue";

export default {
  name: "BoardUpdate",
  components: {
    BoardWriteForm,
  },
};
</script>

<style>
mark.sky {
  background: linear-gradient(to top, #54fff9 20%, transparent 30%);
}
</style>
