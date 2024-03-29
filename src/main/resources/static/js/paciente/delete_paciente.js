function deleteBy(id)
{
          //con fetch invocamos a la API de odontologos con el método DELETE
          //pasandole el id en la URL
          const url = '/pacientes/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => {
             if(response.ok){
             //borrar la fila
               let row_id = "#tr_" + id;
               document.querySelector(row_id).remove();
             }
          })
          .catch((error) => {
              console.log(error);
              if (error.status == 404) {
                alert("Paciente no existe");
              } else if (error.status == 500) {
                //Error del servidor
                alert("Error del servidor");
              } else {
                alert("Oops! Algo salió mal");
              }
            });
}