package xyz.webflutter.kadefootballlanguage.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_teams.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.TeamsAdapter
import xyz.webflutter.kadefootballlanguage.model.ModelTeams
import xyz.webflutter.kadefootballlanguage.presenter.TeamsPresenter
import xyz.webflutter.kadefootballlanguage.utils.*
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.view.TeamsView

class SearchTeamsActivity : AppCompatActivity(), TeamsView, (ModelTeams) -> Unit, SearchView.OnQueryTextListener {

    private var searchTeams: MutableList<ModelTeams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_teams)
        rv_search_teams.layoutManager = LinearLayoutManager(this)
        adapter = TeamsAdapter(searchTeams, this)
        rv_search_teams.adapter = adapter

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, apiRepository, gson)

        search_view_teams.setOnQueryTextListener(this)
    }

    override fun showLoading() {
        progress_bar_search_teams.visible()
    }

    override fun hideLoading() {
        progress_bar_search_teams.invisible()
    }

    override fun showListTeam(data: List<ModelTeams>) {
        if (!EspressoIdlingResource.idlingSource.isIdleNow){
            EspressoIdlingResource.decrement()
        }
        val dataFiltered = data.filter { it.strSport == SOCCER_RESPONSE }
        if (dataFiltered.isNullOrEmpty()){
            val toast = Toast.makeText(this,R.string.error_search, Toast.LENGTH_LONG)
            toast.show()
        }else{
            searchTeams.clear()
            searchTeams.addAll(dataFiltered)
            adapter.notifyDataSetChanged()
        }
        hideLoading()
    }

    override fun invoke(models: ModelTeams) {
        val intent = Intent(this, DetailTeamActivity::class.java)
        intent.putExtra(DetailTeamActivity.EXTRA_ID, models.idTeam)
        intent.putExtra(DetailTeamActivity.EXTRA_STR_TEAM, models.strTeam)
        startActivity(intent)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query.let {
            EspressoIdlingResource.increment()
            presenter.getSearch(it)
        }

        hideSoftKeyboard(this@SearchTeamsActivity)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
