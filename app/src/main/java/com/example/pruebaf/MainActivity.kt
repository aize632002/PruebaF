package com.example.pruebaf

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
// Importamos el ViewBinding que acabamos de activar
import com.example.pruebaf.databinding.ActivityMainBinding
// Tus clases de Room y MVVM
import data.local.entity.AppDatabase
import repository.TechRepository
import repository.LaboratorioViewModel
import repository.LaboratorioViewModelFactory

class MainActivity : AppCompatActivity() {

    // SOLO UNA DECLARACIÓN DE BINDING
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LaboratorioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Inflamos la vista correctamente
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar Base de Datos y Arquitectura
        val database = AppDatabase.getDatabase(this)
        val repository = TechRepository(database.laboratorioDao(), database.equipoDao())
        val factory = LaboratorioViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[LaboratorioViewModel::class.java]

        // Configurar RecyclerView usando el binding (debe existir en activity_main.xml)
        binding.recyclerViewLabs.layoutManager = LinearLayoutManager(this)

        // Configurar el botón flotante (debe tener id: fabAddLab en el XML)
        binding.fabAddLab.setOnClickListener {
            viewModel.insertarLaboratorio("Laboratorio A", "Edificio Principal")
            Toast.makeText(this, "Laboratorio insertado", Toast.LENGTH_SHORT).show()
        }

        // Observar la base de datos en tiempo real
        lifecycleScope.launch {
            viewModel.todosLosLaboratorios.collect { lista ->
                if(lista.isNotEmpty()) {
                    Toast.makeText(this@MainActivity, "Hay ${lista.size} labs", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
