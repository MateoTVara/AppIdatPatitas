package pe.edu.idat.appidatpatitas.bd;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pe.edu.idat.appidatpatitas.bd.dao.PersonaDao;
import pe.edu.idat.appidatpatitas.bd.entity.Persona;

@Database(entities = {Persona.class}, version = 1)
public abstract class IdatRoomDatabase extends RoomDatabase
{
    public abstract PersonaDao personaDao();
    private static volatile  IdatRoomDatabase INSTANCIA;
    public static IdatRoomDatabase getDatabase(final Context context){
        if(INSTANCIA == null){
            synchronized (IdatRoomDatabase.class){
                if(INSTANCIA == null){
                    INSTANCIA = Room.databaseBuilder(
                            context.getApplicationContext(),
                            IdatRoomDatabase.class,
                                    "idatbd")
                            .build();
                }
            }
        }
        return INSTANCIA;
    }


}
