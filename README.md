# Event Booking Service

REST-сервіс для бронювання подій. Зараз це каркас проєкту: налаштовані збірка, профілі, CI та локальний PostgreSQL, а бізнес-логіка й ендпоінти ще попереду.

**Стек:** Java 21 · Spring Boot 4.1 · Maven · PostgreSQL · Docker Compose

## Швидкий старт

Потрібні JDK 21 і Git Bash або інша bash-оболонка. Docker потрібен лише для PostgreSQL. Maven встановлювати не треба, у проєкті є Maven Wrapper.

```bash
./scripts/setup.sh                                    # перевірка JDK і створення .env
./mvnw verify                                         # збірка і тести
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev # запуск на http://localhost:8080
```

У PowerShell замість `./mvnw` використовуй `.\mvnw.cmd`, а аргумент `-D...` бери в лапки. Зручніше через `tasks.ps1`, дивись розділ [Команди](#команди).

> Застосунок поки не підключається до бази (у `pom.xml` немає JDBC-залежності), тому для запуску PostgreSQL не потрібен. Також ще немає жодного ендпоінта, тож будь-який запит поверне `404`.

## Команди

| make | без make | PowerShell | Що робить |
| --- | --- | --- | --- |
| `make` | — | `.\tasks.ps1` | Показати список команд |
| `make setup` | `./scripts/setup.sh` | — (запускай у Git Bash) | Перший запуск: перевірка JDK і `.env` |
| `make test` | `./mvnw test` | `.\tasks.ps1 test` | Тільки тести |
| `make verify` | `./mvnw verify` | `.\tasks.ps1 verify` | Повна перевірка, як у CI |
| `make run` | `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev` | `.\tasks.ps1 run` | Запуск із профілем `dev` |
| `make db-up` | `docker compose up -d` | `.\tasks.ps1 db-up` | Запустити PostgreSQL у Docker |
| `make db-down` | `docker compose down` | `.\tasks.ps1 db-down` | Зупинити PostgreSQL |
| `make clean` | `./mvnw -q clean` | `.\tasks.ps1 clean` | Прибрати теку `target` |

`.\tasks.ps1 verify` виконує `clean verify`, тобто спершу очищає `target`.

Параметри бази (`DB_NAME`, `DB_USER`, `DB_PASSWORD`, `DB_PORT`) задаються в `.env`. Шаблон лежить у `.env.example`, а сам `.env` не комітиться.

## Профілі

| Профіль | Файл | Що міняє |
| --- | --- | --- |
| базовий | `application.yaml` | Спільні налаштування: порт `8080`, помилки у форматі RFC 9457 |
| `dev` | `application-dev.yaml` | `DEBUG`-логи для пакета `com.eventbooking` |
| `test` | `application-test.yaml` (ще не створено) | Тимчасове сховище. Поки що тести працюють на базовому профілі |

## Структура

```text
src/main/java/com/eventbooking/   # кореневий пакет застосунку
└── EventBooking1Application.java # точка входу Spring Boot
src/main/resources/               # конфігурація профілів
src/test/java/com/eventbooking/   # тести, зараз лише перевірка підняття контексту
scripts/setup.sh                  # перевірка оточення для першого запуску
Makefile, tasks.ps1               # короткі команди для bash і PowerShell
docker-compose.yml                # локальний PostgreSQL 16
.github/workflows/ci.yml          # CI: ./mvnw -B verify на кожен PR і push у main
```

## Troubleshooting

| Симптом | Причина | Рішення |
| --- | --- | --- |
| `release version 21 not supported` | Maven працює на старій JDK | Виправити `JAVA_HOME`, щоб він вказував на JDK 21 |
| `Потрібна Java 21+, зараз: 17` від `setup.sh`, хоча `./mvnw` працює | `setup.sh` бере `java` з `PATH`, а `./mvnw` бере `JAVA_HOME`, і вони вказують на різні JDK | Поставити `bin` із JDK 21 у `PATH` перед старою JDK і перевідкрити термінал |
| `port 8080 already in use` | Порт зайнятий іншим процесом | Зупинити той процес або запустити на іншому порту: `./mvnw spring-boot:run -Dspring-boot.run.profiles=dev -Dspring-boot.run.arguments=--server.port=8081` |
| `APPLICATION FAILED TO START` з Jackson | Старий ключ у yml | Видалити блок `jackson` з yml-файлу |
| `make: command not found` | `make` не встановлено (типово для Windows) | Використати команди з колонок «без make» або «PowerShell» |
