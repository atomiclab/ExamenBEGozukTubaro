window.addEventListener('load', function () {

    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del paciente
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let pacienteId = document.querySelector('#paciente_id').value;

        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                id: document.querySelector('#domicilio_id').value,
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                provincia: document.querySelector('#provincia').value,
                localidad: document.querySelector('#localidad').value,
            }
        };

        const url = '/pacientes';
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
                                 '<strong></strong> Paciente actualizado <p> Mostrando resultado en 3...2...1...</p> </div>'

                             document.querySelector('#response').innerHTML = successAlert;
                             document.querySelector('#response').style.display = "block";
                }
            })
             // Esperar 3 segundos y luego recargar la página
                setTimeout(function() {
                    location.reload();
                }, 3000);
              //recargo la web para que se vea actualizado

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un paciente del listado
    //se encarga de llenar el formulario con los datos del mismo

    function findBy(id) {
          const url = '/pacientes'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              //console.log(data);
              let paciente = data;
              document.querySelector('#paciente_id').value = paciente.id;
              document.querySelector('#nombre').value = paciente.nombre;
              document.querySelector('#apellido').value = paciente.apellido;
              document.querySelector('#dni').value = paciente.dni;
              document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
              document.querySelector('#domicilio_id').value = paciente.domicilio.id;
              document.querySelector('#calle').value = paciente.domicilio.calle;
              document.querySelector('#numero').value = paciente.domicilio.numero;
              document.querySelector('#provincia').value = paciente.domicilio.provincia;
              document.querySelector('#localidad').value = paciente.domicilio.localidad;
              //el formulario por default esta oculto y al editar se habilita
              //document.querySelector('#div_paciente_updating').style.display = "block";
              $('#updateModal').modal('show');
              console.log("Mostrando el modal del paciente")
          }).catch(error => {
              alert("Error: " + error);
          })
      }