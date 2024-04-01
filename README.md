# ProstoHack

Кроссплатформенное приложение, разработанное под операционные системы Android и iOS на технологии [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/), разрабатывалось на [хакатон](https://www.prostospb.team/hackathon-sber24) кейс №2.

## Описание проекта

Front-end приложения, разработанного для механизма унификации UI с помощью back-end, то есть структура экранов, навигация между экранами, действия кнопок вынесено на back-end. Front-end только отрисовывает интерфейс. Основная сложность была в разработке архитектуры виджетов, чтобы можно было удобно задв

## Архитектура приложения

Как основой паттерн проектирования был выбран паттерн проектирования "Компоновщик", чтобы можно было строить масштабируемый и кастомизируемый интерфейс

## Стек приложения

- [Compose Multiplatfrom](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Ktor](https://ktor.io/docs/welcome.html)
- [Kamel](https://github.com/Kamel-Media/Kamel)
- [Multiplafrom Settings](https://github.com/russhwolf/multiplatform-settings)
- [Koin](https://insert-koin.io)
- [Precompose](https://github.com/Tlaster/PreCompose)

## Запуск приложения

IDE: Android Studio/Fleet (на macOS может запускать iOS) или связка Xcode (доступна только под macOS, нужна для запуска приложения на iOS) и IntellijIdea (нужна для запуска приложения под Android/Desktop/Web)

Плагины: необходимо установить плагин Kotlin Multiplatform Mobile
На macOS необходимо уставить cocoapods (это можно сделать через brew)

Запуск: для запуска мобильного приложения в эмуляторе понадобится виртуализация
Для Intel: Установка Intel HAXM
Для AMD: включить в настройках BIOS/UEFI виртуализацию

## Скриншоты
<p>
  <img src="../master/splash.jpg" alt="Заставка" width="300" />
  <img src="../master/enter.jpg" alt="Авторизация" width="300" />
  <img src="../master/main.jpg" alt="Главный экран" width="300" />
</p>

## Видео-демонстрация

[Видео](https://youtube.com/shorts/Yk9YlZg1AfQ?si=6DMk7B1UA5bOWpiW)

### Презентация
Ссылка на [Google Disk](https://drive.google.com/file/d/1Nh49nEqURY7byS3OAFRoZPiZ2FvH1F1X/view?usp=share_link)
