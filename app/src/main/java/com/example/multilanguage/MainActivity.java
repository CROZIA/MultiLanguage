package com.example.multilanguage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button cl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();

        setContentView(R.layout.activity_main);
        cl=(Button)findViewById(R.id.button_ChangeLanguage);
        cl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });
    }

    private void showChangeLanguageDialog() {
        final String[] listItems={"English","French","Vietnam"};
        AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose language ...");
        builder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                    {
                        setLocale("en");
                        recreate();
                        break;
                    }
                    case 1:
                    {
                        setLocale("fr");
                        recreate();
                        break;
                    }
                    case 2:
                    {
                        setLocale("vi");
                        recreate();
                        break;
                    }
                    default:dialogInterface.dismiss();
                }
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale=new Locale(lang);
        Locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor=getSharedPreferences("Setting", MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences pref=getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language=pref.getString("My_lang","");
        setLocale(language);

    }

}
