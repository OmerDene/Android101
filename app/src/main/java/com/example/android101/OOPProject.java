package com.example.android101;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class OOPProject extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oopproject);
        User mahoaga = new User("mehmet","driver");
        System.out.println(mahoaga.name);
        System.out.println(mahoaga.job);
        mahoaga.job="sogan";
        System.out.println(mahoaga.job);


        ////////////////////////////////////////////////////////////////////////////////////////////


        Musician james = new Musician("james","guitar",50);
        james.setAge(48496515);
        System.out.println(james.getAge());
        james.setInstrument("ddd");
        System.out.println(james.getInstrument());
        System.out.println(james.getName());
        ////////////////////////////////////////////////////////////////////////////
        //Inheritance

        MusiciansEkstra lars = new MusiciansEkstra("Lars","drums",42);
        System.out.println(lars.getAge());
        lars.setInstrument("kurbaga");
        System.out.println(lars.getInstrument());
        //Static polymorphism aynı sınıfın icerisinde farklı metodları aynı isimle kullanmaya denir.
        //Static polymorphism
        Mathematics mathematics = new Mathematics();
        System.out.println(mathematics.sum());
        System.out.println(mathematics.sum(2,3));
        System.out.println(mathematics.sum(-3,4,-5));
        //Dynamic polymorphism
       /* Animal myAnimal = new Animal();
        myAnimal.sing();
        Dog myDog = new Dog();
        myDog.test();
        myDog.sing();*/




    }
}
