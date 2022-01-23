package com.anagabriela.ec03k
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.google.android.material.button.MaterialButton
import de.hdodenhof.circleimageview.CircleImageView

class Register : AppCompatActivity() {
    val REQUEST_IMAGE_CAPTURE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        val cancel_button: MaterialButton = findViewById(R.id.cancel_button)
        val regis_button: MaterialButton = findViewById(R.id.regis_button)
        cancel_button.setOnClickListener {
            val intent:Intent= Intent(this,Login::class.java)
            startActivity(intent)
        }
        regis_button.setOnClickListener {
            val intent:Intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        setupListener()
    }
    private fun setupListener() {
        val btnTakePhoto: MaterialButton = findViewById(R.id.btnImagen)
        btnTakePhoto.setOnClickListener { dispatchTakePictureIntent() }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            data?.extras?.let { bundle ->
                val imageBitmap = bundle.get("data") as Bitmap
                val image: CircleImageView = findViewById(R.id.imageprofile)
                image.setImageBitmap(imageBitmap)
            }
        }
    }
}