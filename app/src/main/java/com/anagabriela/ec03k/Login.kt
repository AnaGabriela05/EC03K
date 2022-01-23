package com.anagabriela.ec03k

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val login_button: MaterialButton = findViewById(R.id.login_button)
        val register_button: MaterialButton = findViewById(R.id.register_button)
        login_button.setOnClickListener {

            val intent:Intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        register_button.setOnClickListener {
            val intent:Intent= Intent(this,Register::class.java)
            startActivity(intent)
        }

    }
}