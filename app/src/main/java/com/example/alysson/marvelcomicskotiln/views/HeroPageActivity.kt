package com.example.alysson.marvelcomicskotiln.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.alysson.marvelcomicskotiln.R
import com.example.alysson.marvelcomicskotiln.viewModels.HeroPageViewModel
import kotlinx.android.synthetic.main.activity_hero_page.*
import kotlinx.android.synthetic.main.content_hero_page.*

class HeroPageActivity : AppCompatActivity() {

    lateinit var heroPageViewModel: HeroPageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_page)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val position = intent.getIntExtra("POSITION", 0)
        this.heroPageViewModel = HeroPageViewModel(this, position)


        initScreen()
    }

    private fun initScreen(){

        heroPageViewModel.setImageBackdrop(backdrop)
        toolbar.title = heroPageViewModel.getHeroName()
        description.text = "Descrição do Personagem: " + heroPageViewModel.getHeroDescription()
        comicsAvailable.text = "Participações em Comics: " + heroPageViewModel.getHeroComicsParticipation()

        fab.setOnClickListener {
            println("URL HERO: " + heroPageViewModel.getHeroUrlDetails())
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(heroPageViewModel.getHeroUrlDetails())
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}