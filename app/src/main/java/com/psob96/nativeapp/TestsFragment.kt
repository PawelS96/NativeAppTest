package com.psob96.nativeapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_testes.*

class TestsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_testes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testSort.setOnClickListener { displayResults(testCodePerformance(100000)) }
        testNativeCode.setOnClickListener { displayResult(testEmptyMethod()) }
    }

    private fun displayResult(result: Number){
        AlertDialog.Builder(context).setMessage("$result ms").create().show()
    }

    private fun displayResults(results: List<Number>){
        val text = "${results[0]} ms, ${results[1]} ms"
        AlertDialog.Builder(context).setMessage(text).create().show()
    }
}