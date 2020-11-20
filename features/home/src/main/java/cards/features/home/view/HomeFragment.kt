package cards.features.home.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cards.features.home.R
import cards.features.home.view.adapter.HomeAdapter
import cards.features.home.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val adapter = HomeAdapter(emptyList())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.widgetsLiveData.observe(requireActivity(), {
            adapter.widgets = it.widgets
            adapter.notifyDataSetChanged()
        })
        widgetList.adapter = adapter
    }

    companion object {
        @JvmStatic fun newInstance() = HomeFragment()
    }
}