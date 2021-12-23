package studio.cyapp.spring.mafia.layer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import studio.cyapp.spring.mafia.entity.RootElement

@Service
class Service {
    @Autowired
    private lateinit var repo: DynamoDBRepo

    fun createOrUpdateUser(nickname: String, tokenId: String): RootElement {
        return RootElement()
    }

    fun createUser(uid: String, name: String) {
        RootElement().apply {
            pk = "#USER$uid"
            sk = "#PROFILE"
            nickname = name
        }
    }

    fun fetchUser(uid: String): RootElement? {
        return repo.retrieve(pk = "#USER$uid", sk = "#PROFILE")
    }

}