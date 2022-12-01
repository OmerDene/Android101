package com.example.android101;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.android101.databinding.ActivityArtBookBinding;

public class ArtBook extends AppCompatActivity {
    private ActivityArtBookBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtBookBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

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
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}
/* İlk olarak xmlde manuel olarak olusturdugumuz menuyu Mainactivity kısmına baglayarak baslıyoruz.
Bunun icin cagırmamız gereken 2 adet method var ilki , public boolean onCreateOptionsMenu bu metodu
cagırdıgımızda kendi otomatik Override ediyo.Layoutu yani menuyu koda baglamak icin inflate kullanırız.
Menu icin ise menuye ozel bir inflater var ismide MenuInflater.MenuInflaterı olusturduktan sonra getMenuınflater
kodunu yazarız ve ardından alt satıra gecıp menuInflater objemizi  inflate ile cagırır ve nereye baglamak istedigimizi
parantez icinde belirtiriz.İkinci cagırcagımız method ise menuye tıklandıgında ne olacagını tanımladıgımız methot oluyor.
Burada birden fazla menu olabilirdi ama biz bu app projesi icin sadece bir tane menu tanımladık ve onu kontrol etmek icin
if'i kullanabiliriz.if(item.getItemId() == R.id.add_art) bunu yazıp bu secilen kısımdan ne yapmak istedigimizi berliticez simdide.
Eger birden fazla menu olsaydı else ile tekrardan ==R.id.add ....... kısımlarının altında ne yapacagımızı tanımlayabilirdik.
İf kontrolunun altına su an bu menuyu ArtActivity ekranına baglamasını daha dogrusu tıklanınca oraya yonlendirmesini yazıcaz
Intent ile.


 */