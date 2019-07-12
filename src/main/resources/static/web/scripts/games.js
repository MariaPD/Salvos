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
            let arrayId = Array.from(new Set(this.gameData.games.flatMap(juego => juego.gameplayer)
                .map(jugador => jugador.player.id)));


            this.mostrarResultados = arrayId.map(id => this.gameData.games.flatMap(juego => juego.gameplayer)
                .map(jugador => jugador.player)
                .find(player => player.id == id));

        },

        login () {
            let username = document.getElementById("username").value;
            let password = document.getElementById("password").value;

            fetch("http://localhost:8080/api/login", {
                method: "POST",
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'Accept': 'application/json'
                },
                body: `username=${username}&password=${password}`
            }).then(response => {
                console.log(response)
                if (response.ok) {
                    console.log("Funciona");
                    window.location.reload();
                }
                else console.log("No funciona");
            }).catch(function (error) {
                console.log("Request failed: " + error.message);

            });
        },

        logout () {
            fetch("http://localhost:8080/api/logout", {
                method: "POST"
            }).then(response => {
                console.log(response)
                if (response.ok) {
                    window.location.reload();
                }
            }).catch(function (error) {
                console.log("Request failed: " + error.message);
            });
        }

    },

    mounted() {
        this.obtenerJuegos();
    }
})