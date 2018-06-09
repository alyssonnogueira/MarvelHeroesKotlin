package com.example.alysson.marvelcomicskotiln.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.alysson.marvelcomicskotiln.R
import com.example.alysson.marvelcomicskotiln.viewModels.MainViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash_screen.*


class SplashScreenActivity : AppCompatActivity(){
    val TAG: String = "LOG"
    private var mainViewModel: MainViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Picasso.get().load("http://www.blogdoselback.com.br/wp-content/uploads/2017/06/Marvel-Comics-800x420-710x373.jpg").into(imageView)

        mainViewModel = MainViewModel(this)

        mainViewModel?.getHeroes()

        startActivity(intent)
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
        finish()
    }
}