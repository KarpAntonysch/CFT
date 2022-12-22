package com.example.cft

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cft.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater,container,false)
        viewModel = MainFragmentViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentData()
    }
    //TODO краш при ошибке сервера! Учестьи поймать!
    private fun getCurrentData(){
        binding.btnRequest.setOnClickListener{
            val bin:String = binding.etBin.text.toString()
            viewModel.binRequest(bin,requireContext())
            getCurrentBankingModel()
        }
    }

    private fun getCurrentBankingModel(){
        viewModel.currentBin.observe(viewLifecycleOwner) {
            binding.paymentSystem.text = it.paymentSystem
            binding.cardType.text = it.cardType
            binding.country.text = it.country
            binding.bankName.text = it.bankName
            binding.website.text = it.website
            binding.bankPhone.text = it.bankPhone
        }
    }
}