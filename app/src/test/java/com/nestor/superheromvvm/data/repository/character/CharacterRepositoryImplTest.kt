package com.nestor.superheromvvm.data.repository.character

import com.nestor.superheromvvm.data.remote.CharacterService
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

internal class CharacterRepositoryImplTest {
    val characterService: CharacterService = mockk()
    val sut = CharacterRepositoryImpl(characterService)

    /**
     * When calling CharacterRepositoryImpl.getCharacterSeries()
     * we need to make sure that CharacterService.getCharacterSeries() is called with the right arguments.
     */
    @Test
    fun `getCharacterSeries should call service_getCharacterSeries`() = runBlocking {
        //  TODO: Complete this test
        coEvery { characterService.getCharacterSeries(any(), any(), any()) }.returns(mockk {
            every { isSuccessful }.returns(true)
        })
        sut.getCharacterSeries(1005, PaginationKey(1, 1))
        coVerify (exactly = 1) { characterService.getCharacterSeries(1005, 1, 1) }
    }


    @Test
    fun `getCharacter should call service_getCharacterById`() = runBlocking {
        coEvery { characterService.getCharacterById(any()) }.returns(mockk {
            every { isSuccessful }.returns(false)
        })
        sut.getCharacter(1005)
        coVerify(exactly = 1) { characterService.getCharacterById(1005) }
    }
}