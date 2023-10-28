<template>
<div>
    <img src="../assets/mednote.png">
</div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent ({
  name: "Header-Vue"
})
</script>

<style scoped>
  div{
    background-color: #7ececa;
  }
  div img{
    margin: 0;
    display: flex;
    width: 20%;
  }
</style>