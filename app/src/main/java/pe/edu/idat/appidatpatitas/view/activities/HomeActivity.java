package pe.edu.idat.appidatpatitas.view.activities;

import static pe.edu.idat.appidatpatitas.util.SharePreferencesManager.getBooleanValue;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import pe.edu.idat.appidatpatitas.R;
import pe.edu.idat.appidatpatitas.bd.entity.Persona;
import pe.edu.idat.appidatpatitas.databinding.ActivityHomeBinding;
import pe.edu.idat.appidatpatitas.util.SharePreferencesManager;
import pe.edu.idat.appidatpatitas.viewmodel.PersonaViewModel;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    private PersonaViewModel personaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        personaViewModel = new ViewModelProvider(this)
                .get(PersonaViewModel.class);
        setSupportActionBar(binding.appBarHome.tlbmenu);
        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mascotaFragment, R.id.voluntarioFragment)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        mostrarInformacionDelUsuario();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void mostrarInformacionDelUsuario(){
        TextView tvnomusuario = binding.navView.getHeaderView(0)
                .findViewById(R.id.tvnomusuario);
        TextView tvemailusuario = binding.navView.getHeaderView(0)
                .findViewById(R.id.tvemailusuario);
        personaViewModel.obtenerPersona().observe(this,
                new Observer<Persona>() {
                    @Override
                    public void onChanged(Persona persona) {
                        boolean guardarclave = SharePreferencesManager.getBooleanValue("guardarclave");
                        tvnomusuario.setText(persona.getNombres()+
                                " "+persona.getApellidos());
                        tvemailusuario.setText(persona.getEmail() + " - "
                            +guardarclave);
                    }
                }
        );
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_cerrar){
            personaViewModel.eliminarPersona();
            startActivity(new Intent(
                    getApplicationContext(),
                    MainActivity.class
            ));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}