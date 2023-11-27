<template>
  <div>
    <menu-lateral/>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import MenuLateral from "@/components/MenuLateral";

export default defineComponent({
  name: "HomeAdministrador",
  components: {MenuLateral}
})
</script>

<style scoped>
</style>