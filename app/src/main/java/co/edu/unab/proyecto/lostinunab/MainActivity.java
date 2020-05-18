package co.edu.unab.proyecto.lostinunab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnRegistra;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRegistra = findViewById(R.id.btnRegistro);
        btnIngresar = findViewById(R.id.btnLogin);


       btnIngresar.setOnClickListener(this);
       btnRegistra.setOnClickListener(this);

    }


    //aplicacoms las funciones a los botones
    @Override
    public void onClick(View view) {
        switch (view.getId()){

            //para registrarnos
            case R.id.btnRegistro:
                Intent in = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(in);
                onBackPressed();
                break;


            case R.id.btnLogin:

                //pedimos la opcion de ingreso a la informacion

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("¿Como desea acceder a la información?");
                builder.setTitle("Opción de Acceso");

                //Nos envia al activity login
                builder.setPositiveButton("Logueado", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent logi = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(logi);
                        onBackPressed();
                        finish();
                    }
                });

                    //Nos envia directamente al activity de objetos
                builder.setNegativeButton("Sin Loguearme", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent logi = new Intent(MainActivity.this, ObjetosActivity.class);
                        startActivity(logi);
                        onBackPressed();
                        finish();
                        dialog.cancel();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


        }

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }


}


