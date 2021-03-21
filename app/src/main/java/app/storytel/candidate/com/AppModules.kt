package app.storytel.candidate.com



import app.storytel.candidate.com.viewmodel.PostsViewModel
import com.storytel.network.repository.IPostsRepository
import com.storytel.network.repository.PostsRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

//dependencies for app module
val appModule = module {
    single<IPostsRepository> { PostsRepository(get()) }
    viewModel { PostsViewModel(get()) }
}