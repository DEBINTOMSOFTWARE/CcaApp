package com.debin.challengechip.presentation.breeds

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.debin.challengechip.R
import com.debin.challengechip.breeds.domain.utils.OpenForTesting
import com.debin.challengechip.databinding.FragmentBreedsBinding
import com.debin.challengechip.framework.utils.Resource
import kotlinx.android.synthetic.main.fragment_breeds.*
import org.koin.android.viewmodel.ext.android.viewModel
import kotlin.random.Random

private const val TAG = "BreedsFragment"

@OpenForTesting
open class BreedsFragment : Fragment() {

    private lateinit var breedAdapter: BreedAdapter
    private val viewModel: BreedsViewModel by viewModel()
    private lateinit var binding: FragmentBreedsBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       binding = FragmentBreedsBinding.inflate(inflater)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
           bindView()
           initViews()
           observeData()
    }

    private fun initViews() {
        breedAdapter = BreedAdapter(arrayListOf(), BreedAdapter.OnClickListener {
         findNavController().navigate(
             BreedsFragmentDirections.actionBreedsFragmentToBreedDetailsFragment().setBreedName(it)
         )
        })
        rv_breeds.apply {
             layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
             rv_breeds.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
             adapter = breedAdapter
        }
    }

    private fun observeData() {
        viewModel.breeds.observe(viewLifecycleOwner, Observer {result ->
            when(result) {
                is Resource.Success -> {
                    breedAdapter.updateBreed(result.result)
                }
            }
        })
    }

    private fun bindView() {
        binding.breedViewModel = viewModel
        binding.lifecycleOwner = this
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.GONE
    }


}