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
import android.content.Intent;
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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

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
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    Place selectedPlace;




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
                //.allowMainThreadQueries()(burada b??rak??p uygulaman??n kitlenmesinin onune gecebilirdik ufak bir veri
                // kaydediyor olsayd??k.
        placeDao = db.placeDao();
        selectedLatitude = 0.0;
        selectedLongitude = 0.0;
        binding.saveButton.setEnabled(false);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        Intent intent = getIntent();
        String intentInfo = intent.getStringExtra("info");
        if(intentInfo.equals("new")) {
            binding.saveButton.setVisibility(View.VISIBLE);
            binding.deleteButton.setVisibility(View.GONE);
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


        }else{
            mMap.clear();
            selectedPlace = (Place) intent.getSerializableExtra("place");
            LatLng latLng = new LatLng(selectedPlace.latitude,selectedPlace.longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(selectedPlace.name));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
            binding.placeNameText.setText(selectedPlace.name);
            binding.saveButton.setVisibility(View.GONE);
            binding.deleteButton.setVisibility(View.VISIBLE);

        }






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
        compositeDisposable.add(placeDao.insert(place)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(JavaMaps.this::handleResponse)
                // 3 cesit thread var main , default ve network database(io) threadi biz
                // io threadi ile cal??st??k burada.


        );





    }
    private void handleResponse() {
        Intent intent = new Intent(JavaMaps.this,JavaMapsMain.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);


    }
    public void delete(View view) {
        if(selectedPlace != null) {
            compositeDisposable.add(placeDao.delete(selectedPlace)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(JavaMaps.this::handleResponse)


        );

        }


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }



}
/* Oncelikle eyfel kulesinin konumunu yazarak baslad??k uygulamam??za ,bunun icin LatLng s??n??f??n?? kullanabiliriz
mMap.addMarker(new MarkerOptions().position(effiel).title("Effiel Tower")); bu kod blogunda mapimize bir marker
koyduk ve uygulama ac??ld??g??nda art??k eyfel kulesinin ??st??nde otomatik marker??m??z gozugecekt??r , burada positionu
effiel degiskenine esitledik cunku marker??m??z??n burda durmas??n?? ??st??yoruz ,tittle yazma amac??m??z nerede oldugumuzu
bir metin olarak g??stermek icindi.
mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(effiel,15)); bunu yazama sebebimiz harita ilk ac??ld??g??nda ne kadar
yak??nl??kta yada uzakl??kta gostermek istiyoruz onu belirlemek icin bu deger en fazla 25 e kadar gidiyor.25 secersek harita
en yak??n zoomu bize g??sterir 1 secersek en uzak zoomu gosterir.
LocationManager android i??letim sisteminin konum servislerine eri??imimizi sagl??yor.Bu konum servisini kullanarak
kullan??c??n??n konumu ile alakal?? i??leri yapabiliyoruz.
LocationManager locationManager = this.getSystemService(Context.LOCATION_SERVICE); kodu su sekilde b??rakt??g??m??zda bir hata al??r??z
hatam??z kodun ne dondurecegini tam anlamam??s olmas??ndan kaynakl?? oldugu gibi birsey(???) bunu duzeltmek icin casting yap??yoruz
Casting yaparken koda sunu diyoruz ben eminim bu locationmanager dondurecektir ,yap??lma seklide sole
LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE); bu sekilde
LocationManager locationManager = this.getSystemService(Context.LOCATION_SERVICE); bu kod blogunu
LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE); buna ceviririz ki
hata almayal??m.
Biz konumla ilgili seyleri yaparken 2 tane s??n??ftan faydalan??yoruz,ilki locationManager ikincisi LocationListener
LocationListener' da LocationManagerdaki konum degisikliklerininin uyar??s??n?? al??r??z.Listener??da ekledigimizde
bize otomatik bir method olusturdu , onLocationChanged metodu(Listener s??n??f??n??n hemen alt??nda) ,Bu metod konum degistiginde
ne yap??lacak onla ilgilidir.Konum yoneticisi yani LocationManager konum degisiyomu degismiyo mu dinliyo arka plandan bu cal??st??r??larak
bize iletiliyor LocaitonListenerda bu degisiklikleri dinliyor ve bize onLocationChanged metodunu veriyor bizde bu metod icinde
degisen konumla ilgili yapmak istedigim islemleri yapabiliyorum.Sonra onLocationChangedin alt??na System.out.println("location" + location.toString());
yazar??z ve yeni gelen konumu almaya cal??s??r??z ama islem burda bitmez.
ilk olarak locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener); kodunu yazmakla baslar??z.
Bu kod kullan??c?? konum almam??za izin verdiginde  konuma gidip onu kontrol edebilmemize yarar.GPS PROV??DER?? sectik c??nk??
verileri gps ile almak istiyoruz iki tane 0 degeri var bunlardan ilki verinin kac saniyede bir kontrol edilecegi ve ikincisi
kac metrede bir verinin yani konumun kontrol edilecegi bu 0 0 verilerini uygulamaya gore degisik yazabiliriz , ornegin
bir kosu uygulamas?? yap??yorsak bu veriler anl??k olsa daha iyi olur ama bununda dezavantaj?? cok pil yemesi olur yani eger
veriler anl??k olarak laz??m olmayan bir uygulama yap??yorsak ornegin 15 sn bir veya 1000 metrede bir konumu guncleyyebiliriz bu
cihazda daha d??s??k pil harcamas??na olanak verir , ayn?? kod blogunda son olarak listener?? cag??rd??k buda LocationManagerdaki
degisiklikleri dinleyip bize geri d??n??s sagl??yor.
??imdi izinleri istiyoruz
if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {}
bu kod blogunun alt??na kullan??c?? izin evet derse nolcak hay??r derse nolcak bunlar?? tan??ml??yoruz
bu kod blogunda != diyerek egerki kullan??c?? izin vermemisse demis oluyoruz ContextCompat k??sm?? b??t??n apilar?? kontrol edebilmek icin ,
checkSelfPermission izinlerde kullan??l??r ,Manifest.permission.ACCESS_FINE_LOCATION k??sm?? hangi izni istedigimizi , Manifest.permission.ACCESS_FINE_LOCATION
bu k??s??mda art??k en son izin verilmemi??se demektir.Ve bunun alt??na izin verilmemisse ne yapcaz onu yaz??yoruz ,Baslang??c olarak bir SnackBar olustururuz
ama bundan once bu izni kullan??c??ya rasyonel ederek yani neden izin istedigimizin mant??g??n?? anlatan kod blogu ekleriz hemen ard??na Snackbar?? koyar??z
Snackbar.make(binding.getRoot(),"Permission Needed For Maps",Snackbar.LENGTH_INDEFINITE).setAction("Give Permission Please", new View.OnClickListener()
Bu altta bir snackbar acar ve neden izni istedigimizi kullan??c??ya mant??kl?? bir sekilde belirtir.

LatLng effiel = new LatLng(48.8559413,2.2930037);
        mMap.addMarker(new MarkerOptions().position(effiel).title("sogan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(effiel,15));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(effiel));
uygulamay?? olustururken ilk bu kodlar?? cag??rd??k.su an bunlara ihtiyac kalmad??.






 */