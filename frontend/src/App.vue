<template>
  <v-app>
    <Header/>
    <v-main>
      <router-view/>
    </v-main>
    <Footer/>
  </v-app>
</template>

<script>
import Header from "@/components/Header";
import Footer from "@/components/Footer";

export default {
  name: 'App',
  components: {
    Header,
    Footer
  }
}
</script>

<style>

main{
  width: 100%;
  background: #c7ede8;
}

body {
  overflow: hidden;
}

</style>
