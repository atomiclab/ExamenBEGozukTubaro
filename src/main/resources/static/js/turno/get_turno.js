window.addEventListener('load', function () {
    (function(){

      //con fetch invocamos a la API de turnos con el método GET
      //nos devolverá un JSON con una colección de turnos
      const url = '/turnos';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
        //recorremos la colección de turnos del JSON

         for(turno of data){
         //por cada turno armaremos una fila de la tabla
         //cada fila tendrá un id que luego nos permitirá borrar la fila si eliminamos el turno
            var table = document.getElementById("turnoTableBody");
            var turnoRow =table.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;
            //por cada turno creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un turno
            let deleteButton = '<button' +
                              ' id=' + '\"' + 'btn_delete_' + turno.id + '\"' +
                              ' type="button" onclick="deleteBy('+turno.id+')" class="btn btn-danger btn_delete">' +
                              '&times' +
                              '</button>';

            //por cada turno un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar la pelicula que queremos
            //modificar y mostrar los datos de la misma en un formulario.
            let updateButton = '<button' +
                              ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
                              ' type="button" onclick="findBy('+turno.id+')" class="btn btn-info btn_id">' +
                              turno.id +
                              '</button>';
            let fechaMoment = moment(turno.fechaHora);

            turnoRow.innerHTML =
                    '<td class=\"td_id\">' + turno.id + '</td>' +
                    '<td class=\"td_fechahora\">' + fechaMoment.format('DD/MM/YYYY - HH:mm') + '</td>' +
                    '<td class=\"td_dni\">' + turno.paciente.dni + '</td>' +
                    '<td class=\"td_paciente\" data-paciente="'+turno.paciente.id+'">' + turno.paciente.nombre + ' '+turno.paciente.apellido+'</td>' +
                    '<td class=\"td_odontologo\" data-odontologo="'+turno.odontologo.id+'">' + turno.odontologo.nombre + ' '+turno.odontologo.apellido+'</td>' +
                    '<td>' + updateButton + '</td>' +
                    '<td>' + deleteButton + '</td>';
        };

    })
    })

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnoLista.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })