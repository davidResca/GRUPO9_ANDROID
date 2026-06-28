package OpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;

import Entidad.Contacto;

public class ContactoOpenHelper extends SQLiteOpenHelper{
    public static String ContactoCreacionTabla = "CREATE TABLE IF NOT EXISTS contactos(_ID integer primary key autoincrement, Nombre text, Apellido text, Telefono text, TipoTelefono text, Email text, TipoEmail text, Direccion text, FechaNacimiento text, NivelEstudios text, Intereses text, RecibeInfo integer)";
    public static String ContactoTabla = "contactos";
    public static String ContactoColumnaID = "_ID";
    public static String ContactoColumnaNombre = "Nombre";
    public static String ContactoColumnaApellido = "Apellido";
    public static String ContactoColumnaTelefono = "Telefono";
    public static String ContactoColumnaTipoTelefono = "TipoTelefono";
    public static String ContactoColumnaEmail = "Email";
    public static String ContactoColumnaTipoEmail = "TipoEmail";
    public static String ContactoColumnaDireccion = "Direccion";
    public static String ContactoColumnaFechaNacimiento = "FechaNacimiento";
    public static String ContactoColumnaNivelEstudios = "NivelEstudios";
    public static String ContactoColumnaIntereses = "Intereses";
    public static String ContactoColumnaRecibeInfo = "RecibeInfo";

    public ContactoOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ContactoCreacionTabla;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void abrir() { this.getWritableDatabase(); }

    public void cerrar() { this.close(); }


    public void insertarContacto(Contacto c) {
        ContentValues valores = new ContentValues();
        valores.put(ContactoColumnaNombre, c.getNombre());
        valores.put(ContactoColumnaApellido, c.getApellido());
        valores.put(ContactoColumnaTelefono, c.getTelefono());
        valores.put(ContactoColumnaTipoTelefono, c.getTipoTelefono());
        valores.put(ContactoColumnaEmail, c.getEmail());
        valores.put(ContactoColumnaTipoEmail, c.getTipoEmail());
        valores.put(ContactoColumnaDireccion, c.getDireccion());
        valores.put(ContactoColumnaFechaNacimiento, c.getFechaNacimiento());
        valores.put(ContactoColumnaNivelEstudios, c.getNivelEstudios());
        valores.put(ContactoColumnaIntereses, c.getIntereses());
        valores.put(ContactoColumnaRecibeInfo, c.getRecibeInfo() ? 1 : 0);
        this.getWritableDatabase().insert(ContactoTabla, null, valores);
    }
    public ArrayList<Contacto> getListadoContactos() {
        ArrayList<Contacto> listaContacto = new ArrayList<Contacto>();
        Cursor mcursor = null;


        mcursor = this.getReadableDatabase().query(ContactoTabla,
                new String[]{
                        ContactoColumnaID,
                        ContactoColumnaNombre,
                        ContactoColumnaApellido,
                        ContactoColumnaTelefono,
                        ContactoColumnaTipoTelefono,
                        ContactoColumnaEmail,
                        ContactoColumnaTipoEmail,
                        ContactoColumnaDireccion,
                        ContactoColumnaFechaNacimiento,
                        ContactoColumnaNivelEstudios,
                        ContactoColumnaIntereses,
                        ContactoColumnaRecibeInfo
                },
                null, null, null, null, null);

        if (mcursor.moveToFirst()) {
            do {
                Contacto c = new Contacto();
                c.setId(mcursor.getInt(0));
                c.setNombre(mcursor.getString(1));
                c.setApellido(mcursor.getString(2));
                c.setTelefono(mcursor.getString(3));
                c.setTipoTelefono(mcursor.getString(4));
                c.setEmail(mcursor.getString(5));
                c.setTipoEmail(mcursor.getString(6));
                c.setDireccion(mcursor.getString(7));
                c.setFechaNacimiento(mcursor.getString(8));
                c.setNivelEstudios(mcursor.getString(9));
                c.setIntereses(mcursor.getString(10));
                c.setRecibeInfo(mcursor.getInt(11) == 1);

                listaContacto.add(c);
            } while (mcursor.moveToNext());
        }

        mcursor.close();

        return listaContacto;
    }
}
