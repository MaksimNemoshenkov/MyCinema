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
        '<div>' +
            '<input type="text" placeholder="Hall name" v-model="name"/>' +
            '<input type = "button" value= "Save" @click="save"/>' +
        '</div>',
    methods: {
       save: function(){
            var hall = { name: this.name };
            if(this.id) {
                hallApi.update({id: this.id}, hall).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.halls, data.id);
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
        '<div>' +
            '<i>({{ hall.id }})</i>{{ hall.name }}' +
            '<span style="position: absolute; right: 0;  ">' +
                '<input type="button" value="edit" @click="edit"/>' +
                '<input type="button" value="X" @click="del"/>' +
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
      '<div style="position: relative; width: 300px;">' +
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