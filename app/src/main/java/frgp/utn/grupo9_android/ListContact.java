package frgp.utn.grupo9_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import Entidad.Contacto;
import OpenHelper.ContactoOpenHelper;


public class ListContact extends AppCompatActivity {

    private ListView listViewContactos;
    private ContactoOpenHelper bd;

    @Override
    protected void onResume() {
        super.onResume();
        cargarListview();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listViewContactos = findViewById(R.id.listViewContactos);
        bd = new ContactoOpenHelper(this, "BD_Contactos", null, 1);
        cargarListview();

        listViewContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contacto contactoSeleccionado = (Contacto) parent.getItemAtPosition(position);

                Intent intent = new Intent(ListContact.this, DetalleContacto.class);

                intent.putExtra("nombre", contactoSeleccionado.getNombre());
                intent.putExtra("apellido", contactoSeleccionado.getApellido());
                intent.putExtra("telefono", contactoSeleccionado.getTelefono());
                intent.putExtra("email", contactoSeleccionado.getEmail());
                intent.putExtra("direccion", contactoSeleccionado.getDireccion());

                startActivity(intent);
            }
        });
    }

    public void cargarListview() {
        ArrayList<Contacto> listaContactos = bd.getListadoContactos();
        listViewContactos.setAdapter(new ArrayAdapter<Contacto>(getApplicationContext(), android.R.layout.simple_list_item_1, listaContactos));
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