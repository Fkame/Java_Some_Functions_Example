# Что это такое?
Это небольшие примеры кода:
- Вывода classpath приложения
> ClassPathGetter.java

- Вывода самой базовой информации о загрузчиках
> LoadersInfoGetter.java

- Загрузки класса, который находится вне classpath приложения при запуске (ну типа в Runtime'е подгрузить класс откуда-то - непонятно откуда)
> LoadForeignClass.java

- Получения информации о состоянии памяти
> MemoryDataGetterFromMXBean.java
>
> PerforemanceTestingByMXBean.java

> MemoryDataGetterFromRuntime.java
>
> PerforemanceTestingByRuntime.java

- Получения информации о сборщиках мусора
> GarbageCollectorsInfoGetter.java

- - -
# Зачем это нужно?
~~Сомневаюсь, что это может быть кому-либо вообще нужно.~~ Просто примеры.

- - -
# Как это запустить?
1. Убедиться, что стоит jdk 11 или выше.

2. Перейти в директорию, где будет располагаться проект, в  консольном git ввести:
> git clone https://github.com/Fkame/Java_Some_Functions_Example.git

3. Открыть проект в любой ide, и запустить любой понравившийся тест (метод, помеченный **@Test**) *(есть ньюанс, см. след. подпункт)* в папке *src/test/java/com/example/*

- - -
## Особенность при запуске теста LoadForeignClassTest
Тест упадёт, так как для него нужно отдельно скомпилировать классы:
> SomeClassWithDependency.java

>SomeForeignClass.java

> ThreePackClass.java

- - -
### Как скомпилировать классы вне classpath для теста LoadForeignClassTest? ###
1. Перейти в папку *SomeFolderOutOfClassPath*.

2. Прописать в командной строке:
> javac onepack/twopack/demo/SomeClassWithDependency.java

> javac onepack/twopack/demo/SomeForeignClass.java

В первом случае класс-зависимость скомпилируется рекурсивно: пакетная рекурсия вроде или что-то вроде того (если честно, сам не знаю).



