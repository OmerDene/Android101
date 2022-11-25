package com.example.android101;

public class MusiciansEkstra extends Musician {
    public MusiciansEkstra(String name, String instrument, int age) {
        super(name, instrument, age);
    }

    public MusiciansEkstra() {
        super("lars","guitar",84);
    }

    public String sing() {
        return "Nothing Else Matters";

    }
}
/*extends ifadesi yanındaki yazdığımız sınıfa extend etmeyi ifade eder, bir nevi o sınıftaki özellikleri
 bu sınıfın icindede kullanabilmeyi sağlar.Burda MusiciansEkstra Musician sınıfını extens etti yani
artık Musian sınıfı özelliklerini kullanabilmeyi kolaylaştırdı.extendsi yazdıktan sonra hata alırız , public class
erişilebilirlik seviyesinin üstünde kırmızı butona tıklayıp hemen altındaki ilk ifadeyi sectigimizde super keywordu olusur
ve hata gider.
 */