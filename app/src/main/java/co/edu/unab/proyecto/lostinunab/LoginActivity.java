package co.edu.unab.proyecto.lostinunab;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import io.grpc.internal.AbstractReadableBuffer;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnIngresar;
    EditText edtUser;
    EditText edtPassword;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        btnIngresar = findViewById(R.id.btnPassword);
        edtUser = findViewById(R.id.editText);
        edtPassword = findViewById(R.id.editText2);

        btnIngresar.setOnClickListener(this);

    }




    private void loguearUsuario() {
        //Obtenemos los datos y validamos los editText
        String email = edtUser.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast toast = Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast toast = Toast.makeText(this, "Favor introducir la contraseña", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            v.setTextColor(Color.RED);
            toast.show();
            return;
        }


        //Validamos el usuario en el Firebase
        //Este metodo valida la informacion automaticamente
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    Toast toast = Toast.makeText(LoginActivity.this, "Bienvenido al sistema ", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    v.setTextColor(Color.RED);
                    toast.show();

                } else {

                    Toast toast = Toast.makeText(LoginActivity.this, "NO Encontramos Registros ", Toast.LENGTH_SHORT);
                    TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
                    v.setTextColor(Color.RED);
                    toast.show();

                }

            }

        });

    }

    public void onClick(View view) {
        loguearUsuario();
    }

}
