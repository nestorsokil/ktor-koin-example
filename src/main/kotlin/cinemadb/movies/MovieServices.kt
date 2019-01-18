package cinemadb.movies

class MovieService(private val movieRepository: MovieRepository) {

    suspend fun findAll() = movieRepository.findAll()

    suspend fun findOne(id: String) = movieRepository.findOne(id)

    suspend fun create(input: MovieDto) = movieRepository.create(input).id!!

    suspend fun update(id: String, input: MovieDto) {
        movieRepository.update(id, input)
    }

    suspend fun delete(id: String) {
        movieRepository.delete(id)
    }
}
