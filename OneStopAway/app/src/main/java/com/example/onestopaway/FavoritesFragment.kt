package com.example.onestopaway

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import com.example.onestopaway.databinding.ActivityMainBinding
import com.example.onestopaway.databinding.FragmentFavoritesBinding
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment(), TabLayout.OnTabSelectedListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private lateinit var _viewModel : TransitItemsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        val initialFragment : StopsListFragment = StopsListFragment.newInstance(_viewModel)
        childFragmentManager.beginTransaction().apply {
            replace(R.id.favorites_container, initialFragment)
            commit()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        _binding = FragmentFavoritesBinding.inflate(inflater)
        binding.favMenuBar.addOnTabSelectedListener(this)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(viewModel: TransitItemsViewModel) =
            FavoritesFragment().apply {
                _viewModel = viewModel
            }
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        tab?.let { selectedTab ->
            val frag = childFragmentManager.findFragmentById(R.id.favorites_container)
            if (selectedTab.position == 0) {
                if (frag is StopsListFragment) {
                    childFragmentManager.beginTransaction().apply {
                        replace(R.id.favorites_container, frag)
                        commit()
                    }
                } else {
                    val newFrag = StopsListFragment.newInstance(_viewModel)
                    childFragmentManager.beginTransaction().apply {
                        replace(R.id.favorites_container, newFrag)
                        commit()
                    }

                }

            }
                if (selectedTab.position == 1) {
                    if (frag is RouteListFragment) {
                        childFragmentManager.beginTransaction().apply {
                            replace(R.id.favorites_container, frag)
                            commit()
                        }
                    } else {
                        val newFrag = RouteListFragment.newInstance(_viewModel)
                        childFragmentManager.beginTransaction().apply {
                            replace(R.id.favorites_container, newFrag)
                            commit()
                        }
                    }

                }
            }
        }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}