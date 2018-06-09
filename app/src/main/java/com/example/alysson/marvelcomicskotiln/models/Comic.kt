package com.example.alysson.marvelcomicskotiln.models


import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Comic : RealmObject(){

    var resourceURI: String? = null
    var name:String? = null
}