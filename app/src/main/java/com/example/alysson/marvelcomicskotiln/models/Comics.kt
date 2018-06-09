package com.example.alysson.marvelcomicskotiln.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class Comics : RealmObject(){

    var available:Int? = null
    var items: RealmList<Comic> = RealmList()

}