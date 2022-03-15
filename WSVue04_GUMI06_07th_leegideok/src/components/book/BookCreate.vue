<template>
     <div class="regist">
        <h1 class="underline">SSAFY 도서 등록</h1>
        <write-form
          type="create"
        />
    </div>
</template>

<script>
import WriteForm from './include/WriteForm.vue';

export default{
    components: { 
      WriteForm 
    },
}
</script>
