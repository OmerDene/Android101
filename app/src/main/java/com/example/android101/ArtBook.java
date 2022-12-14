package com.example.android101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android101.databinding.ActivityArtBookBinding;

import java.util.ArrayList;

public class ArtBook extends AppCompatActivity {
    private ActivityArtBookBinding binding;
    ArrayList<Art> artArrayList;
    ArtAdapter artAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtBookBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        artArrayList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        artAdapter = new ArtAdapter(artArrayList);
        binding.recyclerView.setAdapter(artAdapter);
        getData();

    }
    private void getData() {
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM arts",null);
            int nameIx = cursor.getColumnIndex("artname");
            int idIx = cursor.getColumnIndex("id");
            while(cursor.moveToNext()) {
                String name = cursor.getString(nameIx);
                int id = cursor.getInt(idIx);
                Art art = new Art(name,id);
                artArrayList.add(art);
                artAdapter.notifyDataSetChanged();

            }
            cursor.close();


        } catch (Exception e) {
            e.printStackTrace();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.art_menu , menu);
        return super.onCreateOptionsMenu(menu);

        
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_art) {
            Intent intent = new Intent(this,ArtActivity.class);
            intent.putExtra("info","new");
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
/* ??lk olarak xmlde manuel olarak olusturdugumuz menuyu Mainactivity k??sm??na baglayarak basl??yoruz.
Bunun icin cag??rmam??z gereken 2 adet method var ilki , public boolean onCreateOptionsMenu bu metodu
cag??rd??g??m??zda kendi otomatik Override ediyo.Layoutu yani menuyu koda baglamak icin inflate kullan??r??z.
Menu icin ise menuye ozel bir inflater var ismide MenuInflater.MenuInflater?? olusturduktan sonra getMenu??nflater
kodunu yazar??z ve ard??ndan alt sat??ra gec??p menuInflater objemizi  inflate ile cag??r??r ve nereye baglamak istedigimizi
parantez icinde belirtiriz.??kinci cag??rcag??m??z method ise menuye t??kland??g??nda ne olacag??n?? tan??mlad??g??m??z methot oluyor.
Burada birden fazla menu olabilirdi ama biz bu app projesi icin sadece bir tane menu tan??mlad??k ve onu kontrol etmek icin
if'i kullanabiliriz.if(item.getItemId() == R.id.add_art) bunu yaz??p bu secilen k??s??mdan ne yapmak istedigimizi berliticez simdide.
Eger birden fazla menu olsayd?? else ile tekrardan ==R.id.add ....... k??s??mlar??n??n alt??nda ne yapacag??m??z?? tan??mlayabilirdik.
??f kontrolunun alt??na su an bu menuyu ArtActivity ekran??na baglamas??n?? daha dogrusu t??klan??nca oraya yonlendirmesini yaz??caz
Intent ile.


 */