package xyz.webflutter.kadefootballlanguage.ui.fragment.match

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_match.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.MainFragmentAdapter
import xyz.webflutter.kadefootballlanguage.ui.activity.SearchActivity

class MainMatchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_match, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view_pager.adapter = MainFragmentAdapter(childFragmentManager)
        tab_layout.setupWithViewPager(view_pager)
        search_home_match.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
    }
}