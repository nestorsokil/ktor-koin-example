package cinemadb.core

import com.github.jasync.sql.db.Configuration
import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.pool.PoolConfiguration
import org.koin.dsl.module.module

val coreModule = module {

    single {
        val factory = MySQLConnectionFactory(
            Configuration(
                username = getProperty("database.username"),
                password = getProperty("database.password"),
                host = getProperty("database.host"),
                port = getProperty("database.port"),
                database = getProperty("database.name")
            )
        )
        ConnectionPool(factory, PoolConfiguration.Default).connect().get().asSuspending
    }
}
