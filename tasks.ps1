param([string]$task = "help")

switch ($task) {
    "db-up"   { docker compose up -d }
    "db-down" { docker compose down }
    "test"    { .\mvnw.cmd test }
    "verify"  { .\mvnw.cmd clean verify }
    "run"     { .\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev" }
    "clean"   { .\mvnw.cmd clean }
    default {
        Write-Host "Команди: db-up, db-down, test, verify, run, clean"
        Write-Host "Приклад: .\tasks.ps1 run"
    }
}