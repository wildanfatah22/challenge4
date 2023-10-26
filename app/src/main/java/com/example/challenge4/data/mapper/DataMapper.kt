package com.example.challenge4.data.mapper

import com.example.challenge4.data.datasource.local.room.entity.NoteEntity
import com.example.challenge4.data.datasource.local.room.entity.UserEntity
import com.example.challenge4.domain.model.Note
import com.example.challenge4.domain.model.User

object DataMapper {

    /** NOTE MAPPER **/

    // List Note Entity to List Note
    fun noteMapEntitiesToDomain(input: List<NoteEntity>): List<Note> =
        input.map {
            Note(
                id = it.id,
                title = it.title,
                subTitle = it.subTitle,
                description = it.description,
                date = it.date,
                color = it.color,
            )
        }

    // Note Entity to Note
    fun oneNoteEntityToDomain(noteEntity: NoteEntity): Note =
        Note(
            id = noteEntity.id,
            title = noteEntity.title,
            subTitle = noteEntity.subTitle,
            description = noteEntity.description,
            date = noteEntity.date,
            color = noteEntity.color,
        )

    // Note to Note Entity
    fun noteDomainToEntity(note: Note) = NoteEntity(
        id = note.id,
        title = note.title,
        subTitle = note.subTitle,
        description = note.description,
        date = note.date,
        color = note.color,
    )

    /** USER MAPPER **/

    // User to User Entity
    fun userDomainToUserEntity(user: User) = UserEntity(
        id = user.id ?: 0,
        name = user.name,
        email = user.email,
        password = user.password
    )

    // UserEntity to User
    fun userLoginEntityToUserDomain(userEntity: UserEntity?) =
        if (userEntity == null) {
            null
        } else {
            User(
                id = userEntity.id,
                name = userEntity.name,
                email = userEntity.email,
                password = userEntity.password
            )
        }

    // UserEntity to User
    fun userEntityToUserDomain(userEntity: UserEntity) =
        User(
            id = userEntity.id,
            name = userEntity.name,
            email = userEntity.email,
            password = userEntity.password
        )
}