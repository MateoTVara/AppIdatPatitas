package pe.edu.idat.appidatpatitas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import pe.edu.idat.appidatpatitas.retrofit.PatitasCliente;
import pe.edu.idat.appidatpatitas.retrofit.request.LoginRequest;
import pe.edu.idat.appidatpatitas.retrofit.request.RegistroRequest;
import pe.edu.idat.appidatpatitas.retrofit.response.LoginResponse;
import pe.edu.idat.appidatpatitas.retrofit.response.RegistroResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends AndroidViewModel {

    public MutableLiveData<LoginResponse> loginResponseMutableLiveData
            = new MutableLiveData<>();

    public MutableLiveData<RegistroResponse> registroResponseMutableLiveData
            = new MutableLiveData<>();

    public AuthViewModel(@NonNull Application application) {
        super(application);
    }

    public void autenticarUsuario(LoginRequest loginRequest){
        new PatitasCliente().getInstance().login(loginRequest)
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        loginResponseMutableLiveData.setValue(
                                response.body()
                        );
                    }
                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void registroUsuario(RegistroRequest registroRequest){
        new PatitasCliente().getInstance().registro(registroRequest)
                .enqueue(new Callback<RegistroResponse>() {
                    @Override
                    public void onResponse(Call<RegistroResponse> call, Response<RegistroResponse> response) {
                        registroResponseMutableLiveData
                                .setValue(response.body());
                    }

                    @Override
                    public void onFailure(Call<RegistroResponse> call, Throwable t) {

                    }
                });
    }
}
