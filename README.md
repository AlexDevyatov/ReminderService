# ReminderService

Android-приложение для создания и управления напоминаниями с поддержкой уведомлений и повторяющихся сигналов.

## Возможности

- Создание, редактирование и удаление напоминаний
- Повторяющиеся напоминания (ежедневно, еженедельно)
- Уведомления с вибрацией
- Откладывание (snooze) напоминаний
- Автоматическое восстановление будильников после перезагрузки устройства и изменения времени/часового пояса
- Включение/отключение напоминаний

## Требования

- Android Studio Hedgehog или новее
- JDK 17
- Min SDK: 26 (Android 8.0)
- Target SDK: 34

## Стек технологий

- **Kotlin** + **Jetpack Compose** — UI и логика приложения
- **Hilt** — внедрение зависимостей
- **Room** — локальное хранение данных
- **Coroutines** — асинхронные операции
- **Navigation Compose** — навигация между экранами
- **AlarmManager** — точный scheduling напоминаний

## Архитектура

Проект следует принципам Clean Architecture с разделением на три слоя:

```
app/src/main/java/com/reminder/service/
├── data/          # Room (Entity, DAO, Database), реализация репозитория
├── domain/        # Модель, интерфейсы, use cases
├── presentation/  # Compose-экраны, ViewModels, тема
├── di/            # Hilt-модули
├── notification/  # Построение и показ уведомлений
└── scheduling/    # AlarmManager, BroadcastReceiver
```

## Сборка и запуск

```bash
# Собрать debug APK
./gradlew assembleDebug

# Установить на подключённое устройство
./gradlew installDebug

# Запустить unit-тесты
./gradlew test
```

## Тестирование

Используемые библиотеки: JUnit 4, MockK, Robolectric, Google Truth, kotlinx-coroutines-test.

## Лицензия

MIT
