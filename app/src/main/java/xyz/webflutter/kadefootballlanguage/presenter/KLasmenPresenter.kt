package xyz.webflutter.kadefootballlanguage.presenter

import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import xyz.webflutter.kadefootballlanguage.model.KlasmenResponses
import xyz.webflutter.kadefootballlanguage.model.TeamResponse
import xyz.webflutter.kadefootballlanguage.utils.CoroutineContextProvider
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiServices
import xyz.webflutter.kadefootballlanguage.view.KlasmenView

class KLasmenPresenter(
    private val klasmenView: KlasmenView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getKlasmenLeague(idLeague: String, idSeason: String) {
        klasmenView.showLoading()
        GlobalScope.launch(context.main) {
            val getData = gson.fromJson(
                apiRepository.doRequest(
                    ApiServices.getKlasmenLeague(
                        idLeague,
                        idSeason
                    )
                ).await(), KlasmenResponses::class.java
            )
            klasmenView.hideLoading()
            klasmenView.showKlasmenLeague(getData.getKlasmen)
        }
    }
}