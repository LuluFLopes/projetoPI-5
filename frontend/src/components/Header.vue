<template>
  <div class="div-central">
    <v-row>
      <div>
        <v-col cols="6" align="left">
          <img src="../assets/mednote.png">
        </v-col>
      </div>
      <div class="menu-navegacao">
        <v-col cols="6" align="right">
          <menu-navegacao/>
        </v-col>
      </div>
    </v-row>
  </div>
</template>

<script>
import {defineComponent} from "vue";
import MenuNavegacao from "@/components/MenuNavegacao";

export default defineComponent({
  name: "Header-Vue",
  components: {MenuNavegacao}
})
</script>

<style scoped>
.div-central {
}

div {
  background-color: #7ececa;
}

div img {
  margin: 0;
  display: inline-block;
  width: 150%;
}

.menu-navegacao {
  display: inline-block;
}
</style>