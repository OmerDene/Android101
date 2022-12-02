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
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
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
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //burada sunu yaptık ,buraya kadarki tum aktivetleri kapat
        //sadece benim cagırdıgım aktiviteye git.addFlags kodu bu ise yaradı.
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
/* Projenin amacı insanların cektigi fotografı uygulamaya atabilmeleri ve kaydedebilmeleri
Burada ilk olarak cekilen fotonun uygulamaya kaydedilebilmesi icin projenin galeriye gitmeye izin alması
gerekmektedir.Bunu yaptıgımız kısım selectImage metodunun hemen altı cunku fotografı secebilmek icin
kullanıcı ilk buraya tıklayacak ve bizde ondan galeriye gitme izni isticez.If kodu ile yine baslıyoruz ve
ContextCompat.checkSelfPermission bu kodu cagırıyoruz.Aslında sadece checkSelfPermission kodu yeter normalde ama
bazı kullanıcılar kullandıgı sürümler eski oldugundan onlardan izin istemeye gerek kalmıyo bu eski sürümlerden izin
istememek icinde ContextCompat ifadesini if kodunun parantez icine ekleriz.checkSelfPermission bunu yazdıktan sonra
parantez icinde 2 sey istiyor bizden ilki refarans contextinin nere olacagı(bu aktivite olcak) ve hangi izni kontrol
edecegimizi soruyor.İlk bunu yazarız Manifest.permission bunun ardından  READ_EXTERNAL_STORAGE bunu seceriz.
Su an amacımız galerideki fotograflara erismek icin izin istedigimizden dolayı READ_EXTERNAL_STORAGE iznini sorduk
baska izinlerde baska kodlar cagrılır.ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE buraya kadar
Read external storadge izni varmı bunu kontrol et diyoruz.Sonrasından konda sole bir ifade ekliyoruz != PackageManager.PERMISSION_GRANTED
bu son eklenen kısım != haric izin verildi demek ama != konunca kodun basına izin verilmemiş anlamı cıkıyor tüm kod hattı boyunca
Buraya kadarki seneryoda izinin verilmedigi durumda nolcak onu yazdık(bu kısmın hemen altıda tekrardan izin vermesi gerektigini
kullanıcıya hatırlatcaz cunku izin vermezse uygulamamıza fotografı yukleyemez.Aslında bu kısımda kullanıcıya neden izni
vermesi gerektigini mantıgını anlatcaz, sole bisi dicez eger ki izin vermezseniz galeriye gidemem sizde bundan dolayı
fotonuzu yukleyemezsiniz o yuzden izin vermelisiniz simdi size tekrar soruyorum izin veriyor musunuz ? dicez.
Bunu yapmak icinde if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_EXTERNAL_STORAGE) bunu yazarız
Bu kısımdada kullanıcıya izni almamın mantıksal gerekcesi gösterilmesi gerekirse ne yapalım demis olduk.
Ve su uyarıyı verdik   Snackbar.make(view,"Permission needed for galery",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission", new View.OnClickListener() {
Burdak view bizim referansımız olur aynı this gibi islev görür.yanındaki permission needed for galery asagıda cıkan toast mesajımız
ve en sondaki length ındefınıte ıse kullanıcı herhangi bir sey yapana kadar toast mesajı gostermeye devam et demek oluyor.
Hemen arkasından gelen setAction ise allta cıkan bir Snackbar uyarı mesajı bunu kapatmadıgınız ya da izin vermediginiz surece
gormeye devam edersiniz ve burda izni neden istedigimize dair kullanıcıya sebep gösteriririz.Ve buna tıklandıgında ne olacagını
onClickListener ile tanımlarız.
simdide galeriye gidip fotografı alabilmek yani galeriden fotoyu alabilmek icin gereken kodu yazdık daha dogrusu su an sadece bagladık
Intent ıntenToGalery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
Galeri fotosu icin ACTION_PICK hazır kodu secilir baska bir yere gitmek isteseydik ornegin muzikler gibi
baska bir secenegi isaretlerdik.Ama su anda bu intenti nasıl kullanıcaz , galeriyi gidip görseli sectikten  sonra neler olacak
bunlarıda belirtmemiz gerekmektedir.Yani su an bir konu daha cozulmeli ,galeriye gidip bisey secilince napacaz
Burada ActivityResultLauncher kullanırız bu kod kullanıcı galeri gibi bi yere gitmemize izin verdiginde ne olacagını
yazabilmemiz icin kullanılmaktadır.
ActivityResultLauncher<Intent> activityResultLauncher; ,   ActivityResultLauncher<String> permissionLauncher; iki tane global degisken
tanımladık ilki yani ActivityResultLauncher<Intent> activityResultLauncher; aktivitede galeriye gidip geri gelmek icin
ActivityResultLauncher<String> permissionLauncher; ikincisi ise izin istemek icin kullanılır.String getirmesinin sebebi
izinlerde string ile ugrasıyor olmamızdır.




 */