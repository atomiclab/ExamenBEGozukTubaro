window.addEventListener('load', function () {

        //es la combinacion de los post de Odontologo + paciente

    const formulario = document.querySelector('#add_new_turno');
    document.querySelector('#fechaHora').value = moment().format('yyyy-MM-DDTHH:mm');

    loadPacientes();
    loadOdontologos();

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = {
           fechaHora: document.querySelector('#fechaHora').value,
           pacienteId: document.querySelector('#paciente').selectedOptions[0].value,
           odontologoId: document.querySelector('#odontologo').selectedOptions[0].value
        }

        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                 let successAlert = '<div class="alert alert-success alert-dismissible">' +
                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                     '<strong> Turno agregado</strong> </div>'

                 document.querySelector('#response').innerHTML = successAlert;
                 document.querySelector('#response').style.display = "block";
                 resetUploadForm();

            })
            .catch(error => {
                    let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                                     '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                     '<strong> Error intente nuevamente</strong> </div>'

                      document.querySelector('#response').innerHTML = errorAlert;
                      document.querySelector('#response').style.display = "block";
                     resetUploadForm();})
    });


    function resetUploadForm(){
        document.querySelector('#fechaHora').value = moment().format('yyyy-MM-DDTHH:mm');
        document.querySelector('#paciente').options[0].selected = true;
        document.querySelector('#odontologo').options[0].selected = true;
    }

    function loadPacientes(){
           const url = '/pacientes'
             const settings = {
                 method: 'GET'
             }
             fetch(url,settings)
             .then(response => response.json())
             .then(data => {
               let formPaciente = document.querySelector('#paciente');
               for(paciente of data){
                  let option = document.createElement('option');
                  option.text = paciente.dni + " - "+paciente.nombre+" "+paciente.apellido;
                  option.value = paciente.id;

                  formPaciente.add(option,null);
               }
             });
          }

          function loadOdontologos(){
                 const url = '/odontologos'
                   const settings = {
                       method: 'GET'
                   }
                   fetch(url,settings)
                   .then(response => response.json())
                   .then(data => {
                     let formOdontologo = document.querySelector('#odontologo');
                     for(odontologo of data){
                        let option = document.createElement('option');
                        option.text = odontologo.nombre + " "+odontologo.apellido+" - "+odontologo.matricula;
                        option.value = odontologo.id;

                        formOdontologo.add(option,null);
                     }
                   });
                }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnoLista.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});