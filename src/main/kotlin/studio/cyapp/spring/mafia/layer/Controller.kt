package studio.cyapp.spring.mafia.layer

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import studio.cyapp.spring.mafia.entity.RootElement

const val PATH_ROOT = "mafia-lobby"
const val PATH_NEW_LOBBY = "$PATH_ROOT/new"
const val PATH_LOBBY_ID = "$PATH_ROOT/{shortId}"
const val PATH_ANNOUNCE_ROLES = "$PATH_ROOT/roles/{shortId}"

@RestController
class Controller {

    @Autowired
    private lateinit var service: Service

    @PostMapping(PATH_NEW_LOBBY)
    fun newLobby(@RequestBody hostInfo: RootElement):
            ResponseEntity<String> {
        val response = service.addLobby(hostInfo)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping(PATH_LOBBY_ID)
    fun joinLobby(
        @PathVariable shortId: String,
        @RequestBody playerInfo: RootElement,
    ): ResponseEntity<String> {
        val response = service.joinLobby(shortId, playerInfo)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @GetMapping(PATH_LOBBY_ID)
    fun getLobbyMembers(@PathVariable shortId: String):
            ResponseEntity<List<String>> {
        val response = service.getLobbyMembers(shortId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping(PATH_ANNOUNCE_ROLES)
    fun announceRoles(@PathVariable shortId: String):
            ResponseEntity<Unit> {
        return ResponseEntity(Unit, HttpStatus.CREATED)
    }

}