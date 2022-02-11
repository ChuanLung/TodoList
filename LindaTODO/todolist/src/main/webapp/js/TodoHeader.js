Vue.component("Todo-Header",{
  props: {
    title: {
      type: String,
      default: "Linda's"
    },
    subTitle: {
      type: String,
      default: "Todo List"
    },

  data: function () {
    return {
      }
    }
  },
  template: `
  <section class="section">
    <div >
      <h1 class="title" style="color: lightseagreen">{{ title }}</h1>
      <h2 class="subtitle" style="color: lightseagreen">{{ subTitle }}</h2>
      <div style="text-align: center">
      <img src="resource/image/Workshops-Header-Background.png" style="height: 15vh;width: 100%">
      </div>
    </div>
        <br>
  </section>
  `
})