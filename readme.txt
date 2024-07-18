# Управление производителями и продуктами

Это веб-приложение для управления производителями и их продуктами. Приложение предоставляет возможность просмотра, добавления, редактирования и удаления информации о производителях и продуктах.

## Зависимости

Для запуска приложения вам понадобятся следующие зависимости:

-Java Development Kit (JDK): Убедитесь, что у вас установлена JDK на вашей системе. Spring требует JDK для компиляции и запуска Java-кода. Рекомендуется использовать JDK версии 8 или выше.

-Среда разработки (IDE): Выберите среду разработки, которая поддерживает Java и Spring, к примеру это может быть IntelliJ IDEA.

## Установка

1. Склонируйте репозиторий на свой локальный компьютер:

git clone https://github.com/maxim2808/DC-Test

2. Откройте проект с помощью среды разработки:

## Конфигурация

1.Если у вас отсутствует база данных то создайте её

2.Если в качесиве базы данных используется не PostgreSQL то в pom.xml необходимо добавить зависимость с необходимым драйвером

3.Подключите базу данных к среде разработки, в IntelliJ IDEA для этого используется раздел database, где нужно добавить необходимый datasourse в зависимости от используемой базы данных.

4.Переименуйте файл application.properties.origin в application.properties

5.Заполните недостающие поля:

spring.application.name=DC-Test-Telegin

spring.datasource.driver-class-name="Введите драйвер через который будет производиться взаимодействие с базой данных"

spring.datasource.username="Введите логин от вашей базы данных"

spring.datasource.password="Введите пароль от вашей базы данных"

spring.datasource.url="Введите адрес вашей базы данных"

spring.jpa.properties.hibernate.dialect="Введите диалект для взаимодействия с базой данных"

spring.jpa.properties.hibernate.show_sql="Введите true или false в зависимости от того нужно ли вам отображать sql запросы пользователей в консоли "


spring.mvc.hiddenmethod.filter.enabled=true


Ниже представлен пример заполнения:

spring.application.name=DC-Test-Telegin

spring.datasource.driver-class-name=org.postgresql.Driver

spring.datasource.username=username

spring.datasource.password=password

spring.datasource.url= jdbc:postgresql://localhost:5432/Digital_Chief_Tsialehin_Test

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

spring.jpa.properties.hibernate.show_sql=true


spring.mvc.hiddenmethod.filter.enabled=true

6.Необходимо создать таблицы для работы с базой данных, в PostgreSQL запросы выглядят так:

create table manufacturer(manufacturer_id int primary key  generated always as identity, manufacturer_name varchar(40) not null,
year_of_creation int, country varchar(50) not null, registration_date timestamp);

create table product (product_id int primary key generated always as identity, product_name varchar(50) not null,
    manufacturer_id int references manufacturer(manufacturer_id),
price float(2) not null, quantity int check (quantity>=0), weight float(2) check ( weight>=0 ),
                      year_of_release int check ( year_of_release>=1950), registration_date timestamp);



## Запуск

Для запуска приложения откройте его в среде разработки и нажмите run или комбинацию клавиш Shift+F10

## Использование

Поставщики:


Для получения списка поставщиков необходимо ввести в браузере:

http://localhost:8080/manufacturer(В этом и дальнейших запросах вместо localhost:8080 может быть другой адрес в зависимости от сервера)

Либо в postman произвести GET запрос по этому адресу.

Для получения одного поставщика необходимо перейти по этому адресу или совершить GET запрос в postman:


http://localhost:8080/manufacturer/{id} - вместо {id} необходимо ввести id необходимого поставщика;

Для добавления нового поставщика необходимо совершить post запрос по следующему адресу:

http://localhost:8080/manufacturer/add

Вот пример:

{
        "name": "Название",
        "country": "Страна",
        "yearOfCreation":Год основания компании
    }

Для редактирования уже созданного поставщика необходимо осуществить patch запрос по следующему адресу:

http://localhost:8080/manufacturer/edit/{id} - вместо {id} необходимо ввести id необходимого поставщика:

Вот пример:

{
        "name": "Название",
        "country": "Страна",
        "yearOfCreation":Год основания компании
 }

Для удаления поставщика можно воспользоваться запросом 

http://localhost:8080/manufacturer/delete/{id} - вместо {id} необходимо ввести id необходимого поставщика.




Товары:

Для получения списка товаров необходимо ввести в браузере:

http://localhost:8080/product

Либо в postman произвести GET запрос по этому адресу.

Для получения одного товара необходимо перейти по этому адресу или совершить GET запрос в postman:

http://localhost:8080/product/{id} - вместо {id} необходимо ввести id необходимого товара;

Для добавления нового товара необходимо совершить post запрос по следующему адресу:

http://localhost:8080/product/add

Вот пример

{
"product": {
    "name": "Название товара ",
    "price": Цена,
    "quantity": Количество товара,
    "weight": Вес,
    "yearOfRelease": Год выхода на рынок
        },
        "manufacturerID": id поставщика
}

Для редактирования уже созданного товара необходимо осуществить patch запрос по следующему адресу:

http://localhost:8080/product/edit/{id} - вместо {id} необходимо ввести id необходимого товара:

{
"product": {
    "name": "Название товара ",
    "price": Цена,
    "quantity": Количество товара,
    "weight": Вес,
    "yearOfRelease": Год выхода на рынок
        },
        "manufacturerID": id поставщика
}

Для удаления товара можно воспользоваться запросом 

http://localhost:8080/product/delete/{id} - вместо {id} необходимо ввести id необходимого товара;
































