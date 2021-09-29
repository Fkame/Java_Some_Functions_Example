package onepack.twopack.demo;

/**
 * Класс для тестирования загрузки класса вне classpath программы.
 * ! Перед подключением нужно отдельно скомпилировать .class файл.
 */
public class SomeForeignClass {
    public SomeForeignClass() { }

    public void printHelloToConsole() {
        System.out.println("Foreign class in da game");
    }

    public String convertIntToArray(int a, int b, char operation) {
        return new StringBuilder().append(a).append(operation).append(b).toString();
    }
}
