function deleteBy(id)
{
          const url = '/turnos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => {
             if(response.ok){
               let row_id = "#tr_" + id;
               document.querySelector(row_id).remove();
             }
          })
          .catch((error) => {
              console.log(error);
              if (error.status == 404) {
                alert("Turno no existe");
              } else if (error.status == 500) {
                //Error del servidor
                alert("Error del servidor");
              } else {
                alert("Oops! Algo salió mal");
              }
            });
}