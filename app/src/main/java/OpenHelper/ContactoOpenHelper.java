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
}
