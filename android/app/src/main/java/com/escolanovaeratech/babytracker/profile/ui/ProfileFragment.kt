package com.escolanovaeratech.babytracker.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.escolanovaeratech.babytracker.R
import com.escolanovaeratech.babytracker.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBabyInfo()
        setupClickListeners()
        setupBottomNavigation()
    }

    private fun setupBabyInfo() {
        binding.tvBabyName.text = "Emma Rose"
        binding.tvBirthDate.text = "Born March 15, 2024"
        binding.tvAgeValue.text = "11"
        binding.tvWeightValue.text = "3.65"
        binding.tvHeightValue.text = "58"
    }

    private fun setupClickListeners() {
        binding.cardEditProfile.setOnClickListener {
            Toast.makeText(requireContext(), "Edit Baby Profile clicked", Toast.LENGTH_SHORT).show()
        }

        binding.cardNotifications.setOnClickListener {
            Toast.makeText(requireContext(), "Notifications clicked", Toast.LENGTH_SHORT).show()
        }

        binding.cardExportData.setOnClickListener {
            Toast.makeText(requireContext(), "Export Data clicked", Toast.LENGTH_SHORT).show()
        }

        binding.carHelpSupport.setOnClickListener {
            Toast.makeText(requireContext(), "Help Support clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    Toast.makeText(requireContext(), "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.nav_profile -> {
                    Toast.makeText(requireContext(), "Profile clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.tvSettings -> {
                    Toast.makeText(requireContext(), "Settings clicked", Toast.LENGTH_SHORT).show()
                    true
                }

                else -> false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}