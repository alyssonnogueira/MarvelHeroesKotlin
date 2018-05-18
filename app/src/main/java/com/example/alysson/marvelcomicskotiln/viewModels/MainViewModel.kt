package com.example.alysson.marvelcomicskotiln.viewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import android.util.Log
import com.example.alysson.marvelcomicskotiln.models.CharacterDataContainer
import com.example.alysson.marvelcomicskotiln.models.Hero
import com.example.alysson.marvelcomicskotiln.repositories.HeroRepository
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import io.realm.RealmList

class MainViewModel(context: Context) : ViewModel() {

    val heroRepository: HeroRepository = HeroRepository(context)

    var loading: MutableLiveData<Boolean> = MutableLiveData()

    var characterDataContainer: CharacterDataContainer

    init {

        if (this.heroRepository.findFirstCharacterDataContainer() == null){
            characterDataContainer = CharacterDataContainer()
            heroRepository.saveObj(characterDataContainer)
        } else {
            characterDataContainer = this.heroRepository.findFirstCharacterDataContainer()!!
        }

        loading.value = false
    }

    fun getHeroes() {

            if ((characterDataContainer.offset < characterDataContainer.total) && (loading.value == false)) {
                val answerObservable = heroRepository.getHeroes(characterDataContainer.results.size)

                loading.postValue(true)
                answerObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ answer ->
                            Log.d("Answer", answer.toString())

                            var newCharacterDataContainer = Gson().fromJson<CharacterDataContainer>(answer.get("data")
                                    , CharacterDataContainer::class.java)

                            characterDataContainer.realm.executeTransaction {
                                characterDataContainer.count += newCharacterDataContainer.count
                                characterDataContainer.limit += newCharacterDataContainer.limit
                                characterDataContainer.offset += newCharacterDataContainer.offset
                                characterDataContainer.total += newCharacterDataContainer.total
                                characterDataContainer.results.addAll(newCharacterDataContainer.results)
                            }

                            loading.postValue(false)
                        }, { error ->
                            Log.d("ERRO", error.toString())
                            loading.postValue(false)
                        })
            }
        }

}