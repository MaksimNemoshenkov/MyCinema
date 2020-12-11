const movieApi = Vue.resource('/mov{/id}');

Vue.component('movie-form', {
    props: ['movies'],
    data: function() {
        return {
            name: '',
            rating: ''
        }
    },
    template:
        '<div>' +
            '<input type="text" placeholder ="Write a movie name" v-model="name" />'+
            '<input type="number" placeholder ="Write a movie rating" v-model="rating" />'+
            '<input type="button" value="Save" @click="save"/>'+
        '</div>',
    methods: {
        save: function() {
            const movie = {name: this.name, rating: this.rating};
            movieApi.save({}, movie).then(result =>
                result.json().then(data => {
                    this.movies.push(data);
                    this.name ='';
                    this.rating='';
                })
            )
        }
    }
});

Vue.component('movie-row', {
    props: ['movie'],
    template:
    '<div>'+
    '<i>({{ movie.id }})</i>{{ movie.name }} <b>Rating: </b> {{ movie.rating }} '+
    '</div>'
});

Vue.component('movies-list', {
 props: ['movies'],
 template:
         '<div>'+
            '<movie-form :movies="movies"/>'+
            '<movie-row v-for="movie in movies" :key="movie.id" :movie="movie"/>'+
         '</div>',
 created: function(){
    movieApi.get().then(result =>
        result.json().then(data =>
            data.forEach(movie =>
                this.movies.push(movie))))
 }
});

const app = new Vue({
    el: '#app',
    template: '<movies-list :movies="movies"/>',
    data: {movies: []}
});

/*
function getIndex(list, id){
    for (var i = 0; i<list.length; i++ ){
        if (list[i].id === id){
            return i;
        }
    }
    return -1;
}

var movieApi = Vue.resource('/movie{/id}');

Vue.component('movie-form', {
    props: ['movies','movieAttr'],
    data: function(){
        return {
            text: '',
            id: ''
        }
    },
    watch: {
        movieAttr: function(newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
            '<input type = "text" placeholder = "write something " v-model="text"/>' +
            '<input type = "button" value= "Save" @click="save"/>' +
        '</div>',
    methods: {
       save: function(){
            var movie = { text: this.text };
            if(this.id) {
                movieApi.update({id: this.id}, movie).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.movies, data.id);
                        this.movies.splice(index, 1, data);
                        this.text = '';
                        this.id = '';
                    })
                )
            } else {
                movieApi.save({}, movie).then(result =>
                    result.json().then(data => {
                    this.movies.push(data);
                    this.text = '';
                    })
                )
            }
        }
    }
});

Vue.component('movie-row', {
    props: ['movie', 'editMethod', 'movies'],
    template:
        '<div>' +
            '<i>({{ movie.id }})</i>{{ movie.name }}' +
            '<span style="position: absolute; right: 0;  ">' +
                '<input type="button" value="edit" @click="edit"/>' +
                '<input type="button" value="X" @click="del"/>' +
            '</span>' +
        '</div>',
        methods: {
            edit: function(){
                this.editMethod(this.movie);
            },
            del: function(){
                movieApi.remove({id: this.movie.id}).then(result => {
                    if(result.ok) {
                        this.movies.splice(this.movies.indexOf(this.movie), 1)
                    }
                })

            }
        }
});

Vue.component('movies-list', {
  props: ['movies'],
  data: function(){
    return {
        movie: null
    }
  },
  template:
      '<div style="position: relative; width: 300px;">' +
          '<movie-form :movies="movies" :movieAttr="movie"/>' +
          '<movie-row v-for="movie in movies" :key="movie.id" :movie="movie" ' +
          ':editMethod="editMethod" :movies="movies"/>' +
      '</div>',
    created: function() {
      movieApi.get().then(result =>
        result.json().then(data =>
            data.forEach(movie => this.movies.push(movie))
            )
        )
    },
    methods: {
        editMethod: function(movie){
        this.movie = movie;
        }
    }
});

var app = new Vue({
  el: '#app',
  template: '<movies-list :movies="movies" />',
  data: {
    movies: []
  }
});*/