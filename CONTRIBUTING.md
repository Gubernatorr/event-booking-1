## Гілки 
Коротко: main завжди зелений, все йде через короткі гілки. Таблиця префіксів: feature/, fix/, refactor/, chore/ з прикладом для кожного (feature/file-storage) 

## Коміти	
Формат тип(область): що зроблено і список типів (feat, fix, refactor, test, docs, build, ci, chore). Приклади: feat(storage): add local file storage, fix(schedule): handle DST gap. На роботі додавай в кінці ID задачі (Refs: PROJ-123) 

## Pull 
Request	4 кроки: підтягнути свіжий main → ./mvnw verify → заповнити шаблон → merge після зеленого CI 

## Конфлікти	
Команди (нижче в кроці 8.5) і правило: розуміти обидва наміри, а не брати лише свою версію. Після розв'язання — verify