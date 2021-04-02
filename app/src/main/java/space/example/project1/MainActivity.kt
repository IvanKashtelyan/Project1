package space.example.project1

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import java.util.*

class MainActivity : AppCompatActivity() {

    var imSemafor: ImageView? = null
    var counter: Int = 0
    var timer: Timer? = null
    var is_run = false
    var imageArray: IntArray = intArrayOf(R.drawable.semafor_red, R.drawable.semafor_yellow, R.drawable.semafor_green)
    private val viewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imSemafor = findViewById(R.id.imSemafor)
        timer = Timer()
        viewModel.liveData.observe(this, androidx.lifecycle.Observer {
            //sample observe
            Log.d("value", it)
        })
    }

    fun onClickStart(view: View) {
        imSemafor?.setImageResource(R.drawable.semafor_grey)
        view as ImageButton
        view.setImageResource(R.drawable.button_stop)
        if (!is_run) {
            startStop()
            is_run = true
        } else {
            timer?.cancel()
            view.setImageResource(R.drawable.button_start)
            is_run = false
            counter = 0
        }
    }

    fun startStop() {
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    imSemafor?.setImageResource(imageArray[counter])
                    counter++
                    if (counter == 3) counter = 0
                }
                }
            }, 0, 1000)
    }
}