import elements.Button
import elements.Card
import elements.Column
import elements.EditTextField
import elements.Image
import elements.LazyColumn
import elements.Navigation
import elements.Screen
import elements.TextField
import elements.TypeButton
import kotlinx.coroutines.runBlocking
import util.Resource

class DataRepository(private val dataService: DataService) {
    suspend fun getNavigation(): Resource<Navigation> {
        return dataService.getNavigation()
        return Resource.Success(
            Navigation(
                listOf(
                    Screen(
                        LazyColumn(
                            listOf(
                                Column(
                                    buttons = listOf(
                                        Button(
                                            "Начать",
                                            "",
                                            "login",
                                            TypeButton.Navigate.name,
                                            1
                                        )
                                    ),
                                    texts = listOf(),
                                    edits = listOf(),
                                    images = listOf(Image("https://i.ibb.co/vHF7c5J/LOGO11.png", 0))
                                )
                            ),
                        ),
                        "Splash",
                        "splash"
                    ),
                    Screen(
                        LazyColumn(
                            listOf(
                                Column(
                                    edits = listOf(
                                        EditTextField(
                                            "",
                                            "username",
                                            "Логин",
                                            0
                                        ),
                                        EditTextField(
                                            "",
                                            "password",
                                            "Пароль",
                                            1
                                        )
                                    ),
                                    texts = listOf(),
                                    buttons = listOf(
                                        Button(
                                            "Вход",
                                            "login",
                                            "main",
                                            TypeButton.Create.name + "/" + TypeButton.Navigate.name,
                                            2
                                        ),
                                        Button(
                                            "Зарегестрироваться",
                                            "",
                                            "authorization",
                                            TypeButton.Navigate.name,
                                            3
                                        )
                                    ),
                                    images = listOf()
                                )
                            ),
                        ),
                        "Вход",
                        "login"
                    ),
                    Screen(
                        LazyColumn(
                            listOf(
                                Column(
                                    edits = listOf(
                                        EditTextField(
                                            "",
                                            "username",
                                            "Логин",
                                            0
                                        ),
                                        EditTextField(
                                            "",
                                            "password",
                                            "Пароль",
                                            1
                                        ),
                                        EditTextField(
                                            "",
                                            "name",
                                            "Имя",
                                            2
                                        ),
                                        EditTextField(
                                            "",
                                            "lastname",
                                            "Фамилия",
                                            3
                                        )
                                    ),
                                    texts = listOf(),
                                    buttons = listOf(
                                        Button(
                                            "Зарегестрироваться",
                                            "register",
                                            "main",
                                            TypeButton.Create.name + "/" + TypeButton.Navigate.name,
                                            4,
                                        )
                                    ),
                                    images = listOf()
                                )
                            ),
                        ),
                        "Авторизация",
                        "authorization"
                    ),
                    Screen(
                        LazyColumn(
                            listOf(
                                Card(
                                    texts = listOf(
                                        TextField(
                                            "Привет," + "user's name + lastname",
                                            0
                                        )
                                    ),
                                    route = "",
                                    type = "",
                                    routeNavigation = ""
                                ),

                                LazyColumn(
                                    listOf(
                                        TextField("Треки", 0)
                                    )
                                )
                            ),
                        ),
                        "Главный экран",
                        "main"
                    ),
                    Screen(
                        LazyColumn(
                            listOf(

                            ),
                        ),
                        "Test1",
                        "trek/{id}",
                    ),
                    Screen(
                        LazyColumn(
                            listOf(

                            ),
                        ),
                        "Test1",
                        "test"
                    ),
                    Screen(
                        LazyColumn(
                            listOf(

                            ),
                        ),
                        "Test1",
                        "test1"
                    )
                ),
                "splash"
            )
        )
    }
}