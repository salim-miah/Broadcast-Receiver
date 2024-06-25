package com.example.drawermenu2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.view.GestureDetector
import android.view.ScaleGestureDetector
import android.graphics.Matrix

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ImageScale.newInstance] factory method to
 * create an instance of this fragment.
 */
class ImageScale : Fragment() {
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var scaleFactor = 1.0f
    private val matrix = Matrix()
    private lateinit var image: ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_image_scale,container,false)
        image = view.findViewById(R.id.offsafi_bhai)

        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

        image.setOnTouchListener { v, event ->
            scaleGestureDetector.onTouchEvent(event)
            true
        }

        return view
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(scaleGestureDetector: ScaleGestureDetector): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            scaleFactor = scaleFactor.coerceIn(0.1f, 10.0f) // Prevent too much zoom in or out
            matrix.setScale(
                scaleFactor,
                scaleFactor,
                scaleGestureDetector.focusX,
                scaleGestureDetector.focusY
            )
            image.imageMatrix = matrix
            return true
        }
    }
}