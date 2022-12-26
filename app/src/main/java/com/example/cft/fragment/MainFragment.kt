package com.example.cft.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cft.R
import com.example.cft.RvAdapter
import com.example.cft.databinding.FragmentMainBinding
import com.example.cft.room.DBApplication

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainFragmentViewModel
    private val adapter = RvAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = MainFragmentViewModel((requireActivity().application as DBApplication).bankingRepository)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCurrentData()
        insertCard()
        realizationOfRV()
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
        with(binding) {
            if (etBin.text.length < 6) etBin.error = getString(warning)
            return etBin.text.length < 6
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

                callToBank(it.bankPhone)

                goToSite(it.website)

                goToMap(it.latitude.toString(), it.longitude.toString())
            }
        }
    }

    private fun callToBank(phoneNumber: String?) {
        binding.btnPhone.setOnClickListener {
            val phoneUri = Uri.parse("tel:$phoneNumber")
            val intent = Intent(Intent.ACTION_DIAL,phoneUri )
            if (phoneNumber !== "Информация отсутсвует") {
                startActivity(intent)
            }//TODO оповещение, что номера нет в базе
        }
    }

    private fun goToSite(url: String?) {
        val webUri = Uri.parse("https://$url")
        val intent = Intent(Intent.ACTION_VIEW, webUri)
        binding.btnWebSite.setOnClickListener {
            if (url !== "Информация отсутсвует") {
                startActivity(intent)
            }//TODO оповещение, что сайта нет в базе
        }
    }

    private fun goToMap(latitude: String, longitude: String) {
        val geoUri = Uri.parse("geo:$latitude , $longitude?z=4")
        val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
        binding.btnCountry.setOnClickListener {
            if (latitude !== "Информация отсутсвует" || longitude !== "Информация отсутсвует") {
                startActivity(mapIntent)
            }//TODO оповещение, что координат нет в базе
        }
    }

    private fun insertCard(){
        viewModel.currentBin.observe(viewLifecycleOwner) {
            viewModel.insertCard(it)
        }
    }
    private fun realizationOfRV(){
        val recyclerview = binding.rvHistory
        recyclerview.adapter = adapter
        viewModel.allCards.observe(viewLifecycleOwner) {
            cardList ->
            cardList.let { adapter.submitList(it) }
        }

    }
}
