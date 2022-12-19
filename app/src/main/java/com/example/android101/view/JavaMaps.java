package com.example.android101.view;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.room.Room;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.android101.R;
import com.example.android101.model.Place;
import com.example.android101.roomdb.PlaceDao;
import com.example.android101.roomdb.PlaceDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.android101.databinding.ActivityJavaMapsBinding;
import com.google.android.material.snackbar.Snackbar;

public class JavaMaps extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private ActivityJavaMapsBinding binding;
    ActivityResultLauncher<String> permissionLauncher;
    LocationManager locationManager;
    LocationListener locationListener;
    SharedPreferences sharedPreferences;
    boolean info;
    PlaceDatabase db;
    PlaceDao placeDao;
    Double selectedLatitude;
    Double selectedLongitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityJavaMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);
        registerLauncher();
        sharedPreferences = this.getSharedPreferences("com.example.android101",MODE_PRIVATE);
        info = false;
        db = Room.databaseBuilder(getApplicationContext(),PlaceDatabase.class,"Places").build();
                //.allowMainThreadQueries()(burada bırakıp uygulamanın kitlenmesinin onune gecebilirdik ufak bir veri
                // kaydediyor olsaydık.
        placeDao = db.placeDao();
        selectedLatitude = 0.0;
        selectedLongitude = 0.0;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        binding.saveButton.setEnabled(false);
         locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
         locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                info = sharedPreferences.getBoolean("info",false);
                if(info == false) {
                    LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLocation,15));
                    sharedPreferences.edit().putBoolean("info",true).apply();
                }


            }
        };
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
                Snackbar.make(binding.getRoot(),"Permission Needed For Maps",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission Please", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);

                    }
                }).show();

            }else {
                //request permission
                permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);


            }

        }else{
            //request granted
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastLocation != null) {
                LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15));
            }

        }
        mMap.setMyLocationEnabled(true);

    }

    private  void registerLauncher() {
        permissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
            @Override
            public void onActivityResult(Boolean result) {
                if(result) {
                    //permission granted
                    if(ContextCompat.checkSelfPermission(JavaMaps.this,Manifest.permission.ACCESS_FINE_LOCATION) ==PackageManager.PERMISSION_GRANTED){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
                        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(lastLocation != null) {
                            LatLng lastUserLocation = new LatLng(lastLocation.getLatitude(),lastLocation.getLongitude());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastUserLocation,15));
                        }
                    }


                }else {
                    //permission denied
                    Toast.makeText(JavaMaps.this,"Permission Needed!" ,Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    @Override
    public void onMapLongClick(@NonNull LatLng latLng) {
        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(latLng));

        selectedLatitude = latLng.latitude;
        selectedLongitude = latLng.longitude;
        binding.saveButton.setEnabled(true);



    }
    public void save(View view) {
        Place place = new Place(binding.placeNameText.getText().toString(),selectedLatitude,selectedLongitude);
        placeDao.insert(place);




    }
    public void delete(View view) {

    }
}
/* Oncelikle eyfel kulesinin konumunu yazarak basladık uygulamamıza ,bunun icin LatLng sınıfını kullanabiliriz
mMap.addMarker(new MarkerOptions().position(effiel).title("Effiel Tower")); bu kod blogunda mapimize bir marker
koyduk ve uygulama acıldıgında artık eyfel kulesinin üstünde otomatik markerımız gozugecektır , burada positionu
effiel degiskenine esitledik cunku markerımızın burda durmasını ıstıyoruz ,tittle yazma amacımız nerede oldugumuzu
bir metin olarak göstermek icindi.
mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(effiel,15)); bunu yazama sebebimiz harita ilk acıldıgında ne kadar
yakınlıkta yada uzaklıkta gostermek istiyoruz onu belirlemek icin bu deger en fazla 25 e kadar gidiyor.25 secersek harita
en yakın zoomu bize gösterir 1 secersek en uzak zoomu gosterir.
LocationManager android işletim sisteminin konum servislerine erişimimizi saglıyor.Bu konum servisini kullanarak
kullanıcının konumu ile alakalı işleri yapabiliyoruz.
LocationManager locationManager = this.getSystemService(Context.LOCATION_SERVICE); kodu su sekilde bıraktıgımızda bir hata alırız
hatamız kodun ne dondurecegini tam anlamamıs olmasından kaynaklı oldugu gibi birsey(???) bunu duzeltmek icin casting yapıyoruz
Casting yaparken koda sunu diyoruz ben eminim bu locationmanager dondurecektir ,yapılma seklide sole
LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE); bu sekilde
LocationManager locationManager = this.getSystemService(Context.LOCATION_SERVICE); bu kod blogunu
LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE); buna ceviririz ki
hata almayalım.
Biz konumla ilgili seyleri yaparken 2 tane sınıftan faydalanıyoruz,ilki locationManager ikincisi LocationListener
LocationListener' da LocationManagerdaki konum degisikliklerininin uyarısını alırız.Listenerıda ekledigimizde
bize otomatik bir method olusturdu , onLocationChanged metodu(Listener sınıfının hemen altında) ,Bu metod konum degistiginde
ne yapılacak onla ilgilidir.Konum yoneticisi yani LocationManager konum degisiyomu degismiyo mu dinliyo arka plandan bu calıstırılarak
bize iletiliyor LocaitonListenerda bu degisiklikleri dinliyor ve bize onLocationChanged metodunu veriyor bizde bu metod icinde
degisen konumla ilgili yapmak istedigim islemleri yapabiliyorum.Sonra onLocationChangedin altına System.out.println("location" + location.toString());
yazarız ve yeni gelen konumu almaya calısırız ama islem burda bitmez.
ilk olarak locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener); kodunu yazmakla baslarız.
Bu kod kullanıcı konum almamıza izin verdiginde  konuma gidip onu kontrol edebilmemize yarar.GPS PROVİDERı sectik cünkü
verileri gps ile almak istiyoruz iki tane 0 degeri var bunlardan ilki verinin kac saniyede bir kontrol edilecegi ve ikincisi
kac metrede bir verinin yani konumun kontrol edilecegi bu 0 0 verilerini uygulamaya gore degisik yazabiliriz , ornegin
bir kosu uygulaması yapıyorsak bu veriler anlık olsa daha iyi olur ama bununda dezavantajı cok pil yemesi olur yani eger
veriler anlık olarak lazım olmayan bir uygulama yapıyorsak ornegin 15 sn bir veya 1000 metrede bir konumu guncleyyebiliriz bu
cihazda daha düsük pil harcamasına olanak verir , aynı kod blogunda son olarak listenerı cagırdık buda LocationManagerdaki
degisiklikleri dinleyip bize geri dönüs saglıyor.
şimdi izinleri istiyoruz
if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {}
bu kod blogunun altına kullanıcı izin evet derse nolcak hayır derse nolcak bunları tanımlıyoruz
bu kod blogunda != diyerek egerki kullanıcı izin vermemisse demis oluyoruz ContextCompat kısmı bütün apiları kontrol edebilmek icin ,
checkSelfPermission izinlerde kullanılır ,Manifest.permission.ACCESS_FINE_LOCATION kısmı hangi izni istedigimizi , Manifest.permission.ACCESS_FINE_LOCATION
bu kısımda artık en son izin verilmemişse demektir.Ve bunun altına izin verilmemisse ne yapcaz onu yazıyoruz ,Baslangıc olarak bir SnackBar olustururuz
ama bundan once bu izni kullanıcıya rasyonel ederek yani neden izin istedigimizin mantıgını anlatan kod blogu ekleriz hemen ardına Snackbarı koyarız
Snackbar.make(binding.getRoot(),"Permission Needed For Maps",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission Please", new View.OnClickListener()
Bu altta bir snackbar acar ve neden izni istedigimizi kullanıcıya mantıklı bir sekilde belirtir.

LatLng effiel = new LatLng(48.8559413,2.2930037);
        mMap.addMarker(new MarkerOptions().position(effiel).title("sogan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(effiel,15));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(effiel));
uygulamayı olustururken ilk bu kodları cagırdık.su an bunlara ihtiyac kalmadı.






 */