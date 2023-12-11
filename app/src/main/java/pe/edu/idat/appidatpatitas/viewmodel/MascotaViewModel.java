package pe.edu.idat.appidatpatitas.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import pe.edu.idat.appidatpatitas.retrofit.PatitasCliente;
import pe.edu.idat.appidatpatitas.retrofit.response.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MascotaViewModel extends AndroidViewModel {
    public MutableLiveData<List<MascotaResponse>> listMutableLiveData
            = new MutableLiveData<>();
    public MascotaViewModel(@NonNull Application application) {
        super(application);
    }

    public void listarMascotas(){
        new PatitasCliente().getInstance().listarMascotas()
                .enqueue(new Callback<List<MascotaResponse>>() {
                    @Override
                    public void onResponse(Call<List<MascotaResponse>> call, Response<List<MascotaResponse>> response) {
                        listMutableLiveData.setValue(response.body());
                    }
                    @Override
                    public void onFailure(Call<List<MascotaResponse>> call, Throwable t) {

                    }
                });
    }
}
