package com.example.cft

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cft.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = MainFragmentViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentData()
    }

    private fun getCurrentData() {
        binding.btnRequest.setOnClickListener {
            if (!etChecking()) {
                val bin: String = binding.etBin.text.toString()
                viewModel.binRequest(bin, requireContext())
                getCurrentBankingModel()
            }
        }
    }

    private fun etChecking(): Boolean {
        val warning = R.string.binWarning
        with(binding){
            if (etBin.text.length<6) etBin.error = getString(warning)
            return etBin.text.length<6
        }

    }


    private fun getCurrentBankingModel() {
        viewModel.currentBin.observe(viewLifecycleOwner) {
            with(binding) {
                paymentSystem.text = it.paymentSystem
                cardType.text = it?.cardType
                country.text = it.country
                bankName.text = it.bankName
                website.text = it.website
                bankPhone.text = it.bankPhone
            }
        }
    }
}