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
                /*vm.showPlayerName();*/
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
                    console.log("Funciona Logout");
                    window.location.reload();
                }
            }).catch(function (error) {
                console.log("Request failed: " + error.message);
            });
        },

        signup () {
            let firstName = document.getElementById("firstname").value;
            let lastName = document.getElementById("lastname").value;
            let userName = document.getElementById("username").value;
            let password = document.getElementById("password").value;

            fetch("http://localhost:8080/api/players", {
                method: "POST",
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
                body: JSON.stringify( {
                    firstName: firstName,
                    lastName: lastName,
                    userName: userName,
                    password: password
                })
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

        //show the current user's name if there is a non-null value for player in the game data.
        /*showPlayerName(){
            if (this.gameData.player != null){
                document.getElementById("logeado").innerHTML = this.gameData.player.email;
            }
        }*/

        showPlayButton(game) {
           if (this.gameData.player != null && game.gameplayer.map(gp => gp.player.id).includes(this.gameData.player.id)){
               return true;
           } else {
               return false;
           }
        },
        /* v-if="gameData.player != null && gameData.games.gameplayer.map(gp => gp.player.id).includes(gameplayer.player.id)"*/

        createGame () {
            fetch("http://localhost:8080/api/games", {
                method: "POST",
                credentials: 'include',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                },
            }).then(response => {
                console.log(response)
                if (response.ok) {
                    console.log("You are logged in");
                    return response.json();
                }
                else console.log("You are NOT logged in");
            }).then(function (json) {
                window.location.href = "http://localhost:8080/web/game.html?gp=" + json.gpid
                console.log("GPID", json);
            }).catch(function (error) {
                console.log("Request failed: " + error.message);

            });
        },

        showCreateButton() {
            if (this.gameData.player != null){
                return true;
            } else {
                return false;
            }
        },

        /*If the request is successful, the JavaScript should send the browser to the URL game.html?gp=mm where mm is the new game player ID*/

        sendToGame(game) {
            let newGamePlayerID = game.gameplayer.filter(gp => gp.player.id == this.gameData.player.id)[0].id;
            console.log("hola", "hola");
            window.location.href = "http://localhost:8080/web/game.html?gp=" + newGamePlayerID
        },

        showJoinButton(game) {
            if (this.gameData.player != null && !game.gameplayer.map(gp => gp.player.id).includes(this.gameData.player.id)){
                return true;
            } else {
                return false;
            }
        },
    },

    mounted() {
        this.obtenerJuegos();
    }
})