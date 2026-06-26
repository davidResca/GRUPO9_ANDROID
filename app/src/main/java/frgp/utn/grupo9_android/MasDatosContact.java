package frgp.utn.grupo9_android;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MasDatosContact extends AppCompatActivity {
    private RadioGroup rgEstudios;
    private CheckBox cbDeporte, cbMusica, cbArte, cbTecnologia;
    private Switch swRecibeInfo;

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
    }

    public boolean validarFormulario() {
        boolean esValido = true;

        if (rgEstudios.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Seleccione un nivel de estudios", Toast.LENGTH_SHORT).show();
            esValido = false;
        }

        return esValido;
    }

    public void eventoGuardar(View view) {
        if (!validarFormulario()) {
            return;
        }

        // PARA HACER: armar y guardar el Contacto
        Toast.makeText(this, "TODO OK", Toast.LENGTH_SHORT).show();
    }
}