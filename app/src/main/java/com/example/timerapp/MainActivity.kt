package com.example.timerapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import com.example.timerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var counterActive:Boolean = false
    lateinit var binding: ActivityMainBinding
    lateinit var countDownTimer: CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.seekBar.max = 60*60
        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateTime(progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
        binding.imageButton3.setOnClickListener {
            when(counterActive){
                true ->{
                    reset()
                }
                false ->{
                    counterActive = true
                    countDownTimer = object: CountDownTimer((binding.seekBar.progress*1000+100).toLong(), 1000) {
                        override fun onTick(millisUntilFinished: Long) {updateTime((millisUntilFinished/1000).toInt())}

                        override fun onFinish() {
                            reset()
                            }
                    }
                    countDownTimer.start()
                }
            }
        }
    }
    fun updateTime(time:Int){
        var minutes = time /60
        var seconds = time - minutes*60
        var secondsString = seconds.toString()
        if (seconds<9){
            secondsString = "0"+seconds.toString()
        }
        binding.textView.setText("${minutes}:${secondsString}")

    }

    fun reset(){
        binding.seekBar.progress = 0
        binding.textView.setText("00:00")
        counterActive = false
        countDownTimer.cancel()
    }
}