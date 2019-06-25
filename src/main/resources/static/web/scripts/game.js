var vm = new Vue({
    el: "#ships",
    data: {
        saludo: "hola",
        numeros: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
        letras: ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J"],
        gpID: new URL(window.location.href).searchParams.get("gp"),
        gamePlayData: {}
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
            }).catch(function (error) {
                console.log("Request failed:" + error.message);
            });
        }
/*         shipsLocaton: function() {
            if location.ship = true
                return bg-secondary
            else no change

        }*/

    },
    mounted() {
        this.obtenerGamePlay();
    }

})