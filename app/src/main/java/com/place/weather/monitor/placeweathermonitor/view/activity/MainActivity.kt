package com.place.weather.monitor.placeweathermonitor.view.activity

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import android.window.OnBackInvokedDispatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.place.weather.monitor.placeweathermonitor.R
import com.place.weather.monitor.placeweathermonitor.app.App
import com.place.weather.monitor.placeweathermonitor.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //region Исходные данные
        // Binding
    private lateinit var binding: ActivityMainBinding
    // Слушатель кнопок нижнего меню
    private val navListener: NavigationBarView.OnItemSelectedListener =
        NavigationBarView.OnItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                // Проверка на нахождение на главном окне
                if (getCurrentFragmentID() != R.id.home_page_fragment) {
                    this.findNavController(R.id.fragment_container_view)
                        .navigate(R.id.action_detail_weather_fragment_to_home_page_fragment)
                } else {
                    Toast.makeText(
                    this, resources.getString(R.string.fragment_home_page_notification_text),
                        Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onOptionsItemSelected(it)
        }
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Подключение Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // Подключение зависимостей Dagger
        App.instance.appComponent.injectMainActivity(this@MainActivity)
        // Настройка нижнего меню
        setupButtomMenu()
        // Настройка системной кнопки Back
        setBackPress()
    }

    // Настройка нижнего меню
    private fun setupButtomMenu() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        bottomNav.setOnItemSelectedListener(navListener)
    }

    //region Настройки системной кнопки Back
    private fun setBackPress() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            onBackInvokedDispatcher.registerOnBackInvokedCallback(
                OnBackInvokedDispatcher.PRIORITY_DEFAULT
            ) {
                backPressedAlgorithm()
            }
        } else {
            onBackPressedDispatcher.addCallback(
                this , object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        backPressedAlgorithm()
                    }
                })
        }
    }
    private fun backPressedAlgorithm() {
        if (getCurrentFragmentID() == R.id.home_page_fragment) {
            // Закрытие приложения
            this.finishAndRemoveTask()
        } else {
            // Переход на предыдущий фрагмент по стеку
            this.findNavController(R.id.fragment_container_view).popBackStack()
        }
    }
    //endregion

    // Получение ID текущего фрагмента
    private fun getCurrentFragmentID(): Int? {
        return this.findNavController(R.id.fragment_container_view).currentDestination?.id
    }
}