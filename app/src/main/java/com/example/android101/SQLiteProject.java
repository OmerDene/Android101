package com.example.android101;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class SQLiteProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_project);
        try {
            SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
            database.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY , name VARCHAR, age INTEGER)");
            //database.execSQL("INSERT   INTO musicians(name ,age ) VALUES ('James',50)");
           // database.execSQL("INSERT   INTO musicians(name ,age ) VALUES ('omer',20)");
            //database.execSQL("INSERT   INTO  musicians(name ,age) VALUES ('kemalettin' ,07)");
            database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'kemalettin'");
            database.execSQL("UPDATE musicians SET name = ' burak ' WHERE name = 'maho aga'");
            database.execSQL("DELETE FROM musicians WHERE id = 3");


            //Cursor cursor = database.rawQuery("SELECT * FROM musicians ",null);
            Cursor cursor = database.rawQuery("SELECT * FROM musicians ",null);
            //Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE id = 3 ",null);
            int nameIx = cursor.getColumnIndex("name");
            int ageIx = cursor.getColumnIndex("age");
            int idIx = cursor.getColumnIndex("id");
            while (cursor.moveToNext()) {
                System.out.println("Name: " +cursor.getString(nameIx));
                System.out.println("Age: " +cursor.getInt(ageIx));
                System.out.println("Id " + cursor.getInt(idIx));

            }
            cursor.close();

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

}
/* try and catch'te bir kod yazımını deniyoruz.Try kısmında kodu deneriz sonrasında hatayı catch içinde
yakalarız.ve hata yakalarsak catch icinde ne yapacagımızı yazıyoruz.catch kısmının icini bu sekilde
 yazarız burada görülen e yi biz kendimiz salladık herhangi bir harfte yazılabilirdi(Exception e).
 e.printStackTrace(); Bu ifadede hatanın detayını yazdırıyor.Burada hatalar alma sebebimiz android
 kodlarından cıkıp SQL kodlarıyla yazıyor olacak ve bu durumda hata aldıgımızda bize sölenmeme ihtimali var
 ve ek olarak kendimizde bir hata yapabiliriz.Biz veri tabanının SQLiteDatabase diyerek olustururuz.bu bir
 sınıftır ve bir veritabanı olusturdugumuzda bu sınıftan bir obje türeterek olusturuyoruz burada objemize
 database dedik.Yine bu veritabanını olustururken this referansını yukarıdaki gibi uygularız.this. ifadesinden sonra
 openOrCreateDatabase ifadesini yazarız ve bunun anlamı ya bu veritabanını ac ya da daha önce olusturulmadıysa olustur.
 Daha once olusturulmussa acıyor olusturulmamıssa kendi olusturuyor.Daha sonra bizden 3 tane şey istiyor parantez icinde
 1 data basenin ismi ,2 modu ve 3 imlec denilen bir ifade var bunları istiyor.İlk olarak ismi yazarken 'Musicians' gibi
 ilk harf büyük olmalı cunku bu bizim ana veri tabanımızdır.Yine aynı sekilde moduda büyük yazarız MODE seklinde sonrasında
 privateyi sectik.İfadenin son hali bu oldu : SQLiteDatabase database = this.openOrCreateDatabase("Musicians",MODE_PRIVATE,null);
 bu bize bir veri tabanı actı.Excel mantıgı ile düsüncek olursak biz su an excel dosyası olusturduk ama aynı zamanda
 bu excel dosyasına bir sheet acmamız lazım(sanırsam excelin icindeki hücreler) bunada tablo denir.onuda sole yaparız :
 database.execSQL(); ifadesi ile.Bu ifadeyi olusturduktan sonra artık java yazmıyoruz SQL kodu yazıyoruz.Ben burada
 muzisyenler olusturmak istiyorum ve bu muzisyenlerin ismini ve yasını giricem bunun icin , CREATE TABLE IF NOT EXISTS musicians
 yazıp daha sonra musicians(name VARCHAR, age INTEGER) yazıyoruz burada VARCHAR stringe denk geliyo sQl yaparken bu sekilde
 kullanma mecburiyeti var age nin yanındada INTEGER yazıyoruz yine sQl de bu sekilde int leri kullanma mecburiyeti var.
 */

/* Şimdide bir isim ve bir yas girip bunları okutcaz.Bir tabloaya deger kaydederken(excel mantıgı ile düsün)
İlk olarak database.execSQL(); yazarız ve parantez icine INSERT INTO yazarız insert yerlestirmek demektir.Bir seyin
icine yerlestir peki neyin ? musiciansın. musiciansın icine name ve age degerlerinin icine yerlestir , sonrada
yazacagımı VALUES diyip  hangi degerleri koyacagımı belirtirim.Neydi varchar yani string yazcam ilk valuesin icine
bunu tek tırnakla yaparım sonra yası yazcam bunu direk olarak yazarım VALUES ('James',50) seklinde.Bu asamaya kadar
jamesin ismini ve yasını kaydettik.Şimdide bunu geri almaya yani okumaya calıscaz.Okumak icin degeri bir Cursor denilen
imlece ihtiyac vardır.Cursor imlecini acıp obje ismi olarak yine cursor verdik ve sonrasında
Cursor cursor = database.rawQuery() yazarız Query bir sorgu yazmak veri tabanından sorgulama yapmak demek
rawQuery bize iki sey soruyor ilki sorgunun neye yapılacagı biz burda "SELECT * FROM musicians" diyerek her seyi sorgula dedik
(yıldız burda her sey demek oluyor) yani anlamı musicians sınıfındaki her seyi cek.İkinci ifade ise filtreleme
isteyip istemedigimizi soruyor ve null diyerek su an filtreleme istemedigimizi belirtiyoruz.
şimdide hangi sıra ve sütunlara gitmemiz gerektigini belirtcez.Bunun icin iki tane int olusturcaz(cunku yas ve
isim iki degisken yaptık) ilki bu sekilde int nameIx = cursor.getColumnIndex("name"); Burada bize namein kacıncı
sutunda oldunu belirtiyor bu yuzden int kullanıyoruz.ikinciside bole int ageIx = cursor.getColumnIndex("age"); ilkiyle aynı mantık
Sonrasında bir while loopu acıcam , while (cursor.moveToNext()) bu sekilde.Burada diyoruz ki cursor ilerleyebildigi
kadar ilerlesin tek tek gezsin bütün verileri ve işlem bittikten sonra cursoru kapatırız while loop koseli parantezlerinin hemen
dısında su sekilde cursor.close();

id INTEGER PRIMARY KEY ifadesi bize excelimizdeki verilerin kendi kendine sıraya girmesine daha dogrusu
id atanmasını saglıyor
1                          omer                               67
2                          mehmet                             41
3                          furkan                             19
4                          ......                             ..
5                          ......                             ..
 tarzında.
 Filtreleme yapmak icin WHERE kodunu kullanırız.ornegin SELECT * FROM musicians WHERE age > 40  ifadesinde
 yası 40 tan buyuk olanları cagırmıs oluruz.Bu sekilde istedigimiz seyleri WHERE ile filtreleyebiliriz.
 Guncelleme yapmak icin database.execSQL("UPDATE musicians SET age = 61 WHERE name = 'kemalettin'");bunu kullanabiliriz
 'UPDATE' ve 'SET' ifadeleri kullanılır.
 veri çıkarmak icin database.execSQL("DELETE FROM musicians WHERE id = 3"); kullanılır.
   Cursor cursor = database.rawQuery("SELECT * FROM musicians WHERE name LIKE 's%' ",null);gibi bir ifade
   sonu s ile biten kişileri cagırmak icin kullanılır.


 */