# Модуль для сбора пользовательского поведения

## Инициализация
Для инициализации Трекинга пользовательского поведения вам нужно добавить следующий код в ваш класс Application в метод onCreate():
```java
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        new UserBehaviourTracker.Builder(this).build();
    }
}
```

Инициализацию в приложении также можно выполнить с помощью дополнительных свойств.
* Добавление дополнительной метаинформации о графических элементах
* Расширение функциональности трекера для сбора других данных о пользовательском поведении с помощью кастомных реализаций интерфейса [DataCollector](tracker/src/main/java/dev/martisv/userbehaviour/tracker/datacollector/DataCollector.java)
* Дополнительные опции. Например, включение/отключение логирования

```java
  ViewMetaInfoDictionary viewMetaInfoDictionary = new ViewMetaInfoDictionary()
        .add(R.id.recycler_view_button, new ViewMetaProperty("text", "Activity with RecyclerView"), new ViewMetaProperty("color", "purple"))
        .add(R.id.text_view_bottom, new ViewMetaProperty("other", "this is important meta information"));

        new UserBehaviourTracker
                .Builder(this)
                .setMetaDictionary(viewMetaInfoDictionary)
                .addDataCollector(new CustomDataCollector()
                .setOptions(new TrackerOptions(true, false, false))
        .build();
```

## Сбор данных
После инициализации UserBehaviourTracker подписываеться на жизненый цикл каждой активити в приложении с помощью класса [AppActivityLifecycleCallback](tracker/src/main/java/dev/martisv/userbehaviour/tracker/activitylifecycle/AppActivityLifecycleCallback.java) и начинает отслеживать клики в методе onActivityCreated
```java
@Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
        final Window activityWindow = activity.getWindow();
        final Window.Callback localCallback = activityWindow.getCallback();
        activityWindow.setCallback(new AndroidWindowCallback(localCallback, touchEventHandler));
    }
```

Сохранение элементов экрана происходит в следующих колбеков жизненого цикла активити
```java

    @Override
    public void onActivityResumed(@NonNull Activity activity) {
        rootView = activity.findViewById(android.R.id.content);
        listener = () -> {
            viewElementsSaver.saveView(rootView);
        };
        rootView.getViewTreeObserver().addOnDrawListener(listener);
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
        rootView.getViewTreeObserver().removeOnDrawListener(listener);
    }
```

## Структура проекта 
* activitylifecycle - классы для отслеживания жизненого цикла активити
* clickhandler - классы для обработки кликов
* datacollector - сбора данных о пользовательском поведении
* repository - репозиторий для хранения или отправки данных. На данный момент используется для логирования кликов
* utils - вспомогательные классы
