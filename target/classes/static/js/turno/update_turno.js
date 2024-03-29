window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado el turno
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        //let pacienteId = document.querySelector('#paciente_id').value; No tengo un ID del turno aun, cuando .save de guardar()
        //se ejecuta le da id (ver el servicio para mas info)

        const formData = {
            id: document.querySelector('#turno_id').value,
            fechaHora: document.querySelector('#fechaHora').value,
            pacienteId: document.querySelector('#paciente').selectedOptions[0].value,
            odontologoId: document.querySelector('#odontologo').selectedOptions[0].value,
        };

        console.log(formData);

        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => {
            if (response.ok){
             let successAlert = '<div class="alert alert-success alert-dismissible">' +
                                 '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                                 '<strong></strong> Turno actualizado <p> Mostrando los datos en 3...2...1... </p> </div>'

                             document.querySelector('#response').innerHTML = successAlert;
                             document.querySelector('#response').style.display = "block";
                }
            })
              // Esperar 3 segundos y luego recargar la página
                 setTimeout(function() {
                 location.reload();
                 }, 3000);
              // recargo la web para que se vea actualizado


    })
 })
    //Es la funcion que se invoca cuando se hace click sobre el id de un turno del listado
    //se encarga de llenar el formulario con los datos del mismo, necesita hacer get del paciente y del odontologo
    //con las fn loadPacientes() loadOdontologo()

    function findBy(id) {
          const url = '/turnos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let turno = data;
              document.querySelector('#turno_id').value = turno.id;
              document.querySelector('#fechaHora').value = turno.fechaHora;

              loadPacientes(turno.paciente);
              loadOdontologos(turno.odontologo);

                //document.querySelector('#div_turno_updating').style.display = "block"; Lo mejoramos por un modal
                 $('#updateModal').modal('show');
                 console.log("Mostrando el modal de Odontologo")
          }).catch(error => {
              alert("Error: " + error);
          })
      }

  function loadPacientes(pacienteSelected){
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
              if (pacienteSelected.id == paciente.id){
                option.selected = true;
              }
              formPaciente.add(option,null);
           }
         });
      }

      function loadOdontologos(odontologoSelected){
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
                    if (odontologoSelected.id == odontologo.id){
                      option.selected = true;
                    }
                    formOdontologo.add(option,null);
                 }
               });
            }