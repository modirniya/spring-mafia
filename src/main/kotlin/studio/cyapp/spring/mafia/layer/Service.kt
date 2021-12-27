package studio.cyapp.spring.mafia.layer

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import studio.cyapp.spring.mafia.entity.RootElement

@Service
class Service {
    @Autowired
    private lateinit var repo: DynamoDBRepo

    fun addLobby(hostInfo: RootElement): String {
        repo.store(hostInfo.apply {
            pk = "#LOBBY$pk"
            sk = "#HOST"
        })
        val shortId = generateShortId(6)
        repo.store(
            RootElement().apply {
                pk = "#SHORT_ID"
                sk = shortId
                rk = hostInfo.pk
            }
        )
        return shortId
    }

    fun joinLobby(shortId: String, playerInfo: RootElement): String {
        val lobbyId = getIfLobbyExist_Or(shortId) ?: return "not found"
        repo.store(
            playerInfo.apply {
                pk = lobbyId
                sk = "#PLAYER$sk"
            }
        )
        return lobbyId
    }

    fun getLobbyMembers(shortId: String):
            List<String> {
        val lobbyId =
            getIfLobbyExist_Or(shortId) ?: return emptyList()
        val paginatedQueryList = repo.query(withQueryExpressionOf(lobbyId))
        val list = mutableListOf<String>()
        paginatedQueryList.map {
            list.add(it.nickname)
        }
        return list
    }

    private fun withQueryExpressionOf(lobbyId: String): DynamoDBQueryExpression<RootElement> {
        val expression = getQueryExpressionByHashKeyValueOf(
            values = RootElement().apply {
                pk = lobbyId
            })
        return expression
    }

    private fun getQueryExpressionByHashKeyValueOf(values: RootElement): DynamoDBQueryExpression<RootElement> {
        return DynamoDBQueryExpression<RootElement>().apply {
            withConsistentRead(false)
            withHashKeyValues(values)
        }
    }

    fun announceRoles(shortId: String, entries: List<String>) {

    }

    private fun getIfLobbyExist_Or(shortId: String): String? =
        repo.retrieve(
            pk = "#SHORT_ID",
            sk = shortId
        )?.rk


    private fun generateShortId(length: Int): String {
        // val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        val allowedChars = ('A'..'Z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}