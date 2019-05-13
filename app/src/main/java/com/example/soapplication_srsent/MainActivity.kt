package com.example.soapplication_srsent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    private var btnStart: Button? = null
    private var btnSend: Button? = null
    var hubConnection: HubConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.btnStart = findViewById(R.id.buttonServer)
        this.btnSend = findViewById(R.id.buttonSend)

        hubConnection = HubConnectionBuilder.create("http://10.0.2.2:5000/movehub").build()


        val seekBarChangeListener0 = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val teValue = findViewById<EditText>(R.id.teValue)
                teValue.text = Editable.Factory.getInstance().newEditable(progress.toString())

                val teSensorIndicator = findViewById<EditText>(R.id.teSensorIndicator)
                teSensorIndicator.text = Editable.Factory.getInstance().newEditable("0")

                Send()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }

        val tSeekBar0 = findViewById<SeekBar>(R.id.seekBar0)
        tSeekBar0.setOnSeekBarChangeListener(seekBarChangeListener0)

        val seekBarChangeListener1 = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val teValue = findViewById<EditText>(R.id.teValue)
                teValue.text = Editable.Factory.getInstance().newEditable(progress.toString())

                val teSensorIndicator = findViewById<EditText>(R.id.teSensorIndicator)
                teSensorIndicator.text = Editable.Factory.getInstance().newEditable("1")

                Send()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }

        val tSeekBar1 = findViewById<SeekBar>(R.id.seekBar1)
        tSeekBar1.setOnSeekBarChangeListener(seekBarChangeListener1)

        val seekBarChangeListener2 = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val teValue = findViewById<EditText>(R.id.teValue)
                teValue.text = Editable.Factory.getInstance().newEditable(progress.toString())

                val teSensorIndicator = findViewById<EditText>(R.id.teSensorIndicator)
                teSensorIndicator.text = Editable.Factory.getInstance().newEditable("2")

                Send()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }
        //Test
        val tSeekBar2 = findViewById<SeekBar>(R.id.seekBar2)
        tSeekBar2.setOnSeekBarChangeListener(seekBarChangeListener2)

        val seekBarChangeListener3 = object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val teValue = findViewById<EditText>(R.id.teValue)
                teValue.text = Editable.Factory.getInstance().newEditable(progress.toString())

                val teSensorIndicator = findViewById<EditText>(R.id.teSensorIndicator)
                teSensorIndicator.text = Editable.Factory.getInstance().newEditable("3")

                Send()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        }

        val tSeekBar3 = findViewById<SeekBar>(R.id.seekBar3)
        tSeekBar3.setOnSeekBarChangeListener(seekBarChangeListener3)

        val onClickListenerStart = View.OnClickListener {
            val hubConnection = this.hubConnection
            val btnStart = this.btnStart
            if (hubConnection != null && btnStart != null) {
                if (btnStart.text.toString().toLowerCase().compareTo("start") == 0) {
                    if (hubConnection.connectionState === HubConnectionState.DISCONNECTED) {
                        hubConnection.start()
                    }
                    btnStart.text = "stop"
                } else if (btnStart.text.toString().toLowerCase().compareTo("stop") == 0) {
                    if (hubConnection.connectionState === HubConnectionState.CONNECTED) {
                        hubConnection.stop()
                    }
                    btnStart.text="start"
                }
            }
        }
        btnStart?.setOnClickListener(onClickListenerStart)


        val onClickListenerSend = View.OnClickListener {
            this.Send()
        }
        btnSend?.setOnClickListener(onClickListenerSend)


    }

    fun Send() {
        val hubConnection = this.hubConnection
        if (hubConnection != null) {
            if (hubConnection.connectionState == HubConnectionState.CONNECTED) {
                try {
                    val teSensorID = findViewById<EditText>(R.id.teSensorID)
                    val teSensorIndicator = findViewById<EditText>(R.id.teSensorIndicator)
                    val teValue = findViewById<EditText>(R.id.teValue)
                    val mSensorID:String = teSensorID.text.toString()
                    val mSensorIndicator:String = teSensorIndicator.text.toString()
                    val mValue:String = teValue.text.toString()
                    hubConnection.send("MoveViewFromServer",mSensorID,mSensorIndicator,mValue)
                }catch (e: Exception) {}
            }
        }
    }
}
