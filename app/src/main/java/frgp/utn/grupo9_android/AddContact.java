package frgp.utn.grupo9_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContact extends AppCompatActivity {

    private EditText txtNombre, txtApellido, txtTelefono, txtEmail, txtFechaNac;
    private Spinner spTelefono, spEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtNombre = findViewById(R.id.editTextNombre);
        txtApellido = findViewById(R.id.editTextApellido);
        txtTelefono = findViewById(R.id.editTextTelefono);
        txtEmail = findViewById(R.id.editTextEmail);
        txtFechaNac = findViewById(R.id.editTextFechaNacimiento);

        spTelefono = findViewById(R.id.spinnerTelefono);
        spEmail = findViewById(R.id.spinnerEmail);

        String[] opciones = {"Casa", "Trabajo", "Móvil"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, opciones);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spTelefono.setAdapter(adapter);
        spEmail.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_listContact) {
            Intent intent = new Intent(this, ListContact.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_addContact) {
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Validaciones
    public boolean validarFormatos() {
        boolean esValido = true;

        String nombre = txtNombre.getText().toString().trim();
        String apellido = txtApellido.getText().toString().trim();
        String telefono = txtTelefono.getText().toString().trim();
        String email = txtEmail.getText().toString().trim();
        String fecha = txtFechaNac.getText().toString().trim();


        // Si algún campo está vacío, marcamos el error y retornamos false

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || fecha.isEmpty()) {
            if (nombre.isEmpty()) txtNombre.setError("Este campo es obligatorio");
            if (apellido.isEmpty()) txtApellido.setError("Este campo es obligatorio");
            if (telefono.isEmpty()) txtTelefono.setError("Este campo es obligatorio");
            if (email.isEmpty()) txtEmail.setError("Este campo es obligatorio");
            if (fecha.isEmpty()) txtFechaNac.setError("Este campo es obligatorio");
            esValido = false;
        }
        // No ingresar numeros
        if (nombre.matches(".*\\d.*")) {
            txtNombre.setError("El nombre no puede contener números");
            esValido = false;
        }
        if (apellido.matches(".*\\d.*")) {
            txtApellido.setError("El apellido no puede contener números");
            esValido = false;
        }

        // Solo se permite numeros y guiones
        if (!telefono.isEmpty() && !telefono.matches("^[0-9-]+$")) {
            txtTelefono.setError("Solo se permiten números y guiones");
            esValido = false;
        }

        // Email válido
        if (!email.isEmpty() && !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            txtEmail.setError("Ingrese un email válido");
            esValido = false;
        }

        // Fecha de nacimiento válida usando el formato DD/MM/YYYY
        if (!fecha.isEmpty() && !fecha.matches("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/(19|20)\\d\\d$")) {
            txtFechaNac.setError("Formato válido: DD/MM/YYYY");
            esValido = false;
        }

        return esValido;
    }
}