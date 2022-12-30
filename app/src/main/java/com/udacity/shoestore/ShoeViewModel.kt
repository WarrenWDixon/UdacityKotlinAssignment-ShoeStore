package com.udacity.shoestore

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

public class ShoeViewModel(): ViewModel() {

    val liveList =  MutableLiveData<ArrayList<Shoe>>()
    private var mListShoes = ArrayList<Shoe>()

    private var returnToList = false
    val returnToListLiveData = MutableLiveData<Boolean>()

    fun addShoe(newShoe: Shoe) {
        mListShoes.add(newShoe)
        liveList.value = mListShoes
        returnToList = true
        returnToListLiveData.value = returnToList
    }

    public fun resetReturnToListFlag() {
        returnToList = false;
        returnToListLiveData.value = returnToList
    }
}