package hostmann.metch

/**
 * 4. Добавьте case-класс Multiple, наследующий класс Item. Напри
 * * мер, Multiple(10, Article("Blackwell Toaster", 29.95)) описывает
 * десять тостеров. Разумеется, должна предусматриваться воз-
 * можность обрабатывать любые элементы, такие как пакет или
 * множитель, во втором аргументе. Расширьте функцию price,
 * чтобы она могла обрабатывать этот новый класс.
 */
case class Multiple(quantity: Int, item: Item) {
    def price: Double = {
        item.price * quantity
    }
}
