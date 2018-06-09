package com.example.alysson.marvelcomicskotiln.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.RealmClass

@RealmClass
open class HeroUrls : RealmObject(){

    var type:String? = null
    var url: String? = null

}