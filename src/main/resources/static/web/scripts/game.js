var vm = new Vue({
    el: "#ships",
    data: {
        saludo: "hola",
        numeros: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        letras: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        gpID: new URL(window.location.href).searchParams.get("gp"),
        gamePlayData: []
    },
    methods: {

        obtenerGamePlay () {
            fetch("http://localhost:8080/api/game_view/"+this.gpID, {
                method: "GET",
            }).then(function (response) {
                if (response.ok) {
                    return response.json();
                }
                throw new Error(response.statusText);
            }).then(function (json) {
                vm.gamePlayData = json;
                console.log("Juegos", vm.gamePlayData);
                vm.shipsLocaton(vm.gamePlayData);
                vm.showActivePlayer(vm.gamePlayData);
                vm.showPasivePlayer(vm.gamePlayData);
            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            });
        },
         shipsLocaton: function(data) {
            data.ships.flatMap(ship => ship.locations)
                .forEach(position => document.getElementById(position).style.backgroundColor = '#6c757d');
        },
        showActivePlayer: function () {
             return this.gamePlayData.gameplayer.find(gameplayers => gameplayers.id == this.gpID).player.email;
        },
        showPasivePlayer: function () {
            return this.gamePlayData.gameplayer.find(gameplayers => gameplayers.id != this.gpID).player.email;
        }

    },
    mounted() {
        this.obtenerGamePlay();
    }

})