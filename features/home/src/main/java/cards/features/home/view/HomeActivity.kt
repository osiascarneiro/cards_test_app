package cards.features.home.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import cards.features.home.di.module
import cards.features.home.viewmodel.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@KoinApiExtension
class HomeActivity: AppCompatActivity(), KoinComponent {

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.home_activity)
        loadKoinModules(module)

        homeViewModel.widgetsLiveData.observe(this, {
            Log.d("Test", "widgets")
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(module)
    }

}