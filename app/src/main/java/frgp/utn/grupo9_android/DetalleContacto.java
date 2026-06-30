package frgp.utn.grupo9_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalleContacto extends AppCompatActivity {

    private TextView txtDetalleNombre, txtDetalleApellido, txtDetalleTelefono;
    private TextView txtDetalleEmail, txtDetalleDireccion;
    private TextView txtDetalleFechaNacimiento, txtDetalleEstudios;
    private TextView txtDetalleIntereses, txtDetalleRecibeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_contacto);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtDetalleNombre = findViewById(R.id.txtDetalleNombre);
        txtDetalleApellido = findViewById(R.id.txtDetalleApellido);
        txtDetalleTelefono = findViewById(R.id.txtDetalleTelefono);
        txtDetalleEmail = findViewById(R.id.txtDetalleEmail);
        txtDetalleDireccion = findViewById(R.id.txtDetalleDireccion);

        txtDetalleFechaNacimiento = findViewById(R.id.txtDetalleFechaNacimiento);
        txtDetalleEstudios = findViewById(R.id.txtDetalleEstudios);
        txtDetalleIntereses = findViewById(R.id.txtDetalleIntereses);
        txtDetalleRecibeInfo = findViewById(R.id.txtDetalleRecibeInfo);


        Intent intent = getIntent();
        txtDetalleNombre.setText(intent.getStringExtra("nombre"));
        txtDetalleApellido.setText(intent.getStringExtra("apellido"));
        txtDetalleTelefono.setText(intent.getStringExtra("telefono"));
        txtDetalleEmail.setText(intent.getStringExtra("email"));
        txtDetalleDireccion.setText(intent.getStringExtra("direccion"));

        txtDetalleFechaNacimiento.setText(intent.getStringExtra("fechaNacimiento"));
        txtDetalleEstudios.setText(intent.getStringExtra("nivelEstudios"));
        txtDetalleIntereses.setText(intent.getStringExtra("intereses"));
        boolean recibeInfo = intent.getBooleanExtra("recibeInfo", false);
        txtDetalleRecibeInfo.setText(recibeInfo ? "Sí" : "No");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_listContact){
            Intent intent = new Intent(this, ListContact.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.menu_addContact){
            Intent intent = new Intent(this, AddContact.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}