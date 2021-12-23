package studio.cyapp.spring.mafia.layer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

const val PATH_ROOT = "mafia-lobby"
const val PATH_NEW_LOBBY = "$PATH_ROOT/new"
const val PATH_LOBBY_ID = "$PATH_ROOT/{shortcutPhrase}"
const val PATH_ANNOUNCE_ROLES = "$PATH_ROOT/roles/{shortcutPhrase}"

@RestController
class Controller {

    @Autowired
    private lateinit var service: Service

    @PostMapping(PATH_NEW_LOBBY)
    fun newLobby(): ResponseEntity<String> {
        return ResponseEntity("New Lobby!", HttpStatus.OK)
    }

    @PostMapping(PATH_LOBBY_ID)
    fun joinLobby(@PathVariable shortcutPhrase: String): ResponseEntity<String> {
        return ResponseEntity("Join Lobby", HttpStatus.OK)
    }

    @GetMapping(PATH_LOBBY_ID)
    fun getLobbyMembers(@PathVariable shortcutPhrase: String): ResponseEntity<List<String>> {
        return ResponseEntity(listOf("a", "b", "c"), HttpStatus.OK)
    }

    @PostMapping(PATH_ANNOUNCE_ROLES)
    fun announceRoles(@PathVariable shortcutPhrase: String): ResponseEntity<Unit> {
        return ResponseEntity(Unit, HttpStatus.OK)
    }

}