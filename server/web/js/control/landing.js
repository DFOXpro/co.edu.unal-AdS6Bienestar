app.controller('landing', function ($scope, $conexion) {
    console.log("landing");

    $scope.is = {escojeRol: false};
    var irARol = function ($scope) {
        var rol = localStorage.getItem("4");
        $scope.is.chc = window.btoa(localStorage.getItem("2"));
        if (rol=="A"){
            $scope.is.escojeRol = true;
        } else location.replace("usuario");
    };

    if ($conexion.iniciar())
        irARol($scope);
    else {
        //is Iniciar Sesion
        console.log($scope.is.escojeRol);
        $scope.is = {
            escojeRol: false,
            usuario: "",
            contrasena: "",
            error: "",
            isError: false,
            submit: function () {
                console.log("is.submit");
                $scope.is.error = "Enviando...";
                $scope.is.isError = true;
                $conexion.enviar(
                        "sesion",
                        {
                            tipo: "iniciar",
                            //btoa es un cifrador base64
                            1: window.btoa($scope.is.usuario),
                            2: window.btoa($scope.is.contrasena),
                            3: window.btoa($conexion.getCookieHashCode()),
                        },
                        function (respuesta) {
                            console.log("rta: ", respuesta);
                            if (respuesta.data.isError)
                                $scope.is.error = respuesta.data.errorDescrip;
                            else {
                                $scope.usr = respuesta.data.a;
                                localStorage.setItem("4", respuesta.data.b);
                                $conexion.pdc(respuesta.data.c);
                                $conexion.enviar(
                                        "sesion", {
                                            tipo: "cc", //ConfirmarCifrado
                                            1: $conexion.getLlavePublica()
                                        }
                                );
                                irARol($scope);
                            }
                        }
                )
            }
        }
    }
});
console.log("landingController cargado")
