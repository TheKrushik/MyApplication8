package info.krushik.android.myapplication8;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.XMLFormatter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick(View v) {
        SharedPreferences preferences;

        switch (v.getId()) {
            case R.id.button:
                preferences = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("Text", "I am preference");
                editor.commit();
                break;
            case R.id.button2:
                preferences = getPreferences(MODE_PRIVATE);
                Toast.makeText(this, preferences.getString("Text", ""), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button3:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.button4:
                preferences = PreferenceManager.getDefaultSharedPreferences(this);
                Toast.makeText(this, preferences.getString("pref2", ""), Toast.LENGTH_SHORT).show();
                break;
            case R.id.button5:

                break;
            case R.id.button7:

                break;
            case R.id.button8:
                saveInternalFile("MyFile.txt", "Internal file data");
                break;
            case R.id.button9:
                String text = readInternalFile("MyFile.txt");
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button10:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                    try{
                        File folder = Environment.getExternalStorageDirectory();
                        folder = new File(folder.getAbsolutePath() + "/MyFolder");

                        if (!folder.exists()){
                            folder.mkdirs();
                        }
                        saveExternalFile(folder, "MyFile.txt", "External file data");
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.button11:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

                    File folder = Environment.getExternalStorageDirectory();
                    folder = new File(folder.getAbsolutePath() + "/MyFolder");

                    if (folder.exists()){
                        String text2 = readExternalFile(folder, "MyFile.txt");
                        Toast.makeText(this, text2, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.button12:
                Student student = new Student("Ivan", "Ivanov", 22);

                Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();
                String json = gson.toJson(student);

                saveInternalFile("Student.txt", json);
                break;
            case R.id.button13:
                String json2 = readInternalFile("Student.txt");
                Gson gson2 = new GsonBuilder().setDateFormat("dd-MM-yyyy HH:mm:ss").create();

                Student student2 = gson2.fromJson(json2, Student.class);
                Toast.makeText(this, student2.FirstName, Toast.LENGTH_SHORT).show();
                break;
            case R.id.button14:
                Student student0 = new Student("Ivan0", "Ivanov0", 22);

                File xmlFile = new File(getFilesDir().getPath() + "/Student.xml");
                try
                {
                    Serializer serializer = new Persister();
                    serializer.write(student0, xmlFile);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.button15:
                xmlFile = new File(getFilesDir().getPath() + "/Student.xml");
                if (xmlFile.exists())
                {
                    try
                    {
                        Serializer serializer = new Persister();
                        serializer.read(Student.class, xmlFile);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

                break;
        }
    }

    private void saveInternalFile(String fileName, String data) {
        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            openFileOutput(fileName, Context.MODE_PRIVATE)));

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readInternalFile(String fileName) {
        try {
            StringBuilder builder = new StringBuilder();

            InputStream inputStream = openFileInput(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            reader.close();

            return builder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void saveExternalFile(File folder, String fileName, String data) {
        File file = new File(folder, fileName);

        try {
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(file), "UTF8"));

            writer.write(data);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String readExternalFile(File folder, String fileName) {
        File file = new File(folder, fileName);
        try {
            if (file.exists()) {
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new FileReader(file));

                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }

                reader.close();

                return builder.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
