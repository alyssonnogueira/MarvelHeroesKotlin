package com.example.alysson.marvelcomicskotiln.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alysson.marvelcomicskotiln.MarvelComicsApplication
import com.example.alysson.marvelcomicskotiln.R
import com.example.alysson.marvelcomicskotiln.contracts.ApplicationComponent
import com.example.alysson.marvelcomicskotiln.contracts.HeroComponent
import com.example.alysson.marvelcomicskotiln.viewModels.HeroPageViewModel
import kotlinx.android.synthetic.main.activity_hero_page.*
import kotlinx.android.synthetic.main.content_hero_page.*
import com.example.alysson.marvelcomicskotiln.di.di.components.ActivityComponent
import javax.inject.Inject
import javax.swing.text.StyleConstants.getComponent
import com.example.alysson.marvelcomicskotiln.modules.ActivityModule

class HeroPageActivity : AppCompatActivity() {

    lateinit var heroPageViewModel: HeroPageViewModel

    private lateinit var heroComponent: HeroComponent


//    fun getActivityComponent(): ActivityComponent {
//        if (activityComponent == null) {
//            activityComponent = DaggerActivityComponent.builder()
//                    .activityModule(ActivityModule(this))
//                    .applicationComponent(MarvelComicsApplication.get(this).getComponent())
//                    .build()
//        }
//        return activityComponent
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_page)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        heroComponent = DaggerHeroComponent
                .builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(ActivityModule(this))
                .build()

        heroComponent.inject(MainActivity())

//        getActivityComponent().inject(this)

        val position = intent.getIntExtra("POSITION", 0)
        //this.heroPageViewModel = HeroPageViewModel(this, position)

        initScreen()
    }

    fun getApplicationComponent: ApplicationComponent(){
        return (MarvelComicsApplication().mApplicationComponent)
    }

    private fun initScreen(){

        heroPageViewModel.setImageBackdrop(backdrop)
        toolbar.title = heroPageViewModel.getHeroName()
        description.text = heroPageViewModel.getHeroDescription()
        comicsAvailable.text = heroPageViewModel.getHeroComicsParticipation()

        fab.setOnClickListener {
            println(heroPageViewModel.getHeroUrlDetails())
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(heroPageViewModel.getHeroUrlDetails())
            startActivity(intent)
        }
    }

}