var vm = new Vue({
    el: "#salvo",
    data: {
        gameData: {},
        mostrarResultados: ""
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
                vm.mostrarResultados = vm.gameData;
                vm.filtrarResultados();
                console.log("Juegos", vm.gameData);
            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            });
        },
        
        filtrarResultados: function () {
            let arrayId = Array.from(new Set(this.gameData.flatMap(juego => juego.gameplayer)
                .map(jugador => jugador.player.id)));


            this.mostrarResultados = arrayId.map(id => this.gameData.flatMap(juego => juego.gameplayer)
                .map(jugador => jugador.player)
                .find(player => player.id == id));

        },

    },

    mounted() {
        this.obtenerJuegos();
    }
})