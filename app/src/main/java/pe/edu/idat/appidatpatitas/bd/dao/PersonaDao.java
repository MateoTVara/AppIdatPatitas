package pe.edu.idat.appidatpatitas.bd.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import pe.edu.idat.appidatpatitas.bd.entity.Persona;

@Dao
public interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertar(Persona persona);
    @Update
    void actualizar(Persona persona);
    @Query("DELETE FROM persona")
    void eliminar();
    @Query("SELECT * FROM persona LIMIT 1")
    LiveData<Persona> obtener();

}
