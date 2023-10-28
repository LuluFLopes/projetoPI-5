<template>
  <footer id="footer">

  <p>MedNote &copy; 2024</p>

  <div class="imagens-footer">
    <a href="https://github.com/LuluFLopes/projetoPI-5" target="_blank"><img src="../assets/icons8-github-48.png" alt="GitHub"></a>
    <a href="https://trello.com/b/kpYeBHia/mednote-pi5" target="_blank"><img src="../assets/icons8-trello-48.png" alt="Trello"></a>
  </div>


  </footer>
</template>

<script>
export default {
  name: "Footer-Vue"
}
</script>

<style scoped>

#footer{
  position: absolute;
  bottom: 0;
  background-color: #7ececa;
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 70px;
}

p {
  text-align: center;
  justify-content: center;
  vertical-align: center;
  margin-left: 2vw;
}

.imagens-footer {
  margin: auto;
  margin-right: 0;
}

.imagens-footer img {
  padding: 10px;
}

</style>