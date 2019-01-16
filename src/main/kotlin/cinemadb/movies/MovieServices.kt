package cinemadb.movies


class MovieService {
    fun findAll(): List<MovieDto> {
        return listOf(MovieDto("1"), MovieDto("2"))
    }

    fun findOne(id: String): MovieDto {
        return MovieDto("3")
    }

    fun create(input: MovieDto): MovieDto {
        return MovieDto("4")
    }

    fun update(id: String, input: MovieDto) {

    }

    fun delete(id: String) {

    }
}
