package com.anagabriela.ec03k

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.anagabriela.ec03k.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(),SearchView.OnQueryTextListener,
    androidx.appcompat.widget.SearchView.OnQueryTextListener {

    lateinit var toogle:ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter:Adapter
    private val imag = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val drawerLayout:DrawerLayout = findViewById(R.id.drawerLayout)
        val navView:NavigationView = findViewById(R.id.navView)
        toogle= ActionBarDrawerToggle(this, drawerLayout, R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        navView.setNavigationItemSelectedListener{
            when(it.itemId){
                R.id.item1-> Toast.makeText(applicationContext,
                    "Aun no disponible", Toast .LENGTH_SHORT).show()
                R.id.item2-> Toast.makeText(applicationContext,
                    "Aun no disponible", Toast .LENGTH_SHORT).show()
                R.id.item3-> Toast.makeText(applicationContext,
                    "Aun no disponible", Toast .LENGTH_SHORT).show()
                R.id.item4-> Toast.makeText(applicationContext,
                    "Aun no disponible", Toast .LENGTH_SHORT).show()
            }
            true
        }
        initRecyclerView()

        binding.searchView.setOnQueryTextListener(this)
    }
    private fun initRecyclerView() {
        adapter = Adapter(imag)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun searchByName(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(API::class.java).getMovieby("$query/images")
            val puppies = call.body()
            runOnUiThread{
                if(call.isSuccessful){
                    val images = puppies?.image ?: emptyList()
                    imag.clear()
                    imag.addAll(images)
                    adapter.notifyDataSetChanged()
                }else{
                    showError()
                }
            }
        }
    }
    private fun showError() {
        Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_SHORT).show()
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()){
            searchByName(query.toLowerCase())
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }



}
