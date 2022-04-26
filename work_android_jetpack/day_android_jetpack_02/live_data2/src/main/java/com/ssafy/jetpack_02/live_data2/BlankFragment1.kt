package com.ssafy.jetpack_02.live_data2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ssafy.jetpack_02.live_data2.databinding.FragmentBlank1Binding

class BlankFragment1 : Fragment() {

    private lateinit var binding: FragmentBlank1Binding
    private  val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentBlank1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        printCount()

        binding.buttonPlus.setOnClickListener {
            mainActivityViewModel.increaseCount()
            printCount()
        }

        binding.buttonMinus.setOnClickListener {
            mainActivityViewModel.decreaseCount()
            printCount()
        }
    }

    private fun printCount() {
        binding.textResult.text = mainActivityViewModel.count.toString()
    }
}