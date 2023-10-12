package com.android2023.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button btnEnviar;
    EditText mensaje;
    TextView recibir, nombre;
    FirebaseDatabase fireBaseDB;
    DatabaseReference databaseReference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEnviar = findViewById(R.id.btnEnviar);
        mensaje = findViewById(R.id.txtEnviar);
        recibir = findViewById(R.id.textView);
        nombre = findViewById(R.id.txtNombre);

        fireBaseDB = FirebaseDatabase.getInstance();
        databaseReference = fireBaseDB.getReference();

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("Mensaje").setValue(mensaje.getText().toString());
            }
        });
        databaseReference.child("Nombre").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String dato = snapshot.getValue(String.class);
                nombre.setText(dato);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        databaseReference.child("Mensaje").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String datos = snapshot.getValue(String.class);
                recibir.setText(datos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}