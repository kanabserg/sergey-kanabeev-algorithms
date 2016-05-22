/**
 * Создаём циклический двусвязный список. Каждый элемент должен иметь ссылку на следующий и предыдущий элементы.
 * Первый элемент при попытке перейти на предыдущий попадает на последний — соответственно последний при попытке пойти на следующий
 * попадает на первый.
 *
 * Требования к списку:
 *
 *
 * Операции доступа к элементу по индексу, удаления/добавления по индексу, получение длины.
 *
 * Список параметризованный. Параметром может быть только сравниваемый тип — у списка должна быть операция сортировки(использовать
 * любую)
 *
 * Делаем свой интерфейс двусвязного списка — должна быть возможность каким угодно образом ходить вперёд и назад по массиву
 * (используем двусвзяность!). * Как вариант, возвращать свой двунаправленный итератор с операциями next и prev, но тут на
 * ваше усмотрение.
 *
 * Список должен реализовывать Iterable интерфейс — будем бегать по нему с foreach. Важный момент — хоть список и циклический,
 * но итератор не хотел бы на последнем элементе возвращать true на hasNext — мы не хотим бегать вечно!
 *
 * Реализацию Cloneable делать не надо.
 *
 * Бонусный уровень для любителей дженериков. Создаём класс функции, которая принимает 2 параметра: входной тип и выходной.
 * У неё есть метод apply, который соответственно из переменной одного типа возвращает переменную другого. Для нашего листа
 * делаем метод map, который принимает на вход объект класса функции, применяет её ко всем элементам листа и возвращает таким
 * образом новый лист с новым типом. Пока не используем лямбды! Тут понадобится знание параметризованных методов, которые мы
 * не рассмотрели, так что до окончания темы дженериков это задание необязательное, как только тему добьём, оно таковым станет,
 * но можно начать и сейчас. Результатом должна быть возможность преобразовать наш лист одного типа в другой, передав, например,
 * анонимную функцию.
 *
 * Добавляем исключения, например, в случае доступа по индексу за границами листа.
 *
 * Теперь лист должен выбрасывать ConcurrentModificationException при попытке модификации листа, когда с ним уже работает итератор.
 */
package com.epam.task_2;