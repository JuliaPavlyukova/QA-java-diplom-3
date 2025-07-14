# QA-java-diplom-3


Для тестирования в Yandex Browser использовалась:
версия yandexdriver-25.6.0.2259-win, https://github.com/yandex/YandexDriver/releases/tag/v25.6.0-stable
версия Yandex 25.6.0.2370 (64-bit)



Для переключения с одного браузера на другой надо выбрать в src/test/resources/browser.properties
testBrowser = YANDEX
или
testBrowser = CHROME

Обновление Allure-отчета:
mvn clean test
mvn allure:serve 
