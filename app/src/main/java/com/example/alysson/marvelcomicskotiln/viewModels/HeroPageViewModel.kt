package com.example.alysson.marvelcomicskotiln.viewModels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.ImageView
import com.example.alysson.marvelcomicskotiln.models.Hero
import com.example.alysson.marvelcomicskotiln.repositories.HeroRepository

class HeroPageViewModel constructor(context: Context, position: Int) : ViewModel() {

    private val heroRepository: HeroRepository = HeroRepository(context)
    var hero: Hero

    init {

        val heroRepository = HeroRepository(context)

        hero = heroRepository.findHero(position)!!

        println(position)

    }

    fun setImageBackdrop(backdrop: ImageView) {
        heroRepository.setHeroPerfil(hero, backdrop)
    }

    fun getHeroName(): String = if (hero.name != null) hero.name!! else ""

    fun getHeroDescription(): String = if (hero.description != null) hero.description!! else ""

    fun getHeroUrlDetails(): String = if (hero.urls.size != 0) hero.urls[0]?.url!! else ""

    fun getHeroComicsParticipation(): String {
        var textWithParticipations = ""
        if (hero.comics != null)
            hero.comics!!.items.forEach {
                comic -> textWithParticipations = "$textWithParticipations ${comic.name}"
            }

        return textWithParticipations
    }

}