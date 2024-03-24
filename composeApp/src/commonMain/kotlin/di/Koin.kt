package di

import DataRepository
import DataService
import DataViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import util.Postman

/** Init Koin for init modules **/
fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            getModules()
        )
    }

fun getModules() = listOf(
    module { single { Postman() } },
    module { single { DataService(get()) } },
    module { single { DataRepository(get()) } },
    module { single { DataViewModel(get()) } }
)

/** Realization for iOS **/
fun initKoin() = initKoin { }