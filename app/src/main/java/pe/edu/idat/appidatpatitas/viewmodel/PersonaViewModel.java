package pe.edu.idat.appidatpatitas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import pe.edu.idat.appidatpatitas.bd.entity.Persona;
import pe.edu.idat.appidatpatitas.repository.PersonaRepository;

public class PersonaViewModel extends AndroidViewModel {
    private LiveData<Persona> personaLiveData;
    private PersonaRepository personaRepository;
    public PersonaViewModel(@NonNull Application application) {
        super(application);
        personaRepository = new PersonaRepository(application);
    }
    public LiveData<Persona> obtenerPersona(){
        return personaRepository.obtenerPersona();
    }
    public void insertarPersona(Persona persona){
        personaRepository.registrarPersona(persona);
    }
    public void actualizarPersona(Persona persona){
        personaRepository.actualizarPersona(persona);
    }
    public void eliminarPersona(){
        personaRepository.eliminarPersona();
    }
}
