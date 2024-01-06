# Дипломная работа №2
QA Java Project Diplom_2

# Описание проекта
Проект-Зададние №2 по тестированию API: Создание и Авторизация пользователя, создание и получение заказов
Используемые технологии: 
Java 11, junit 4.3.12, maven 3.6.3, rest-assured 5.3.2,
google.code.gson 2.10.1, jackson-databind 2.15.1, allure 2.25.0, aspectjweaver 1.9.7

# Запуск проекта
Команда для запуска - `mvn clean test`
Команда для генерации отчета плагином maven-surefire-plugin - allure serve target/surefire-reports/

Запустить тесты командой 'mvn clean test' в консоли. 
В папке target появится подпапка allure-results с отчётом Allure.
Перейти в папку проекта и выполни команду:  'mvn allure:serve' 
Запустится веб-сервер Allure, и в браузере откроется вкладка с отчётом.

После запуска API будут проверены и сформируется Allure отчет по покрытию в \target\allure-results

# Отчёт Allure
Сгенеририровать отчет и запушь его в репозиторий.
Чтобы добавить в коммит только отчёт, нужно перейти в папку проекта в консоли и выполнить команды:
- добавить папку с отчётом Allure к файлам. Ключ -f пригодится, если папка target указана в .gitignore:
git add -f .\target\allure-results\.
- выполнить коммит:
git commit -m "add allure report"
- так отправишь файлы в удалённый репозиторий:
git push 