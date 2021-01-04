package com.debin.challengechip.presentation.dogs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.recyclerview.widget.GridLayoutManager
import com.debin.challengechip.R
import com.debin.challengechip.databinding.FragmentBreedDetailsBinding
import com.debin.challengechip.framework.utils.Resource
import com.debin.challengechip.presentation.breeds.BreedsViewModel
import kotlinx.android.synthetic.main.fragment_breed_details.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.random.Random

private const val TAG = "BreedDetailsFragment"

class BreedDetailsFragment : Fragment() {

    private lateinit var dogsAdapter: DogsAdapter
    private val viewModel : DogsViewModel by viewModel()
    private lateinit var binding : FragmentBreedDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBreedDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
          bindView()
          initViews()
          getDogs(getBreed())
          observeData()
    }

    private fun getBreed() : String{
     var breedName = arguments?.let {
           BreedDetailsFragmentArgs.fromBundle(
               it
           ).breedName
     }
        return breedName.toString()
    }

    private fun initViews() {
        dogsAdapter = DogsAdapter(arrayListOf())
        rv_dogs.apply {
            layoutManager = GridLayoutManager(requireContext(), 2 , GridLayoutManager.VERTICAL, false)
            adapter = dogsAdapter
        }
    }


    private fun observeData() {
       viewModel.dogs.observe(viewLifecycleOwner, Observer { result ->
           when(result) {
               is Resource.Loading -> {

               }
               is Resource.Success -> {
                       dogsAdapter.updateDogs(result.result)
               }
               is Resource.Error -> {

               }
           }
       })
    }

    private fun getDogs(breedName : String) {
        viewModel.savedBreedName.observe(viewLifecycleOwner, Observer {
            println("$TAG :: value of savedBreed :: $it")
            if(it.isEmpty()) {
                viewModel.getDogs(breedName)
            }
        })
    }

    private fun bindView() {
        binding.dogsViewModel = viewModel
        binding.lifecycleOwner = this
    }


}