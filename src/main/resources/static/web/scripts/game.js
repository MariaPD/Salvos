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
                vm.showActivePlayer();
                vm.showPasivePlayer();
                vm.salvoFired();
                vm.salvoHits();
            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            });
        },
         shipsLocaton: function(data) {
            data.ships.flatMap(ship => ship.locations)
                .forEach(position => document.getElementById("ship" + position).style.backgroundColor = '#6C757D');
        },
        showActivePlayer: function () {
             return this.gamePlayData.gameplayer.find(gameplayers => gameplayers.id == this.gpID).player.email;
        },
        showPasivePlayer: function () {
            return this.gamePlayData.gameplayer.find(gameplayers => gameplayers.id != this.gpID).player.email;
        },
        salvoFired: function () {
            let playerID = this.gamePlayData.gameplayer.find(gameplayers => gameplayers.id == this.gpID).player.id;

            this.gamePlayData.salvos.filter(salvo => (salvo.player == playerID))
                .forEach(salvo => salvo.locations.forEach(position => {
                    document.getElementById("salvo" + position).style.backgroundColor = '#E5BE01';
                    document.getElementById("salvo" + position).innerHTML = salvo.turn;
                }))
        },
        salvoHits: function () {
            let playerID = this.gamePlayData.gameplayer.find(gameplayers => gameplayers.id != this.gpID).player.id;
            let shipsLocations = this.gamePlayData.ships.flatMap(ship => ship.locations);

            this.gamePlayData.salvos.filter(salvo => (salvo.player == playerID))
                .forEach(salvo => salvo.locations.forEach(position => {
                    if(shipsLocations.includes(position)){
                        document.getElementById("ship" + position).style.backgroundColor = '#815762';
                        document.getElementById("ship" + position).innerHTML = salvo.turn;
                    }
                    else {
                        document.getElementById("ship" + position).style.backgroundColor = '#9B111E';
                        document.getElementById("ship" + position).innerHTML = salvo.turn;
                    }
                }))
        },
        /*backToGames() {
            fetch("http://localhost:8080/api/logout", {
                method: "POST"
            }).then(response => {
                console.log(response)
                if (response.ok) {
                    console.log("Funciona Logout");
                    window.location.reload();
                }
            }).catch(function (error) {
                console.log("Request failed: " + error.message);
            });
        }*/
    },
    mounted() {
        this.obtenerGamePlay();
    }

})