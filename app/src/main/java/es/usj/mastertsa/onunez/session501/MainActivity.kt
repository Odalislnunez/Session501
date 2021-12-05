package es.usj.mastertsa.onunez.session501

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnPing.setOnClickListener {
            Thread(Runnable {
                ping("8.8.8.8") }).start()
        }
    }

    private fun ping(ip: String) {
        try {
            val process = Runtime.getRuntime().exec("ping -c 8 $ip")
            val bufferedReader = BufferedReader(
                InputStreamReader(process.inputStream)
            )
            val log = StringBuilder()

            bufferedReader.lines().forEach {
                log.append("$it\n")
            }
            runOnUiThread {
                tvPingResult.text = log.toString()
            }
        }
        catch (e: IOException) { }
    }
}