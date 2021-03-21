package app.storytel.candidate.com.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.storytel.candidate.com.model.Comment
import app.storytel.candidate.com.model.Post
import app.storytel.candidate.com.shared.Constants

import app.storytel.candidate.com.shared.extensions.toModel
import com.storytel.network.repository.IPostsRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class PostsViewModel(private var repository: IPostsRepository) : ViewModel() {

    private var completableJob: Job? = null

    val loadingPostsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorPostsLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val loadingCommentsLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val getPostsLiveData: MutableLiveData<MutableList<Post>> = MutableLiveData()
    val getCommentsLiveData: MutableLiveData<MutableList<Comment>> = MutableLiveData()

    fun getPosts() {
        if(getPostsLiveData.value.isNullOrEmpty()){
            loadingPostsLiveData.postValue(true)
            errorPostsLoadingLiveData.postValue(false)

            completableJob = viewModelScope.launch {
                runCatching {
                    repository.getPostAndPhoto()
                }.onSuccess {
                    getPostsLiveData.postValue(it.map {
                        it.toModel()
                    }.toMutableList())
                    loadingPostsLiveData.postValue(false)
                }.onFailure {
                    loadingPostsLiveData.postValue(false)
                    errorPostsLoadingLiveData.postValue(true)
                }
            }
        }
    }

    fun getComments(postId: Int) {
        loadingCommentsLiveData.postValue(true)

        completableJob = viewModelScope.launch {
            runCatching {
                repository.getTopComments(postId, Constants.LIMIT_TOP_COMMENTS)
            }.onSuccess {
                getCommentsLiveData.postValue(it.map {
                    it.toModel()
                }.toMutableList())
                loadingCommentsLiveData.postValue(false)
            }.onFailure {
                loadingCommentsLiveData.postValue(false)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        completableJob?.cancel()
    }
}