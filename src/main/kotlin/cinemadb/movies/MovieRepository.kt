package cinemadb.movies

import cinemadb.core.getLocalDate
import com.github.jasync.sql.db.RowData
import com.github.jasync.sql.db.SuspendingConnection
import java.time.Duration

class MovieRepository(private val connection: SuspendingConnection) {

    suspend fun findAll() =
        connection.sendPreparedStatement("select * from movies").rows.map { movieDtoFrom(it) }

    suspend fun findOne(id: String) =
        connection.sendPreparedStatement("select * from movies where id = ?", listOf(id))
            .rows.first().let { movieDtoFrom(it) }

    suspend fun create(input: MovieDto): MovieDto {
        connection.sendPreparedStatement(
            "insert into movies (id, name, genre, duration, release_date) values (?,?,?,?,?)",
            listOf(
                input.id,
                input.name,
                input.genre.name,
                input.duration.toString(),
                input.releaseDate.toString() //TODO workaround for joda-time
            )
        )
        return input
    }

    suspend fun update(id: String, input: MovieDto) {
        connection.sendPreparedStatement(
            "update movies set name = ?, genre = ?, duration = ?, release_date = ? where id = ?",
            listOf(
                input.name,
                input.genre.name,
                input.duration.toString(),
                input.releaseDate.toString(),
                id
            )
        )
    }

    suspend fun delete(id: String) {
        connection.sendPreparedStatement("delete from movies where id = ?", listOf(id))
    }
}

internal fun movieDtoFrom(rowData: RowData): MovieDto {
    return MovieDto(
        rowData.getString("id"),
        rowData.getString("name")!!,
        MovieGenre.valueOf(rowData.getString("genre")!!),
        Duration.parse(rowData.getString("duration")),
        rowData.getLocalDate("release_date")!!
    )
}



