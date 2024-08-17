# Шаги по воспроизведению автотестов

1. Командой git clone склонировать репозиторий к себе на локальную папку
2. Запустить программу Docker Dekstop
3. Запустить Intellij IDEA
4. Открыть на локальной машине склонированный проект в Intellij IDEA 
5. Запустить контейнеры в терминале командой docker compose up
6. Запустить SUT командой: java -jar ./artifacts/aqa-shop.jar
7. Запустить тесты командой на выбор в терминале: 
   - **Для MSQL** ./gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app
   - **Для Postgresql** ./gradlew clean test "-Ddb.url=jdbc:postgresql://localhost:5432/app"
8. Сгенирировать отчеты Allure в терминале: ./gradlew allureServe  (или ./gradlew allureReport )
9. Изучить сформированный в браузере отчет. 