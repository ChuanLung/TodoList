const app = new Vue({
  el: "#app",
  data: {
    editTodo: null,
    todoDesc: "",
    todos: [],
    needShow: true,
    shark: true,
    animate: 'animate__zoomInDown'
  },
  methods: {
    deleteTodo(id, i){
      fetch("/api/events/" + id,{
        method: "DELETE"
      })
      .then((data) => {
        this.todos.splice(i, 1);
      })
    },
    updateTodo(updateDescTodo){
      fetch("/api/events/" + updateDescTodo.id,{
        method: "PUT",
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
        body: JSON.stringify(updateDescTodo),
      })
        .then(() => {
          this.editTodo = null;
        })
    },
    addTodo(todoDesc) {
      this.todos. unshift({desc: todoDesc});
      this.$emit('added');
      this.todoDesc = "";

      fetch("/api/events", {
        body: JSON.stringify({desc: todoDesc}),
        method: "POST",
        headers: {
          "Content-Type": "application/json; charset=utf-8",
        },
      })
      .then((result) => {
        console.log(result);
      })
    },
    clickDelete(){
      document.querySelector(".animateDiv").className = "animateDiv animate__animated";
    },
  },
  mounted() {
    fetch("/api/events", {
      method: "GET"
    })
      .then(response => response.json())
      .then((data) => {
        this.todos = data;
        this.todos.reverse();
      }).catch(err => {
      console.log('錯誤：' + err)
    });
  },
  template: `
    <div class="col-xs-12"> 
       <Todo-Header/>
      <div style="text-align: center">
        <div class="input-group input-group-lg" style="padding-bottom: 10px;color: bisque">
          <input type="text" v-on:keyup.enter="addTodo(todoDesc)" v-model="todoDesc" placeholder="增加事項..." class="form-control" aria-label="Large">
        </div>
        <div class="jumbotron" style="background: aliceblue">
          <table class="table table-bordered">
            <thead>
              <tr style="text-align: center">
                <th class="bg-info text-white">待辦事項</th>              
              </tr>
            </thead>
            <tbody>                  
              <tr id="row" v-for="todo, i in todos" :class="['animateDiv', 'animate__animated', animate]" v-on:click="clickDelete()"">            
                <td> 
                  <div v-if="editTodo === todo.id">                                
                    <input class="form-control" aria-label="Large" v-on:keyup.enter="updateTodo(todo)" v-model="todo.desc" />       
                  </div>        
                  <div v-else>
                    <div class="input-group input-group-lg" style="padding-bottom: 10px">                    
                      <input type="text" class="form-control" aria-label="Large" v-model="todo.desc" readonly>                    
                    </div>
                     <div style="text-align: center">
                       <button class="btn btn-amend" v-on:click="editTodo = todo.id">修改</button> | 
                       <button class="btn btn-delete" v-on:click="deleteTodo(todo.id, i)">刪除</button>
                     </div>
                  </div>           
                </td>
              </tr>
            </tbody>        
          </table>
        </div>
      </div>
    </div>`

});

$("#row").on("click",".btn-outline-danger",function(){
  this.animate = animate === 'animate__zoomInDown' ? 'animate__slideOutRight' : 'animate__zoomInDown'
  this.clickDelete();
})







