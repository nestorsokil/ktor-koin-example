package cinemadb.core

import com.github.jasync.sql.db.Configuration
import com.github.jasync.sql.db.RowData
import com.github.jasync.sql.db.asSuspending
import com.github.jasync.sql.db.mysql.pool.MySQLConnectionFactory
import com.github.jasync.sql.db.pool.ConnectionPool
import com.github.jasync.sql.db.pool.PoolConfiguration
import org.koin.dsl.module.module
import java.time.LocalDate
import java.time.LocalDateTime

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

class OptimisticLockingFailureException(message: String?) : RuntimeException(message)

//TODO remove once joda time is removed https://github.com/jasync-sql/jasync-sql/blob/master/TODO.md
fun RowData.getLocalDate(column: String): LocalDate? {
    val localDate = get(column) as org.joda.time.LocalDate?
    return LocalDate.parse(localDate!!.toString("yyyy-MM-dd"))
}

fun RowData.getLocalDateTime(column: String): LocalDateTime? {
    val localDateTime = get(column) as org.joda.time.LocalDateTime?
    return LocalDateTime.parse(localDateTime!!.toString("yyyy-MM-dd HH:mm:ss"))
}

fun <R> unwrap(rowData: RowData, column: String, valueRetriever: RowData.(String) -> R?): R {
    return rowData.valueRetriever(column)
        ?: throw IllegalStateException("Retrieved 'Null' value from database")
}
