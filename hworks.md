# hw 02 - ArrayList
Написать реализацию ArrayList на основе массива.
class DiyArrayList<T> implements List<T>

Проверить, что на ней работают методы из java.util.Collections:
static <T> boolean addAll(Collection<? super T> c, T... elements)
static <T> void copy(List<? super T> dest, List<? extends T> src)
static <T> void sort(List<T> list, Comparator<? super T> c)

1) Проверяйте на коллекциях содержащих >= 20 элементов.
2) Класс должен имплементировать ТОЛЬКО интерфейс List.
3) Если метод не имплементирован, то он должен выбрасывать исключение UnsupportedOperationException.

# [hw 01 - Gradle](app)

1) Создайте аккаунт на github.com (если еще нет)
2) Создайте репозиторий для домашних работ
3) Сделайте checkout репозитория на свой компьютер
4) Создайте локальный бранч hw01-gradle
5) Создать проект gradle
6) В проект добавьте последнюю версию зависимости
```
<groupId>com.google.guava</groupId>
<artifactId>guava</artifactId>
```
7) Создайте модуль hw01-gradle
8) В модуле сделайте класс
9) В этом классе сделайте вызов какого-нибудь метода из guava
10) Создайте "толстый-jar"
11) Убедитесь, что "толстый-jar" запускается.
