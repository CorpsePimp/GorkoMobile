package com.example.gorko.data.local.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.gorko.data.local.entity.*

// Отношение Wedding с Users (многие ко многим)
data class WeddingWithUsers(
    @Embedded val wedding: WeddingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserWeddingCrossRef::class,
            parentColumn = "weddingId",
            entityColumn = "userId"
        )
    )
    val users: List<UserEntity>
)

// Отношение User с Weddings (многие ко многим)
data class UserWithWeddings(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = UserWeddingCrossRef::class,
            parentColumn = "userId",
            entityColumn = "weddingId"
        )
    )
    val weddings: List<WeddingEntity>
)

// Отношение Wedding со всеми связанными данными
data class WeddingWithAllData(
    @Embedded val wedding: WeddingEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "weddingId"
    )
    val tasks: List<TaskEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "weddingId"
    )
    val expenses: List<ExpenseEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "weddingId"
    )
    val guests: List<GuestEntity>,
    @Relation(
        parentColumn = "id",
        entityColumn = "weddingId"
    )
    val gifts: List<GiftEntity>
)

// Отношение для гостей с их столами
data class TableWithGuests(
    val tableNumber: Int,
    val guests: List<GuestEntity>
)