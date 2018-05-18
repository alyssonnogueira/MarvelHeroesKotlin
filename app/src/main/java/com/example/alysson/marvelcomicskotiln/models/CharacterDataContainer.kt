package com.example.alysson.marvelcomicskotiln.models

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class CharacterDataContainer : RealmObject(){
    @PrimaryKey
    var id:Int = 1
    var offset:Int = -1
    var limit:Int = 0
    var total:Int = 0
    var count:Int = 0
    var results:RealmList<Hero> = RealmList()
}