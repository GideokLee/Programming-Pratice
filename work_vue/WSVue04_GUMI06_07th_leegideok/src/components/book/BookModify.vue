<template>
    <div class="regist">
        <h1 class="underline">SSAFY 도서 수정</h1>
        <modify-form type="modify"/>
    </div>
</template>

<script>
import ModifyForm from './include/WriteForm.vue';
export default {
    name: 'book-modify',
    components: {
        ModifyForm
    },
};
</script>

