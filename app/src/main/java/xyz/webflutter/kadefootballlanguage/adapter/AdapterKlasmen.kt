package xyz.webflutter.kadefootballlanguage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_klasmen.view.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.model.ModelKlasmen

class AdapterKlasmen(private val klasmen: List<ModelKlasmen>): RecyclerView.Adapter<AdapterKlasmen.KlasmenHolder>(){

    class KlasmenHolder(view: View): RecyclerView.ViewHolder(view) {
        lateinit var id:String
        fun bindItem(itemKlasmen: ModelKlasmen){
            itemView.name_team.text = itemKlasmen.name
            itemView.team_point.text = itemKlasmen.total
            itemView.team_played.text = itemKlasmen.played
            itemView.team_win.text = itemKlasmen.win
            itemView.team_draw.text = itemKlasmen.draw
            itemView.team_loss.text = itemKlasmen.loss
            itemView.team_gf.text = itemKlasmen.goalsfor
            itemView.team_ga.text = itemKlasmen.goalsagainst
            itemView.team_gd.text = itemKlasmen.goalsdifference
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KlasmenHolder {
        return KlasmenHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_klasmen, parent, false))
    }

    override fun getItemCount(): Int = klasmen.size

    override fun onBindViewHolder(holder: KlasmenHolder, position: Int) {
        holder.bindItem(klasmen[position])
    }
}