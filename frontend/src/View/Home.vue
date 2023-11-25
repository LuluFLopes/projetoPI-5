<template>
  <div>
    <div class="div-imagem-principal">
      <img class="imagem-principal" src="../assets/equipe-medica2.png"  alt=""/>
    </div>
  </div>
</template>

<script>
import {defineComponent} from "vue";
export default defineComponent({
  name: "Tela-Home",
})
</script>

<style scoped>
.div-imagem-principal {
  display: flex;
  justify-content: center;
}

.imagem-principal {
  position: absolute;
  opacity: 35%;
}
</style>