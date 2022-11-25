package com.example.android101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RunnableHandler extends AppCompatActivity {

    TextView textview;
    int number;
    Runnable runnable;
    Handler handler;
    Button button;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_runnable_handler);

        textview = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        number = 0;


    }


    public void start (View view) {
        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                textview.setText("time" + number);
                number++;
                textview.setText("aga" + number );
                handler.postDelayed(runnable,1000);


            }
        };
        handler.post(runnable);
        button.setEnabled(false);




    }
    public void stop (View view) {
        button.setEnabled(true);
        handler.removeCallbacks(runnable);
        number = 0;
        textview.setText("maho" + number);



    }
}
/* burada Thread.sleep yazdıktan sonra bir hata alırız ve yandaki kırmızı ampule basarak surround try/catch
diyerek      catch (InterruptedException e) {
                e.printStackTrace();
            }
   metodunuz otomatik oluştururuz , şu işe yarar : Bir şey deniyoruz eger ki bir hata alırsak hata alınca
   program çökecegine catch içinde hatayı yakalıyoruz ve o yakaladıgımız şeyde ne olacagını yazıyoruz.
   uygulamayı bu sekilde calıstırdıgımızda kitlenmesine sebep oluruz cunku thread.sleep diyerek kullanıcının
   etkilesime gectigi yeri kitlemis olurum.Bunun onune gecmek ıcın Treadı arka planda baska bir treadde calıstırmamız gerekiyor.
   Bunun icinde Runnable ve Handler  kullanabiliriz.Handler runnable ı ele aldıgımız yönettigimiz bir arayüzdür.Runnable ' da
   bir islemi birden fazla kez belirttigimiz sayıda veya belirttigimiz periyotta yapmamıza olanak saglayan bir arayüzdür.
   Sonrasında runnable ve handlerı tanımlarız ve su cıktıgında  @Override
            public void run() {
            bunun ıcıne yazdıgımız her sey benim belirttigim periyot icersinde olacaktır.ve en son handlerı kullanarak
            runablı baslatıcam.
            button.setEnabled(false); bu sekilde buttonu mainaktiviti bölümüne tanımladıgımızda starta
 2 kez bastıgımızda saniye dahada hızlanmayacaktır(bunu tanımlamadan önce hızlanıyordu)
soru neden stop buttonunu mainaktivitede tanımlayamıyorum ?(id:button3)



 */
