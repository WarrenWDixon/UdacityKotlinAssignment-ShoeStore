package com.udacity.shoestore

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.CardViewBinding
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.FragmentWelcomeBinding
import com.udacity.shoestore.models.Shoe

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class ShoeListFragment : Fragment() {

    private lateinit var shoeViewModel: ShoeViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding :FragmentShoeListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_shoe_list, container, false)
        binding.fab.setOnClickListener{ v: View ->
            binding.fab.findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment2())
        }

        shoeViewModel = ViewModelProvider(requireActivity()).get(ShoeViewModel::class.java)

        shoeViewModel.liveList.observe(viewLifecycleOwner, Observer {shoeList ->
            var len = shoeList.size
            if (len  > 0) {
                for (shoe in shoeList) {
                    var shoeName = shoe.name
                    var shoeSize = shoe.size
                    var shoeCompany = shoe.company
                    var shoeDescription = shoe.description

                    //
                    // var view : View?  = getView()
                    var view: View = binding.shoeListLayout
                    (view as? ViewGroup)?.let {
                        val bindingCardView: CardViewBinding = DataBindingUtil.inflate(
                            inflater, R.layout.card_view, view, true
                        )
                        bindingCardView.shoeName.text = shoeName
                        bindingCardView.shoeSize.text = shoeSize.toString()
                        bindingCardView.shoeCompany.text = shoeCompany
                        bindingCardView.shoeDescription.text = shoeDescription
                    }
                }
                }
            })

        return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ShoeListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ShoeListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}