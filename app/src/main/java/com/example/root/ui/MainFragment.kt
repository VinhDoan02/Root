package com.example.root.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.root.R
import com.example.root.data.SearchRepositoryImpl
import com.example.root.data.SearchResultApi
import com.example.root.databinding.FragmentMainBinding
import com.example.root.ui.viewmodel.MainViewModel
import com.example.root.util.MainViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class MainFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    val viewModel : MainViewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(SearchRepositoryImpl(getSearchResultApi()))
        ).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding : FragmentMainBinding = DataBindingUtil.inflate(
                inflater,
        R.layout.fragment_main,
        container,
        false
        )

        compositeDisposable.add(
            viewModel.events
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleEvent)
        )

        binding.viewModel = viewModel
        hideBackButton()
        return binding.root
    }


    fun handleEvent(event : MainViewModel.MainViewModelEvent) {
        when(event) {
            is MainViewModel.MainViewModelEvent.OnItemClick -> {
                    var bundle = bundleOf(SEARCH_ITEM_OBJ to event.item)
                    findNavController().navigate(R.id.action_main_fragment_to_details_fragment, bundle)

            }
        }
    }

        override fun onDestroyView() {
            compositeDisposable.clear()
            super.onDestroyView()
        }

    fun hideBackButton() {
        if (activity is AppCompatActivity) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as AppCompatActivity).supportActionBar?.title = "Main Fragment"
        }
    }
}


fun getSearchResultApi() : SearchResultApi {
    return Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://en.wikipedia.org/w/")
        .build()
        .create(SearchResultApi::class.java)
}

const val SEARCH_ITEM_OBJ = "searchItem"