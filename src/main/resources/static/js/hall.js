function getIndex(list, id){
    for (var i = 0; i<list.length; i++ ){
        if (list[i].id === id){
            return i;
        }
    }
    return -1;
}

var hallApi = Vue.resource('/cinema-hall{/id}');

Vue.component('hall-form', {
    props: ['halls','hallAttr'],
    data: function(){
        return {
            name: '',
            id: ''
        }
    },
    watch: {
        hallAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
        }
    },
    template:
        '<div class="input-group mb-3">' +
            '<input type="text" class="form-control" placeholder="Hall name" aria-label="Hall name" v-model="name" @keyup.enter="save" aria-describedby="button-addon2"/>' +
            '<button type = "button" @click="save" class="btn btn-outline-secondary" id="button-addon2"> Save </button>' +
        '</div>',
    methods: {
       save: function(){
           const hall = {name: this.name};
           if(this.id) {
                hallApi.update({id: this.id}, hall).then(result =>
                    result.json().then(data => {
                        const index = getIndex(this.halls, data.id);
                        this.halls.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                    })
                )
            } else {
                hallApi.save({}, hall).then(result =>
                    result.json().then(data => {
                    this.halls.push(data);
                    this.name = ''
                    })
                )
            }
        }
    }
});

Vue.component('hall-row', {
    props: ['hall', 'editMethod', 'halls'],
    template:
        '<div class="container mt-3">' +
            '<i>({{ hall.id }})</i>{{ hall.name }}' +
            '<span style="position: absolute; right: 0;  ">' +
                '<button type = "button" @click="edit" class="btn btn-outline-secondary" > Edit </button>' +
                '<button type = "button" @click="del" class="btn btn-outline-secondary" > X </button>' +
            '</span>' +
        '</div>',
        methods: {
            edit: function(){
                this.editMethod(this.hall);
            },

            del: function(){
                hallApi.remove({id: this.hall.id}).then(result => {
                    if(result.ok) {
                        this.halls.splice(this.halls.indexOf(this.hall), 1)
                    }
                })

            }
        }
});

Vue.component('halls-list', {
  props: ['halls'],
  data: function(){
    return {
        hall: null
    }
  },
  template:
      '<div style="position: relative; width: 350px;">' +
          '<hall-form :halls="halls" :hallAttr="hall"/>' +
          '<hall-row v-for="hall in halls" :key="hall.id" :hall="hall" ' +
          ':editMethod="editMethod" :halls="halls"/>' +
      '</div>',
    created: function() {
      hallApi.get().then(result =>
        result.json().then(data =>
            data.forEach(hall => this.halls.push(hall))
            )
        )
    },
    methods: {
        editMethod: function(hall){
        this.hall = hall;
        }
    }
});

var app = new Vue({
  el: '#app',
  template: '<halls-list :halls="halls" />',
  data: {
    halls: []
  }
});