.DEFAULT_GOAL := help
MVN ?= ./mvnw

.PHONY: help setup test verify run db-up db-down clean

help: ## Показати команди
	@grep -E '^[a-zA-Z_-]+:.*?## ' $(MAKEFILE_LIST) | awk 'BEGIN {FS = ":.*?## "}; {printf "  %-10s %s\n", $$1, $$2}'

setup: ## Перший запуск: перевірка JDK і .env
	./scripts/setup.sh

test: ## Тільки тести
	$(MVN) test

verify: ## Повна перевірка, як у CI
	$(MVN) verify

run: ## Запустити застосунок з профілем dev
	$(MVN) spring-boot:run -Dspring-boot.run.profiles=dev

db-up: ## Запустити PostgreSQL у Docker
	docker compose up -d

db-down: ## Зупинити PostgreSQL
	docker compose down

clean: ## Прибрати теку target
	$(MVN) -q clean
