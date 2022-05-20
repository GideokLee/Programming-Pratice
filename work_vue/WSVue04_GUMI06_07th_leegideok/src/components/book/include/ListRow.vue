<template>
    <tr>
        <td>{{ index + 1 }}</td>
        <td>{{ isbn }}</td>
        <td><router-link :to ="`/detail?index=${index}`">{{ title }}</router-link></td>
        <td>{{ author }}</td>
        <td>{{ price }}</td>
    </tr>
</template>

<script>

export default {
    name: 'list-row',
    props:{
        index: {type: Number},
        isbn: {type: String},
        title : {type: String},
        author: {type: String},
        price: {type: Number}
    }
}
</script>
