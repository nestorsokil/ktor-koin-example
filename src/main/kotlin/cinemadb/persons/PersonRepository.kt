package cinemadb.persons

import cinemadb.core.OptimisticLockingFailureException
import cinemadb.core.getLocalDateTime
import cinemadb.core.unwrap
import com.github.jasync.sql.db.RowData
import com.github.jasync.sql.db.SuspendingConnection

class PersonRepository(private val connection: SuspendingConnection) {

    suspend fun findAll() =
        connection.sendPreparedStatement("select * from persons").rows.map { personDtoFrom(it) }

    suspend fun findOne(id: String) =
        connection.sendPreparedStatement("select * from persons where id = ?", listOf(id))
            .rows.first().let { personDtoFrom(it) }

    suspend fun create(input: PersonDto): PersonDto {
        connection.sendPreparedStatement(
            "insert into persons (id, first_name, last_name, gender, age) " +
                    "values (?, ?, ?, ?, ?)",
            listOf(input.id, input.firstName, input.lastName, input.gender, input.age)
        )

        return input;
    }

    suspend fun update(id: String, input: PersonDto) {
        val result = connection.sendPreparedStatement(
            "update persons set first_name = ?, last_name = ?, gender = ?, age = ?, version = ? where id = ? and version = ?",
            listOf(
                input.firstName,
                input.lastName,
                input.gender,
                input.age,
                input.version + 1,
                id,
                input.version
            )
        )
        if (result.rowsAffected == 0L) {
            throw OptimisticLockingFailureException("Failed to update $input")
        }
    }

    suspend fun delete(id: String) {
        val result =
            connection.sendPreparedStatement("delete from persons where id = ?", listOf(id))

        if (result.rowsAffected == 0L) {
            throw NoSuchElementException("Failed to delete $id")
        }
    }
}

fun personDtoFrom(rowData: RowData): PersonDto {
    return PersonDto(
        unwrap(rowData, "id") { getString(it) },
        unwrap(rowData, "first_name") { getString(it) },
        unwrap(rowData, "last_name") { getString(it) },
        Gender.valueOf(unwrap(rowData, "gender") { getString(it) }),
        unwrap(rowData, "age") { getInt(it) },
        unwrap(rowData, "version") { getInt(it) },
        unwrap(rowData, "created") { getLocalDateTime(it) },
        unwrap(rowData, "updated") { getLocalDateTime(it) }
    )
}

