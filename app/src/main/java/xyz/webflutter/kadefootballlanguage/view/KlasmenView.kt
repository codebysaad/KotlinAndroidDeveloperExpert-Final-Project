package xyz.webflutter.kadefootballlanguage.view

import xyz.webflutter.kadefootballlanguage.model.ModelKlasmen
import xyz.webflutter.kadefootballlanguage.model.ModelTeams

interface KlasmenView {
    fun showLoading()
    fun hideLoading()
    fun showKlasmenLeague(data: List<ModelKlasmen>)
}