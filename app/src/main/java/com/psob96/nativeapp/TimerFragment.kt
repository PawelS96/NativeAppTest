package com.psob96.nativeapp

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.android.synthetic.main.fragment_timer.view.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*

class TimerFragment : Fragment() {

    private val handler = Handler()
    var timers = mutableListOf<TextView>()

    private var counter = 0
    private var timerRunning = false
    private var delay : Long = 20

    private fun startTimer() {
        counter = 0
        val timerRunnable: Runnable = object : Runnable {
            override fun run() {
                val countStr = (counter++).toString()
                timers.forEach { it.text = countStr }
                handler.postDelayed(this, delay)
            }
        }
        handler.post(timerRunnable)
    }

    private fun stopTimer() {

        counter = 0
        handler.removeCallbacksAndMessages(null)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

       val root = inflater.inflate(R.layout.fragment_timer, container, false)
        for (i in 1..5){
            val counter = TextView(ContextThemeWrapper(context, R.style.TimerText), null, 0)
            counter.text = "0"
            root.counterContainer.addView(counter)
            timers.add(counter)
        }

        root.seekBar.max = 9
        root.seekBarText.text = "$delay ms"

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                delay = (progress + 1) * 10L
                seekBarText.text = "$delay ms"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        timerButton.setOnClickListener {

            if (timerRunning) {
                stopTimer()
                timerButton.text = "START"
            } else {
                startTimer()
                timerButton.text = "STOP"
            }

            timerRunning = !timerRunning

            listOf<View>(seekBarText, seekBar).forEach { view ->
                view.visibility = if (timerRunning) View.INVISIBLE else View.VISIBLE
            }
        }

        activity?.reportFullyDrawn()
    }
}