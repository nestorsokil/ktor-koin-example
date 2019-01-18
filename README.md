1. Run `docker-compose up`
 
2. Run `gradle flywayMigrate`

3. In Intellij go to:

```
Run > Edit Configurations...
```

Add new **Kotlin** configuration. In the field "*Main Class:*" add `io.ktor.server.netty.EngineMain`
