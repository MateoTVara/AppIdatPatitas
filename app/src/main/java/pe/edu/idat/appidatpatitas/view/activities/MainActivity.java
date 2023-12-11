package pe.edu.idat.appidatpatitas.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import pe.edu.idat.appidatpatitas.R;
import pe.edu.idat.appidatpatitas.bd.entity.Persona;
import pe.edu.idat.appidatpatitas.databinding.ActivityMainBinding;
import pe.edu.idat.appidatpatitas.retrofit.request.LoginRequest;
import pe.edu.idat.appidatpatitas.retrofit.response.LoginResponse;
import pe.edu.idat.appidatpatitas.viewmodel.AuthViewModel;
import pe.edu.idat.appidatpatitas.viewmodel.PersonaViewModel;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    private ActivityMainBinding binding;
    private AuthViewModel authViewModel;
    private PersonaViewModel personaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        authViewModel = new ViewModelProvider(this)
                .get(AuthViewModel.class);
        personaViewModel = new ViewModelProvider(this)
                .get(PersonaViewModel.class);
        binding.btningresar.setOnClickListener(this);
        binding.btnregistro.setOnClickListener(this);
        authViewModel.loginResponseMutableLiveData
                .observe(this, new Observer<LoginResponse>() {
                    @Override
                    public void onChanged(LoginResponse loginResponse) {
                        validarLogin(loginResponse);
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btningresar){
            invocarLogin();
        }else{
            startActivity(
                    new Intent(MainActivity.this,
                            RegistroActivity.class)
            );
        }

    }

    public void invocarLogin(){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsuario(binding.etusuario.getText().toString());
        loginRequest.setPassword(binding.etpassword.getText().toString());
        authViewModel.autenticarUsuario(loginRequest);
    }

    public void validarLogin(LoginResponse loginResponse){
        if(loginResponse.isRpta()){
            startActivity(new Intent(MainActivity.this,
                    HomeActivity.class));
            Persona nuevaPersona = new Persona();
            nuevaPersona.setId(Integer.parseInt(loginResponse.getIdpersona()));
            nuevaPersona.setNombres(loginResponse.getNombres());
            nuevaPersona.setApellidos(loginResponse.getApellidos());
            nuevaPersona.setUsuario(loginResponse.getUsuario());
            nuevaPersona.setPassword(loginResponse.getPassword());
            nuevaPersona.setEmail(loginResponse.getEmail());
            nuevaPersona.setEsvoluntario(loginResponse.getEsvoluntario());
            personaViewModel.insertarPersona(nuevaPersona);
        }else{
            Snackbar.make(binding.getRoot(), loginResponse.getMensaje(),
                    Snackbar.LENGTH_LONG).show();
        }
    }

}