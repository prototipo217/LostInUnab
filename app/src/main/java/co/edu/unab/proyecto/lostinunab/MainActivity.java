package co.edu.unab.proyecto.lostinunab;

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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnRegistro:
                Intent in = new Intent(MainActivity.this, RegistroActivity.class);
                startActivity(in);
                finish();
                break;


            case R.id.btnLogin:
                Intent logi = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(logi);
                finish();
                break;
        }

    }


}


