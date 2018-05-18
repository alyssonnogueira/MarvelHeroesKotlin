package com.example.alysson.marvelcomicskotiln.viewModels

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.widget.ImageView
import com.example.alysson.marvelcomicskotiln.ApplicationContext
import com.example.alysson.marvelcomicskotiln.models.Hero
import com.example.alysson.marvelcomicskotiln.repositories.HeroRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HeroPageViewModel @Inject constructor(@ApplicationContext context: Context) : ViewModel() {

    private val heroRepository: HeroRepository = HeroRepository(context)
    var hero: Hero

    var position: Int = 1

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

    fun getHeroUrlDetails(): String = if (hero.urlDetails != null) hero.urlDetails!! else ""

    fun getHeroComicsParticipation(): String {
        var textWithParticipations = ""
        if (hero.comics != null)
            hero.comics!!.items.forEach {
                comic -> textWithParticipations = "$textWithParticipations ${comic.name}"
            }

        return textWithParticipations
    }

}