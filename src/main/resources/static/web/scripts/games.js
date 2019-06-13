var vm = new Vue({
    el: "#salvo",
    data: {
        gameData: {}
    },
    methods: {

        obtenerJuegos () {
            fetch("http://localhost:8080/api/games", {
                method: "GET",
            }).then(function (response) {
                if (response.ok) {
                    return response.json();
                }
                throw new Error(response.statusText);
            }).then(function (json) {
                vm.gameData = json;
                console.log("Juegos", vm.gameData);
            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            });
        }

    },

    mounted() {
        this.obtenerJuegos();
    }
})