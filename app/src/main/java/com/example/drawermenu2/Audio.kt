package com.example.drawermenu2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler
import android.widget.Button
import android.widget.SeekBar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Audio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Audio : Fragment() {

    lateinit var mediaplayer: MediaPlayer
    lateinit var seekbar: SeekBar
    private val handler = Handler()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_audio, container, false)
        val play_button = view.findViewById<Button>(R.id.play)
        val pause_button = view.findViewById<Button>(R.id.pause)
        val stop_button = view.findViewById<Button>(R.id.stop)
        seekbar = view.findViewById(R.id.seekbar)

        val audioUri = Uri.parse("android.resource://${requireContext().packageName}/"+R.raw.halamadrid)
        mediaplayer = MediaPlayer.create(requireContext(), audioUri)

        seekbar.max = mediaplayer.duration

        play_button.setOnClickListener{
            mediaplayer.start()
            updateSeekBar()
        }

        pause_button.setOnClickListener{
            if (mediaplayer.isPlaying) {
                mediaplayer.pause()
            }
        }

        stop_button.setOnClickListener{
            if (mediaplayer.isPlaying) {
                mediaplayer.stop()
                mediaplayer.prepare()
                seekbar.progress = 0
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaplayer.seekTo(progress)
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Do nothing
            }
        })

        return view
    }

    private fun updateSeekBar() {
        seekbar.progress = mediaplayer.currentPosition
        if (mediaplayer.isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaplayer.release()
    }

}