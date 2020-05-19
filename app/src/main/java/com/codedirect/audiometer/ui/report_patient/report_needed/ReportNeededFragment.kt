package com.codedirect.audiometer.ui.report_patient.report_needed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.codedirect.audiometer.R

/**
 * A simple [Fragment] subclass.
 */
class ReportNeededFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report_needed, container, false)
    }

}
