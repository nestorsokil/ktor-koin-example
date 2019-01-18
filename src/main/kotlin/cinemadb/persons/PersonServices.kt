package cinemadb.persons

class PersonService(private val personRepository: PersonRepository) {

    suspend fun findAll() = personRepository.findAll()

    suspend fun findOne(id: String) = personRepository.findOne(id)

    suspend fun create(input: PersonDto) = personRepository.create(input).id!!

    suspend fun update(id: String, input: PersonDto) {
        personRepository.update(id, input)
    }

    suspend fun delete(id: String) {
        personRepository.delete(id)
    }
}
