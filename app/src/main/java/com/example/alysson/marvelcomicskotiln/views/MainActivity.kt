package com.example.alysson.marvelcomicskotiln.views

import android.app.Activity
import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.alysson.marvelcomicskotiln.App
import com.example.alysson.marvelcomicskotiln.R
import com.example.alysson.marvelcomicskotiln.di.modules.MainModule
import com.example.alysson.marvelcomicskotiln.viewModels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var mainViewModel: MainViewModel? = null
    private val visibleThreshold: Int = 10
    private val spanCount: Int = 1
    val Activity.app: App
        get() = application as App
    val component by lazy { app.component.plus(MainModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        component.inject(this)

        mainViewModel = MainViewModel(this)

        recycler.adapter = HeroAdapter(mainViewModel!!.characterDataContainer.results, this, mainViewModel!!.heroRepository!!, object : OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                val intent = Intent(baseContext, HeroPageActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("POSITION", mainViewModel!!.characterDataContainer.results[position]!!.id)
                baseContext.startActivity(intent)
            }
        })

        val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager = layoutManager

        mainViewModel?.getHeroes()

        recycler.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            var arrayInt = IntArray(spanCount){spanCount}
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                var totalItem = recycler!!.layoutManager.itemCount
                var lastVisibleItem = layoutManager.findLastVisibleItemPositions(arrayInt)

                if(totalItem <= lastVisibleItem[spanCount-1] + visibleThreshold)
                    mainViewModel?.getHeroes()
            }
        })
    }

    override fun onResume() {
        super.onResume()

        mainViewModel?.loading?.observe(this, Observer { it ->
            if (it != null) {
                if (it) {
                    progressBar.visibility = View.VISIBLE
                } else {
                    recycler.adapter.notifyItemInserted(mainViewModel!!.characterDataContainer.results.size)
                    progressBar.visibility = View.GONE
                }
            }
        })
    }

}
