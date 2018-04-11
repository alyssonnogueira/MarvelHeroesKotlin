package com.example.alysson.marvelcomicskotiln

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Action
import kotlinx.android.synthetic.main.activity_main.*
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription




class MainActivity : AppCompatActivity() {

    var heroList = mutableListOf<Hero>()
    private var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = HeroAdapter(heroList, this)
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        recycler.layoutManager = layoutManager

    }

    override fun onResume() {
        super.onResume()

        Observable.fromCallable {
            HeroServices(this).getHeroes()
        }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    heroList.addAll(it)
                    recycler.adapter.notifyItemInserted(heroList.size)
                }
            //Use result for something
        )

//        Observable.fromCallable(() -> {
//            Request request = new Request.Builder()
//                    .url(url)
//                    .build();
//            try {
//                Response response = sHttpClient.newCall(request).execute();
//                return response.isSuccessful();
//            } catch (IOException e) {
//                Log.e("Network request", "Failure", e);
//            }
//
//            return false;
//        })
//        .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe((result) -> {
//            //Use result for something
//        });


//            val list = HeroServices().getHeroes() //listOf("Alpha", "Beta", "Gamma", "Delta", "Epsilon")
//                .observeOn(AndroidSchedulers.mainThread())
//        HeroServices().getHeroes()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeBy(  // named arguments for lambda Subscribers .filter { it.length >= 5 }
//                        onNext = { println("All") },
//                        onError =  { it.printStackTrace() },
//                        onComplete = {  println("All DOne") }
//                )

//                .subscribeBy(
//                        onNext = {System.out.println(it)},
//                        onError = {it.printStackTrace()},
//                        onComplete = {println("All DOne")}
//                )

        //)
//        heroList.toObservable()
//                .observeOn(Schedulers.newThread())
//                    .subscribeBy(  // named arguments for lambda Subscribers .filter { it.length >= 5 }
//                            onNext = { HeroServices().getHeroes() },
//                            onError =  { it.printStackTrace() },
//                            onComplete = { println("Done!") }
//                    )

    }

//    private fun onButtonClicked(button: Button) {
//        subscription = networkService.getObservableUser(123)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : Action1<User>() {
//                    fun call(user: User) {
//                        nameTextView.setText(user.getName())
//                        // ... set other views
//                    }
//                })
//    }

//    override fun onDestroy() {
//        if (subscription != null && !subscription!!.isUnsubscribed()) {
//            subscription!!.unsubscribe()
//        }
//        super.onDestroy()
//    }

    private fun getHeroes(): List<Hero> {
        val result = mutableListOf<Hero>()
        for (i in 1..10)
            result.add(Hero(null, "Name $i", null,null,null,null,null,null,null))

        return result
    }
//
//    fun getHeroesList(): Observable<List<Hero>> {
//        return heroList
//                .flatMap({ cityList -> Observable.from(cityList) })
//                .filter({ city -> city.getPopulation() > 500 }, 0)
//                .flatMap({ city -> weatherService.getCurrentWeather(city) }) //each runs in parallel
//                .toSortedList({ cw1, cw2 -> cw1.getCityName().compare(cw2.getCityName()) })
//    }
}
