package com.example.alysson.marvelcomicskotiln

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.card_view_hero.view.*


//RecyclerView.Adapter<MyheroRecyclerViewAdapter.ViewHolder>
class HeroAdapter(val heroList: List<Hero>, val context:Context) : RecyclerView.Adapter<HeroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_hero, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = heroList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val hero = heroList[position]
        holder.let{
            //it.name?.text = hero.name
            it.bindView(hero)
        }
        //holder.perfil.drawable = hero.thumbnail
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

        val name = itemView?.tvName
//        val perfil = itemView?.ivPerfil

        fun bindView(hero: Hero){
            val name = itemView.tvName
            //val perfil = itemView.ivPerfil

            name.text = hero.name
            //perfil.drawable = hero.thumbnail
        }
//        override fun onClick(v: View?) {
//            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//        }
    }
}