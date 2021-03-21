package app.storytel.candidate.com



import com.storytel.network.repository.IPostsRepository
import com.storytel.network.repository.PostsRepository
import org.koin.dsl.module

//dependencies for app module
val appModule = module {
    single<IPostsRepository> { PostsRepository(get()) }
}