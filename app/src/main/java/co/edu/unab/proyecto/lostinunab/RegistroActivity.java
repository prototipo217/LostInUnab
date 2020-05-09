package co.edu.unab.proyecto.lostinunab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistroActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edtUsuario;
    EditText edtPassword;
    Button btnGuarda;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        firebaseAuth = FirebaseAuth.getInstance();


        edtUsuario =  findViewById(R.id.edtUsuario);
        edtPassword =  findViewById(R.id.edtContraseña);
        btnGuarda =  findViewById(R.id.btnGuardar);
        progressDialog = new ProgressDialog(this);

        btnGuarda.setOnClickListener(this);
    }

    private void registrarUsuario(){

        //Obtenemos los datos
        String email = edtUsuario.getText().toString().trim();
        String password  = edtPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //Este metodo crea el usuario
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            Toast.makeText(RegistroActivity.this,"" + edtUsuario.getText()+" Se ha Registrado Correctamente",Toast.LENGTH_LONG).show();
                           
                            edtUsuario.setText("");
                            edtPassword.setText("");
                            
                        }else{

                            Toast.makeText(RegistroActivity.this,"Algo Salio Mal ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {
        registrarUsuario();
    }
}