#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

echo "==> Перевіряю JDK"
if ! command -v java >/dev/null 2>&1; then
  echo "JDK не знайдено. Встанови JDK 21." >&2
  exit 1
fi
JAVA_MAJOR=$(java -XshowSettings:properties -version 2>&1 | awk -F'= ' '/java.specification.version/ {print $2}')
if [ "${JAVA_MAJOR%%.*}" -lt 21 ]; then
  echo "Потрібна Java 21+, зараз: ${JAVA_MAJOR}" >&2
  exit 1
fi
echo "    Java ${JAVA_MAJOR} - ок"

echo "==> .env"
if [ ! -f .env ]; then
  cp .env.example .env
  echo "    створено .env з .env.example"
else
  echo "    .env вже є"
fi

echo "==> Docker"
if command -v docker >/dev/null 2>&1; then
  echo "    docker знайдено"
else
  echo "    docker не знайдено - знадобиться з тижня 3"
fi

echo "==> Готово. Далі: ./mvnw verify"
