window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado el odontologo
    const formulario = document.querySelector('#update_dentist_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let odontologoId = document.querySelector('#dentist_id').value;

        const formData = {
            id: document.querySelector('#dentist_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        const url = '/odontologos';
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
                                 '<strong></strong> Odontólogo actualizado <p> Mostrando resultado en 3...2...1...</p></div>'

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

    //Es la funcion que se invoca cuando se hace click sobre el id de un odontologo del listado
    //se encarga de llenar el formulario con los datos del mismo
   
    function findBy(id) {

          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#dentist_id').value = odontologo.id;
              document.querySelector('#nombre').value = odontologo.nombre;
              document.querySelector('#apellido').value = odontologo.apellido;
              document.querySelector('#matricula').value = odontologo.matricula;
              //el formulario por default esta oculto y al editar se habilita
              //document.querySelector('#div_dentist_updating').style.display = "block";
               $('#updateModal').modal('show');
              console.log("Mostrando el modal de Odontologo")

          }).catch(error => {
              alert("Error: " + error);
          })
      }