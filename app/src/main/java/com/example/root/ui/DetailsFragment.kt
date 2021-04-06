package com.example.root.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.root.databinding.FragmentDetailsBinding
import com.example.root.domain.SearchItem
import com.example.root.ui.viewmodel.DetailsViewModel
import com.example.root.util.DetailViewModelFactory
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController


class DetailsFragment : Fragment() {


    val viewModel : DetailsViewModel by lazy {
        ViewModelProvider(this,DetailViewModelFactory()).get(DetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val searchItem : SearchItem? = arguments?.getParcelable(SEARCH_ITEM_OBJ)
        if(searchItem != null ) {
            Log.d("detailsfrag" , "search item " + searchItem.title)
        }

        val binding : FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater,
            com.example.root.R.layout.fragment_details,
            container,
            false
        )

        binding.viewModel = viewModel
        setHasOptionsMenu(true)
        showBackButton()
        return binding.root
    }


    fun showBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.title = "Details Fragment"
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            android.R.id.home -> { onBackPressed() }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onBackPressed() {
        findNavController().navigateUp()
    }


}

