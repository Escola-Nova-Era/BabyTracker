package com.escolanovaeratech.babytracker

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.platform.ComposeView
import com.escolanovaeratech.babytracker.login.ui.LoginScreen
import com.escolanovaeratech.babytracker.ui.components.BabyTrackerApp // Importe sua função
import com.escolanovaeratech.babytracker.ui.theme.BabyTrackerTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Configuração de Insets para não sobrepor as barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Injetando o Compose na View XML
        val composeView = findViewById<ComposeView>(R.id.compose_view)
        composeView.setContent {
            BabyTrackerTheme {
                // Chamando sua lógica de navegação adaptativa
                BabyTrackerApp()
            }
        }
    }
}