package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController

class fragmentsearch : Fragment() {
    var imageButton: ImageButton? = null
    var search_button: ImageButton? = null
    var text: EditText? = null
    var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_fragmentsearch, container, false)
        imageButton = view.findViewById(R.id.customFab)
        text = view.findViewById(R.id.search_edit_text)
        progressBar = view.findViewById(R.id.progressBar)
        search_button = view.findViewById(R.id.search_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageButton!!.setOnClickListener { v: View? ->
            val nav = findNavController(view)
            nav.popBackStack()
        }
        search_button!!.setOnClickListener { v: View? ->
            val text2 = text!!.text.toString()
            val r = GameApiService()
            r.rma(context, progressBar!!, view, text2)
        }
    }
}