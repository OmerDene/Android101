package com.example.android101.KotlinArtBook

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.android101.R
import com.example.android101.databinding.ActivityKotlinArtBookDetailBinding
import com.example.android101.databinding.ActivityKotlinLandMarkDetailActiviyBinding
import com.google.android.material.snackbar.Snackbar
import java.io.ByteArrayOutputStream
import java.io.IOException

class KotlinArtBookDetail : AppCompatActivity() {
    var selectedBitmap : Bitmap? = null
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private  lateinit var binding : ActivityKotlinArtBookDetailBinding
    private lateinit var dataBase : SQLiteDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKotlinArtBookDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        registerLauncher()
        val dataBase =this.openOrCreateDatabase("Arts", MODE_PRIVATE,null)
        val intent =intent
        val info =intent.getStringExtra("info")
        if(info.equals("new")){
            binding.kotlinArtYearText.setText("")
            binding.kotlinArtNameText.setText("")
            binding.kotlinArtistNameText.setText("")
            binding.button17.visibility=View.VISIBLE

        }else{
            binding.button17.visibility=View.INVISIBLE
            val selectedId =intent.getIntExtra("id",1)
            val cursor =dataBase.rawQuery("SELECT*FROM arts WHERE id = ?", arrayOf(selectedId.toString()))
            val artNameIx =cursor.getColumnIndex("artname")
            val artistNameIx =cursor.getColumnIndex("artistname")
            val yearIx = cursor.getColumnIndex("year")
            val imageIx =cursor.getColumnIndex("image")
            while (cursor.moveToNext()){
                binding.kotlinArtistNameText.setText(cursor.getString(artistNameIx))
                binding.kotlinArtNameText.setText(cursor.getString(artNameIx))
                binding.kotlinArtYearText.setText(cursor.getString(yearIx))
                val bytearray =cursor.getBlob(imageIx)
                val bitmap =BitmapFactory.decodeByteArray(bytearray,0,bytearray.size)
                binding.kotlinFotoSecImageText.setImageBitmap(bitmap)


            }
            cursor.close()

        }



    }

    fun save(view : View){
        val artName =binding.kotlinArtNameText.text.toString()
        val artistName =binding.kotlinArtistNameText.text.toString()
        val year = binding.kotlinArtYearText.text.toString()
        if(selectedBitmap!=null){
            val smallBitmap =makeSmallerBitmap(selectedBitmap!!, maximumSize = 300)
            val outputStream = ByteArrayOutputStream()
            smallBitmap.compress(Bitmap.CompressFormat.PNG,50,outputStream)
            val byteArray = outputStream.toByteArray()
            try {

                dataBase.execSQL("CREATE TABLE IF NOT EXISTS arts (id INTEGER PRIMARY KEY, artname VARCHAR,artistname VARCHAR,year VARCHAR,image BLOB)")
                val sqlString = "INSERT INTO arts(artname,artistname,year,image) VALUES (? , ? ,? ,?)"
                val statement =dataBase.compileStatement(sqlString)
                statement.bindString(1,artName)
                statement.bindString(2,artistName)
                statement.bindString(3,year)
                statement.bindBlob(4,byteArray)
                statement.execute()

            }catch (e : Exception){
                e.printStackTrace()

            }
            val intent = Intent(this@KotlinArtBookDetail,KotlinArtBook::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)


        }


    }
    fun makeSmallerBitmap(image: Bitmap, maximumSize : Int) : Bitmap {
        var width = image.width
        var height = image.height

        val bitmapRatio : Double = width.toDouble() / height.toDouble()
        if (bitmapRatio > 1) {
            width = maximumSize
            val scaledHeight = width / bitmapRatio
            height = scaledHeight.toInt()
        } else {
            height = maximumSize
            val scaledWidth = height * bitmapRatio
            width = scaledWidth.toInt()
        }
        return Bitmap.createScaledBitmap(image,width,height,true)
    }

    fun fotoSec(view: View){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_MEDIA_IMAGES)) {
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        View.OnClickListener {
                            permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                        }).show()
                } else {
                    permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                }
            } else {
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        } else {
            if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        View.OnClickListener {
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }).show()
                } else {
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            } else {
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            }
        }


    }

    fun registerLauncher() {
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val intentFromResult = result.data
                if (intentFromResult != null) {
                    val imageData = intentFromResult.data
                    try {
                        if (Build.VERSION.SDK_INT >= 28) {
                            val source = ImageDecoder.createSource(this@KotlinArtBookDetail.contentResolver, imageData!!)
                            selectedBitmap = ImageDecoder.decodeBitmap(source)
                            binding.kotlinFotoSecImageText.setImageBitmap(selectedBitmap)
                        } else {
                            selectedBitmap = MediaStore.Images.Media.getBitmap(this@KotlinArtBookDetail.contentResolver, imageData)
                            binding.kotlinFotoSecImageText.setImageBitmap(selectedBitmap)
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }
        permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
            if (result) {
                //permission granted
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            } else {
                //permission denied
                Toast.makeText(this@KotlinArtBookDetail, "Permisson needed!", Toast.LENGTH_LONG).show()
            }
        }
    }

}




