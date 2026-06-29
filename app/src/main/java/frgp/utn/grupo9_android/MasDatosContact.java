package frgp.utn.grupo9_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import Entidad.Contacto;
import OpenHelper.ContactoOpenHelper;

public class MasDatosContact extends AppCompatActivity {
    private RadioGroup rgEstudios;
    private CheckBox cbDeporte, cbMusica, cbArte, cbTecnologia;
    private Switch swRecibeInfo;

    private ContactoOpenHelper bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mas_datos_contact);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgEstudios = findViewById(R.id.rgEstudios);

        cbDeporte = findViewById(R.id.cbDeporte);
        cbMusica = findViewById(R.id.cbMusica);
        cbArte = findViewById(R.id.cbArte);
        cbTecnologia = findViewById(R.id.cbTecnologia);
        swRecibeInfo = findViewById(R.id.swRecibeInfo);

        bd = new ContactoOpenHelper(this, "BD_Contactos", null, 1);
    }

    public boolean validarFormulario() {
        boolean esValido = true;

        if (rgEstudios.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Seleccione un nivel de estudios", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        if (!cbDeporte.isChecked() && !cbMusica.isChecked() && !cbArte.isChecked() && !cbTecnologia.isChecked()) {
            Toast.makeText(this, "Seleccione al menos un interes", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }

    public void eventoGuardar(View view) {
        if (!validarFormulario()) {
            return;
        }

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");
        String apellido = intent.getStringExtra("apellido");
        String telefono = intent.getStringExtra("telefono");
        String tipoTelefono = intent.getStringExtra("tipoTelefono");
        String email = intent.getStringExtra("email");
        String tipoEmail = intent.getStringExtra("tipoEmail");
        String direccion = intent.getStringExtra("direccion");
        String fechaNacimiento = intent.getStringExtra("fechaNacimiento");

        RadioButton rbSeleccionado = findViewById(rgEstudios.getCheckedRadioButtonId());
        String nivelEstudios = rbSeleccionado.getText().toString();

        String intereses = "";
        if (cbDeporte.isChecked()) intereses += "Deporte ";
        if (cbMusica.isChecked()) intereses += "Musica ";
        if (cbArte.isChecked()) intereses += "Arte ";
        if (cbTecnologia.isChecked()) intereses += "Tecnologia ";

        boolean recibeInfo = swRecibeInfo.isChecked();

        Contacto c = new Contacto(nombre, apellido, telefono, tipoTelefono, email, tipoEmail, direccion, fechaNacimiento, nivelEstudios, recibeInfo, intereses);
        boolean guardado = bd.insertarContacto(c);

        if (guardado) {
            Toast.makeText(this, "Contacto guardado con éxito", Toast.LENGTH_SHORT).show();
            Intent intentListado = new Intent(this, ListContact.class);
            intentListado.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentListado);
            finish();
        } else {
            Toast.makeText(this, "Error al guardar el contacto", Toast.LENGTH_SHORT).show();
        }
    }
}
