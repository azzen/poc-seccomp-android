package com.example.poc_seccomp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.poc_seccomp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        val pid = stringFromJNI();
        binding.sampleText.text = "Current PID: $pid"
    }

    /**
     * A native method that is implemented by the 'poc_seccomp' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {
        // Used to load the 'poc_seccomp' library on application startup.
        init {
            System.loadLibrary("poc_seccomp")
        }
    }
}