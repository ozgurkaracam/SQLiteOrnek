package com.example.sqliternek;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button buttonEkle;
    EditText editTextTurkce,editTextIngilizce;
    ArrayList<Kelimeler> kelimelers;
    KeliemelerDao kd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        kd=new KeliemelerDao(new Database(this));

        buttonEkle=findViewById(R.id.buttonEkle);
        editTextIngilizce=findViewById(R.id.editTextIngilizce);
        editTextTurkce=findViewById(R.id.editTextTurkce);
        recyclerView=findViewById(R.id.recycleView);
        recycleprepare();


        buttonEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kd.kelimeEkle(editTextTurkce.getText().toString(),editTextIngilizce.getText().toString());
                recycleprepare();
            }
        });

    }

    private void recycleprepare(){
        kelimelers=kd.TumKelimeler();
        recyclerView.setAdapter(new MyAdapter(this,kelimelers));
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }




}
