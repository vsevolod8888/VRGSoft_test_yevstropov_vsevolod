package com.example.vrgsoft_test_yevstropov_vsevolod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel@Inject constructor(val repozitory: Repozitory) : ViewModel() {
    init {
        refreshList()
    }
    fun refreshList() {
        viewModelScope.launch {
            repozitory.refreshDataFromInternet()
        }
    }
    var listRedditDomainVM: LiveData<List<RedditDomain>> = repozitory.listRedditdomainREP

    private val _choosenitem = MutableLiveData<RedditDomain>()
    val choosenitem: LiveData<RedditDomain>
        get() = _choosenitem
    fun onClickDetail(choosenItem: RedditDomain) {
        _choosenitem.value = choosenItem
    }
}