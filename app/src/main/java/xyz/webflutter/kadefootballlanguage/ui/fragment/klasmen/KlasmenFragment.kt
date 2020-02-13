package xyz.webflutter.kadefootballlanguage.ui.fragment.klasmen


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_klasmen.*
import org.jetbrains.anko.support.v4.onRefresh

import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.AdapterKlasmen
import xyz.webflutter.kadefootballlanguage.adapter.SpinnerAdapters
import xyz.webflutter.kadefootballlanguage.model.ModelKlasmen
import xyz.webflutter.kadefootballlanguage.presenter.KLasmenPresenter
import xyz.webflutter.kadefootballlanguage.utils.EspressoIdlingResource
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.KlasmenView

/**
 * A simple [Fragment] subclass.
 */
class KlasmenFragment : Fragment(), KlasmenView {

    private lateinit var idLeague: String
    private var idSeasonLeague: String = "1920"
    private var klasmen: MutableList<ModelKlasmen> = mutableListOf()
    private lateinit var presenter: KLasmenPresenter
    private lateinit var adapter: AdapterKlasmen

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_klasmen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val teamList = resources.getStringArray(R.array.name)
        val idList = resources.getStringArray(R.array.id)

        rv_klasmen.layoutManager = LinearLayoutManager(context)
        adapter = AdapterKlasmen(klasmen)
        rv_klasmen.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = KLasmenPresenter(this, request, gson)

        val adapterLeague = SpinnerAdapters(requireContext().applicationContext, teamList, idList)
        spinner_league_klasmen.adapter = adapterLeague
        spinner_league_klasmen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                idLeague = adapterLeague.listIdTeam[position]
                EspressoIdlingResource.increment()
                presenter.getKlasmenLeague(idLeague, idSeasonLeague)
            }
        }
        swipe_klasmen.onRefresh {
            EspressoIdlingResource.increment()
            presenter.getKlasmenLeague(idLeague, idSeasonLeague)
        }
    }

    override fun showLoading() {
        progress_bar_klasmen.visible()
    }

    override fun hideLoading() {
        progress_bar_klasmen.invisible()
    }

    override fun showKlasmenLeague(data: List<ModelKlasmen>) {
        if (!EspressoIdlingResource.idlingSource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        swipe_klasmen.isRefreshing = false
        klasmen.clear()
        klasmen.addAll(data)
        adapter.notifyDataSetChanged()
        hideLoading()
    }
}
