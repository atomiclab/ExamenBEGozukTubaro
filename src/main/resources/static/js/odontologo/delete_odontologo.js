function deleteBy(id)
{
          //con fetch invocamos a la API de odontologos con el método DELETE
          //pasandole el id en la URL
          const url = '/odontologos/'+ id;
          const settings = {
              method: 'DELETE'
          }
          fetch(url,settings)
          .then(response => {
             if(response.ok){
              // Si la respuesta es satisfactoria (código de estado 200 al 299),
              // borramos la fila del odontólogo
               let row_id = "#tr_" + id;
               document.querySelector(row_id).remove();
             }
          })
          // Manejamos distintos tipos de errores:
          .catch((error) => {
              console.log(error);
              if (error.status == 404) {
                // Si el odontólogo no existe, mostramos un msj por consola
                console.log("Odontólgo no existe");
              } else if (error.status == 500) {
                 // Si hay un error del servidor, mostramos un mensaje en la consola
                console.log("Error del servidor");
              } else {
              //y para cualquier otra cosa
                console.log("Algo salio mal");
              }
            });
}