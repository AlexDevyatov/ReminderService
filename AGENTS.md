# Development Commands

```bash
# Build and run
./gradlew assembleDebug
./gradlew installDebug

# Tests
./gradlew test
```

# Architecture

Clean Architecture with these boundaries:

```
app/src/main/java/com/reminder/service/
├── data/          # Room (Entity, DAO, Database), ReminderRepositoryImpl
├── domain/        # Reminder model, ReminderRepository interface, use cases, ReminderScheduler interface
├── presentation/  # Compose screens, ViewModels, MainActivity (NavHost)
├── di/            # Hilt modules
├── notification/  # NotificationHelper
└── scheduling/    # AlarmSchedulerImpl, ReminderReceiver, BootReceiver
```

Entry points: `MainActivity.kt` (single activity, NavHost), `ReminderApp.kt` (@HiltAndroidApp).

# Broadcast Receivers

- `ReminderReceiver`: handles `ACTION_FIRE`, `ACTION_SNOOZE`, `ACTION_DONE`. Uses `goAsync()` with manual `pendingResult.finish()` and `ReceiverEntryPoint` for Hilt injection.
- `BootReceiver`: handles `BOOT_COMPLETED`, `MY_PACKAGE_REPLACED`, `TIME_SET`, `TIMEZONE_CHANGED`. Exports exported=true for system intents.

# Testing

- Libraries: JUnit 4, MockK, Robolectric, Google Truth, kotlinx-coroutines-test, Room testing.
- Tests use `runTest` from coroutines-test, `mockk(relaxed = true)` patterns.
- Robolectric enabled via `testOptions.unitTests.isIncludeAndroidResources = true`.

# Android Details

- Namespace: `com.reminder.service`
- Min SDK 26, Target SDK 34, Compile SDK 35
- `AlarmSchedulerImpl` uses `setExactAndAllowWhileIdle()` with exact alarm permission fallback
- `ReminderReceiver` repeats alarms by advancing time past `System.currentTimeMillis()` to avoid notification storms
- Room KSP codegen: dependencies include `ksp(libs.androidx.room.compiler)`
- No lint/typecheck commands configured; use Android Studio built-in tools.

# Dependencies

Version catalog: `gradle/libs.versions.toml`. Main deps: Hilt, Room, Compose, Coroutines.