package com.example.alysson.marvelcomicskotiln.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alysson.marvelcomicskotiln.R
import com.example.alysson.marvelcomicskotiln.models.Hero
import com.example.alysson.marvelcomicskotiln.repositories.HeroRepository
import io.realm.RealmList
import kotlinx.android.synthetic.main.card_view_hero.view.*


class HeroAdapter(val heroList: RealmList<Hero>, val context:Context, val heroRepository: HeroRepository, val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_hero, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = heroList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val hero = heroList[position]
        if (hero != null) {
            holder.let {
                it.bindView(hero, heroRepository)
            }
            holder.itemView.setOnClickListener {
                onItemClickListener.onItemClick(it, position)
            }
        }
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val name = itemView!!.tvName!!
        private val perfil = itemView!!.ivPerfil!!

        fun bindView(hero: Hero, heroRepository: HeroRepository){
            name.text = hero.name
            heroRepository.setHeroPerfil(hero, perfil)
        }
    }
}