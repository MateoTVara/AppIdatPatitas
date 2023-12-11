package pe.edu.idat.appidatpatitas.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import pe.edu.idat.appidatpatitas.bd.IdatRoomDatabase;
import pe.edu.idat.appidatpatitas.bd.dao.PersonaDao;
import pe.edu.idat.appidatpatitas.bd.entity.Persona;

public class PersonaRepository {
    private PersonaDao personaDao;
    public PersonaRepository(Application application){
        IdatRoomDatabase db = IdatRoomDatabase.getDatabase(application);
        personaDao = db.personaDao();
    }
    public LiveData<Persona> obtenerPersona(){
        return personaDao.obtener();
    }
    public void registrarPersona(Persona persona){
        new registrarAsyncTask(personaDao).execute(persona);
    }
    private static class registrarAsyncTask
            extends AsyncTask<Persona, Void, Void>{
        private PersonaDao personaDao;
        registrarAsyncTask(PersonaDao _personaDao){
            personaDao = _personaDao;
        }
        @Override
        protected Void doInBackground(Persona... personas) {
            personaDao.insertar(personas[0]);
            return null;
        }
    }
    public void actualizarPersona(Persona persona){
        new actualizarAsyncTask(personaDao).execute(persona);
    }
    private static class actualizarAsyncTask
            extends AsyncTask<Persona, Void, Void>{
        private PersonaDao personaDao;
        actualizarAsyncTask(PersonaDao _personaDao){
            personaDao = _personaDao;
        }
        @Override
        protected Void doInBackground(Persona... personas) {
            personaDao.actualizar(personas[0]);
            return null;
        }
    }

    public void eliminarPersona(){
        new eliminarAsyncTask(personaDao).execute();
    }
    private static class eliminarAsyncTask
            extends AsyncTask<Void, Void, Void>{
        private PersonaDao personaDao;
        eliminarAsyncTask(PersonaDao _personaDao){
            personaDao = _personaDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            personaDao.eliminar();
            return null;
        }
    }
}
