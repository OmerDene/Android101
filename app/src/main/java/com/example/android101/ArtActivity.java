package com.example.android101;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.android101.databinding.ActivityArtBinding;
import com.example.android101.databinding.ActivityArtBookBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;

public class ArtActivity extends AppCompatActivity {
    private ActivityArtBinding binding;
    ActivityResultLauncher<Intent> activityResultLauncher;
    ActivityResultLauncher<String> permissionLauncher;
    Bitmap selectedImage;
    SQLiteDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityArtBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        registerLauncher();
        database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
        Intent intent = getIntent();
        String info = intent.getStringExtra("info");
        if(info.equals("new")) {
            binding.editTextTextPersonName.setText("");
            binding.artistText.setText("");
            binding.yearText.setText("");
            binding.button2.setVisibility(View.VISIBLE);
            binding.selectImage.setImageResource(R.drawable.fotosec);

        }else {
            int artId = intent.getIntExtra("artId",0);
            binding.button2.setVisibility(View.INVISIBLE);
            try {
                Cursor cursor = database.rawQuery("SELECT * FROM arts WHERE id = ? " ,new String[] {String.valueOf(artId)});
                int artNameIx = cursor.getColumnIndex("artname");
                int painterNameIx = cursor.getColumnIndex("paintername");
                int yearIx = cursor.getColumnIndex("year");
                int imageIx = cursor.getColumnIndex("image");
                while (cursor.moveToNext()) {
                    binding.editTextTextPersonName.setText(cursor.getString(artNameIx));
                    binding.yearText.setText(cursor.getString(yearIx));
                    binding.artistText.setText(cursor.getString(painterNameIx));
                    byte[] bytes = cursor.getBlob(imageIx);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes , 0,bytes.length);
                    binding.selectImage.setImageBitmap(bitmap);

                }
                cursor.close();


            }catch (Exception e) {
                e.printStackTrace();
            }
        }



    }
    public void save(View view) {
        String name = binding.editTextTextPersonName.getText().toString();
        String artistName = binding.artistText.getText().toString();
        String year = binding.yearText.getText().toString();
        Bitmap smallImage = makeSmallerImage(selectedImage ,300);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        smallImage.compress(Bitmap.CompressFormat.PNG,50,outputStream);
        byte[] byteArray =outputStream.toByteArray();
        try {
            database = this.openOrCreateDatabase("Arts",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY, artname VARCHAR , paintername VARCHAR, year VARCHAR, image BLOB)");
            String sqlString = "INSERT INTO arts (artname, paintername, year, image) VALUES(?, ?, ?, ?)";
            SQLiteStatement sqLiteStatement = database.compileStatement(sqlString);
            sqLiteStatement.bindString(1,name);
            sqLiteStatement.bindString(2,artistName);
            sqLiteStatement.bindString(3,year);
            sqLiteStatement.bindBlob(4,byteArray);
            sqLiteStatement.execute();



        } catch (Exception e){
            e.printStackTrace();

        }

        Intent intent = new Intent(ArtActivity.this,ArtBook.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //burada sunu yapt??k ,buraya kadarki tum aktivetleri kapat
        //sadece benim cag??rd??g??m aktiviteye git.addFlags kodu bu ise yarad??.
        startActivity(intent);


    }

    public Bitmap makeSmallerImage(Bitmap image,int maximumSize ){
        int width = image.getWidth();
        int height = image.getHeight();
        float bitmapRatio = (float) width/ (float) height;
        if(bitmapRatio > 1 ){
            // lanscape image
            width = maximumSize;
            height = (int) (width /bitmapRatio);

        }else{
            //portrait image
            height = maximumSize;
            width = (int) (height * bitmapRatio);



        }

        return image.createScaledBitmap(image,width,height,true);
    }

    public void selectImage(View view) {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Snackbar.make(view,"Permission needed for gallery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);

                    }
                }).show();

            }else{
                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE);


            }

        }else{
            Intent intentToGalery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(intentToGalery);

        }


    }


    private void registerLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == RESULT_OK ) {
                    Intent intentFromResult = result.getData();
                    if(intentFromResult != null) {
                       Uri imageData = intentFromResult.getData();
                       try {
                           if(Build.VERSION.SDK_INT >= 28) {
                               ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(),imageData);
                               selectedImage = ImageDecoder.decodeBitmap(source);
                               binding.selectImage.setImageBitmap(selectedImage);
                           }else{
                               selectedImage = MediaStore.Images.Media.getBitmap(ArtActivity.this.getContentResolver(),imageData);
                               binding.selectImage.setImageBitmap(selectedImage);
                           }


                       } catch (Exception e){
                           e.printStackTrace();

                       }
                    }

                }

            }
        });
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result ) {
                    Intent intentToGalery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activityResultLauncher.launch(intentToGalery);


                }
                else {
                    Toast.makeText(ArtActivity.this ,"Permission needed!" ,Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
/* Projenin amac?? insanlar??n cektigi fotograf?? uygulamaya atabilmeleri ve kaydedebilmeleri
Burada ilk olarak cekilen fotonun uygulamaya kaydedilebilmesi icin projenin galeriye gitmeye izin almas??
gerekmektedir.Bunu yapt??g??m??z k??s??m selectImage metodunun hemen alt?? cunku fotograf?? secebilmek icin
kullan??c?? ilk buraya t??klayacak ve bizde ondan galeriye gitme izni isticez.If kodu ile yine basl??yoruz ve
ContextCompat.checkSelfPermission bu kodu cag??r??yoruz.Asl??nda sadece checkSelfPermission kodu yeter normalde ama
baz?? kullan??c??lar kulland??g?? s??r??mler eski oldugundan onlardan izin istemeye gerek kalm??yo bu eski s??r??mlerden izin
istememek icinde ContextCompat ifadesini if kodunun parantez icine ekleriz.checkSelfPermission bunu yazd??ktan sonra
parantez icinde 2 sey istiyor bizden ilki refarans contextinin nere olacag??(bu aktivite olcak) ve hangi izni kontrol
edecegimizi soruyor.??lk bunu yazar??z Manifest.permission bunun ard??ndan  READ_EXTERNAL_STORAGE bunu seceriz.
Su an amac??m??z galerideki fotograflara erismek icin izin istedigimizden dolay?? READ_EXTERNAL_STORAGE iznini sorduk
baska izinlerde baska kodlar cagr??l??r.ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE buraya kadar
Read external storadge izni varm?? bunu kontrol et diyoruz.Sonras??ndan konda sole bir ifade ekliyoruz != PackageManager.PERMISSION_GRANTED
bu son eklenen k??s??m != haric izin verildi demek ama != konunca kodun bas??na izin verilmemi?? anlam?? c??k??yor t??m kod hatt?? boyunca
Buraya kadarki seneryoda izinin verilmedigi durumda nolcak onu yazd??k(bu k??sm??n hemen alt??da tekrardan izin vermesi gerektigini
kullan??c??ya hat??rlatcaz cunku izin vermezse uygulamam??za fotograf?? yukleyemez.Asl??nda bu k??s??mda kullan??c??ya neden izni
vermesi gerektigini mant??g??n?? anlatcaz, sole bisi dicez eger ki izin vermezseniz galeriye gidemem sizde bundan dolay??
fotonuzu yukleyemezsiniz o yuzden izin vermelisiniz simdi size tekrar soruyorum izin veriyor musunuz ? dicez.
Bunu yapmak icinde if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE) bunu yazar??z
Bu k??s??mdada kullan??c??ya izni almam??n mant??ksal gerekcesi g??sterilmesi gerekirse ne yapal??m demis olduk.
Ve su uyar??y?? verdik   Snackbar.make(view,"Permission needed for galery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
Burdak view bizim referans??m??z olur ayn?? this gibi islev g??r??r.yan??ndaki permission needed for galery asag??da c??kan toast mesaj??m??z
ve en sondaki length ??ndef??n??te ??se kullan??c?? herhangi bir sey yapana kadar toast mesaj?? gostermeye devam et demek oluyor.
Hemen arkas??ndan gelen setAction ise allta c??kan bir Snackbar uyar?? mesaj?? bunu kapatmad??g??n??z ya da izin vermediginiz surece
gormeye devam edersiniz ve burda izni neden istedigimize dair kullan??c??ya sebep g??steriririz.Ve buna t??kland??g??nda ne olacag??n??
onClickListener ile tan??mlar??z.
simdide galeriye gidip fotograf?? alabilmek yani galeriden fotoyu alabilmek icin gereken kodu yazd??k daha dogrusu su an sadece baglad??k
Intent ??ntenToGalery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
Galeri fotosu icin ACTION_PICK haz??r kodu secilir baska bir yere gitmek isteseydik ornegin muzikler gibi
baska bir secenegi isaretlerdik.Ama su anda bu intenti nas??l kullan??caz , galeriyi gidip g??rseli sectikten  sonra neler olacak
bunlar??da belirtmemiz gerekmektedir.Yani su an bir konu daha cozulmeli ,galeriye gidip bisey secilince napacaz
Burada ActivityResultLauncher kullan??r??z bu kod kullan??c?? galeri gibi bi yere gitmemize izin verdiginde ne olacag??n??
yazabilmemiz icin kullan??lmaktad??r.
ActivityResultLauncher<Intent> activityResultLauncher; ,   ActivityResultLauncher<String> permissionLauncher; iki tane global degisken
tan??mlad??k ilki yani ActivityResultLauncher<Intent> activityResultLauncher; aktivitede galeriye gidip geri gelmek icin
ActivityResultLauncher<String> permissionLauncher; ikincisi ise izin istemek icin kullan??l??r.String getirmesinin sebebi
izinlerde string ile ugras??yor olmam??zd??r.




 */