package me.inassar.navigationarchitecturecomponent

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import me.inassar.navigationarchitecturecomponent.dummy.DummyContent

class MainActivity : AppCompatActivity(), ListFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        findNavController(R.id.nav_host).navigate(ListFragmentDirections.actionToParams().apply {
            param1 = "Selected (with safeargs)"
            param2 = item.toString()
        })
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                findNavController(R.id.nav_host).navigate(R.id.main_dest)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                findNavController(R.id.nav_host).navigate(R.id.list_dest)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                findNavController(R.id.nav_host).navigate(R.id.action_global_params, Bundle().apply {
                    putString("param1", "Android")
                    putString("param2", "Rocks")
                })
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}
