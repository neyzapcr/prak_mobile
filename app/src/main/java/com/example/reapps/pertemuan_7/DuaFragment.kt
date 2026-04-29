package com.example.reapps.pertemuan_7

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.reapps.R

/**
 * A simple [Fragment] subclass.
 * Use the [DuaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DuaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dua, container, false)
    }

}