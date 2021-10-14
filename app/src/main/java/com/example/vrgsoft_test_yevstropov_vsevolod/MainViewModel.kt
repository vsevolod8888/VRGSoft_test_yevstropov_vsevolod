package com.example.vrgsoft_test_yevstropov_vsevolod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.vrgsoft_test_yevstropov_vsevolod.domain.RedditDomain
import com.example.vrgsoft_test_yevstropov_vsevolod.repozitory.RedditPagingSource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel@Inject constructor(val repozitory: Repozitory) : ViewModel() {
//    init {
//        refreshList()
//    }
//    fun refreshList() {
//        viewModelScope.launch {
//            repozitory.refreshDataFromInternet()
//        }
//    }
   // var listRedditDomainVM: LiveData<List<RedditDomain>> = repozitory.listRedditdomainREP
    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        RedditPagingSource(repozitory.ourApiService)
    }.flow
        .cachedIn(viewModelScope)

    private val _choosenitem = MutableLiveData<RedditDomain>()
    val choosenitem: LiveData<RedditDomain>
        get() = _choosenitem
    fun onClickDetail(choosenItem: RedditDomain) {
        _choosenitem.value = choosenItem
    }
}