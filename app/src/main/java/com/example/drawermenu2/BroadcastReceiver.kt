package com.example.drawermenu2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Button
import androidx.lifecycle.findViewTreeViewModelStoreOwner

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BroadcastReceiver.newInstance] factory method to
 * create an instance of this fragment.
 */
class BroadcastReceiver : Fragment() {

    lateinit var spinnerlist: Spinner

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_broadcast_receiver, container, false)

        spinnerlist = view.findViewById(R.id.broadcastreceiver_spinner)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinner_list,  //Defined in strings.xml,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerlist.adapter=adapter
        }

        var button = view.findViewById<Button>(R.id.proceed)

        button.setOnClickListener{
            val item = spinnerlist.selectedItem.toString()

            if (item=="Custom broadcast receiver"){
                val intent = Intent(requireContext(), CustomBroadcastReceiver::class.java)
                startActivity(intent)
            }
            else{
                val intent = Intent(requireContext(), SystemBatteryNotificationReceiver::class.java)
                startActivity(intent)
            }
        }
        return view
    }
}